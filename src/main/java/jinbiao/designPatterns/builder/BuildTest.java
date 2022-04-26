package jinbiao.designPatterns.builder;

import lombok.Data;

/**
 * Builder模式的应用:
 *
 * 是为了将构建复杂对象的过程和它的部件解耦。注意：是解耦过程和部件。
 * 因为一个复杂的对象，不但有很多大量组成部分，如汽车，有很多部件：车轮、方向盘、发动机，还有各种小零件等等，部件很多，
 * 但远不止这些，如何将这些部件装配成一辆汽车，这个装配过程也很复杂(需要很好的组装技术)，
 * Builder模式就是为了将部件和组装过程分开。
 *
 *  在Java实际使用中，我们经常用到"池"(Pool)的概念，当资源提供者无法提供足够的资源，并且这些资源需要被很多用户反复共享时，
 * 就需要使用池。"池"实际是一段内存，当池中有一些复杂的资源的"断肢"(比如数据库的连接池，也许有时一个连接会中断)，
 * 如果循环再利用这些"断肢"，将提高内存使用效率，提高池的性能。修改Builder模式中Director类使之能诊断"断肢"断在哪个部件上，再修复这个部件。
 */
public class BuildTest {
    public static void main(String[] args) {
//        Product product=new Product();
//        product.setCompanyName("");
//        product.setPart1("");

//        DefaultConcreteProductBuilder builder=new DefaultConcreteProductBuilder();
//        Director director = new Director(builder);
//        Product product = director.makeProduct("productNamexxx", "rise", "part1", "part2", "part3", "part4");
//        System.out.println(product);

        //1.创建建造者
        ProductBuilder specialBuilder=new SpecialConcreteProductBuilder();
        //2.创建Director,指导建造者该如何进行创建产品
        Director director = new Director(specialBuilder);
        //3.使用Director创建产品
        Product product = director.makeProduct("channel", "rise", "part1", "part2", "part3", "part4");
        System.out.println(product);
    }
}

/**
 * 定义抽象,建造一个公司需要什么.   产品名称,公司名称,  部件1，部件2，部件3，部件4  等等
 */
interface ProductBuilder{
    void buildProductName(String productName);
    void buildCompanyName(String companyName);
    void buildPart1(String part1);
    void buildPart2(String part2);
    void buildPart3(String part3);
    void buildPart4(String part4);

    Product build();
}

class DefaultConcreteProductBuilder implements ProductBuilder{
    private String productName;
    private String companyName;
    private String part1;
    private String part2;
    private String part3;
    private String part4;

    @Override
    public void buildProductName(String productName) {
        this.productName=productName;
    }
    @Override
    public void buildCompanyName(String companyName) {
        this.companyName=companyName;
    }
    @Override
    public void buildPart1(String part1) {
        this.part1=part1;
    }

    @Override
    public void buildPart2(String part2) {
        this.part2=part2;
    }
    @Override
    public void buildPart3(String part3) {
        this.part3=part3;
    }
    @Override
    public void buildPart4(String part4) {
        this.part4=part4;
    }
    @Override
    public Product build() {
        return new Product(productName,companyName,part1,part2,part3,part4);
    }
}

class SpecialConcreteProductBuilder implements ProductBuilder{
    private String productName;
    private String companyName;
    private String part1;
    private String part2;
    private String part3;
    private String part4;

    @Override
    public void buildProductName(String productName) {
        this.productName=productName;
    }
    @Override
    public void buildCompanyName(String companyName) {
        this.companyName=companyName;
    }
    @Override
    public void buildPart1(String part1) {
        this.part1=part1;
    }

    @Override
    public void buildPart2(String part2) {
        this.part2=part2;
    }
    @Override
    public void buildPart3(String part3) {
        this.part3=part3;
    }
    @Override
    public void buildPart4(String part4) {
        this.part4=part4;
    }
    @Override
    public Product build() {
        return new Product(productName,companyName,part1,part2,part3,part4);
    }
}

/**
 * 指导建造者该如何进行创建产品
 */
class Director{
    private ProductBuilder builder;
    public Director(ProductBuilder builder){   //构造方法传入一个建造者
         this.builder=builder;
    }
    //使用建造者创建产品~
    public Product makeProduct(String productName, String companyName, String part1, String part2, String part3, String part4){
        builder.buildProductName(productName);
        builder.buildCompanyName(companyName);
        builder.buildPart1(part1);
        builder.buildPart2(part2);
        builder.buildPart3(part3);
        builder.buildPart4(part4);
        Product product = builder.build();
        return product;
    }
}

@Data
class Product{
    private String productName;
    private String companyName;
    private String part1;
    private String part2;
    private String part3;
    private String part4;
    public Product(){

    }
    public Product(String productName, String companyName, String part1, String part2, String part3, String part4) {
        this.productName = productName;
        this.companyName = companyName;
        this.part1 = part1;
        this.part2 = part2;
        this.part3 = part3;
        this.part4 = part4;
    }

}