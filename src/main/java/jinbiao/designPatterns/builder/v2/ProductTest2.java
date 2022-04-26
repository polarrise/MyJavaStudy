package jinbiao.designPatterns.builder.v2;

import lombok.Data;

public class ProductTest2 {
    public static void main(String[] args) {
        Product.Builder builder1 = new Product.Builder().productName("channel").companyName("rise").part1("part1").part2("part2").part4("part4");
        builder1.part3("part3");
        Product product1 = builder1.build();
        System.out.println("product1:"+product1);

        /**
         * 踩了一个这样的坑。通过new类Product.Builder。得到了一个建造者对象。
         * 然后建造者对象通过productName,companyName等方法,又把这个建造者对象return出来了,所以可以通过以上那种方式连环对象.方法名称
         */
        System.out.println("以下是我踩过的坑,写的错代码===");
        Product pro1 = new Product.Builder().productName("channel").build();
        System.out.println(pro1);
        Product pro2 = new Product.Builder().companyName("rise").build();
        System.out.println(pro2);
        new Product.Builder().part1("part1");
        new Product.Builder().part2("part2");
        new Product.Builder().part3("part3");
        new Product.Builder().part4("part4");
        Product product2 = new Product.Builder().build();
        System.out.println("product2:"+product2);
        System.out.println("以上是我踩过的坑,写的错代码===");

        Product.Builder builder3 = new Product.Builder();
        builder3.productName("channel");
        builder3.companyName("rise");
        builder3.part1("part1");
        builder3.part2("part2");
        builder3.part3("part3");
        builder3.part4("part4");
        Product product3 = builder3.build();
        System.out.println("product3:"+product3);
    }
}


@Data
class Product{
    private final String productName;
    private final String companyName;
    private final String part1;
    private final String part2;
    private final String part3;
    private final String part4;
    //...

    public Product(String productName, String companyName, String part1, String part2, String part3, String part4) {
        this.productName = productName;
        this.companyName = companyName;
        this.part1 = part1;
        this.part2 = part2;
        this.part3 = part3;
        this.part4 = part4;
    }

    static class Builder{
        private  String productName;
        private  String companyName;
        private  String part1;
        private  String part2;
        private  String part3;
        private  String part4;

        public Builder productName(String productName){
            this.productName=productName;
            return this;
        }
        public Builder companyName(String companyName){
            this.companyName=companyName;
            return this;
        }
        public Builder part1(String part1){
            this.part1=part1;
            return this;
        }
        public Builder part2(String part2){
            this.part2=part2;
            return this;
        }
        public Builder part3(String part3){
            this.part3=part3;
            return this;
        }
        public Builder part4(String part4){
            this.part4=part4;
            return this;
        }
        public Product build(){
             //可以做一些校验的工作
            Product product = new Product(productName, companyName, part1, part2, part3, part4);
            return product;
        }
    }

}