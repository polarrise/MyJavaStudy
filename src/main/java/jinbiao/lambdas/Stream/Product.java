package jinbiao.lambdas.Stream;

import com.google.common.collect.Lists;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jinbiao
 * @date 2021/12/22
 * Collectors.groupingBy根据一个或多个属性对集合中的项目进行分组
 */
@Data
public class Product {
    private Long id;
    private Integer num;
    private BigDecimal price;
    private String name;
    private String category;
    private String sumPrice;
    public Product(){

    }
    public Product(Long id, Integer num, BigDecimal price, String name, String category) {
        this.id = id;
        this.num = num;
        this.price = price;
        this.name = name;
        this.category = category;
    }

    /**
     * Collectors.groupingBy根据一个或多个属性对集合中的项目进行分组
     */
     public List test1(List<Product> prodList ){
         System.out.println("prodList:"+prodList);
         //1.分组:按照类目分组：
         Map<String, List<Product>> prodMap1= prodList.stream().collect(Collectors.groupingBy(Product::getCategory));
         System.out.println("prodMap1:"+prodMap1);


         //2.按照几个属性拼接分组：
         Map<String, List<Product>> prodMap2=prodList.stream().collect(Collectors.groupingBy(item->item.getCategory()+"_"+item.getName()));
         System.out.println("prodMap2:"+prodMap2);


         //3.根据类目分组:  求组内num的总和
         Map<String, Integer> prodMap3 = prodList.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.summingInt(Product::getNum))); //{"啤酒":13,"零食":6}
         System.out.println("prodMap3:"+prodMap3);

         //4.根据不同条件分组:   num<3来分组
         Map<String, List<Product>> prodMap4=prodList.stream().collect(Collectors.groupingBy(item->{
             if(item.getNum() < 3) {
                 return "3";
             }else {
                 return "other";
             }
         }));
         System.out.println("prodMap4:"+prodMap4);

         //多级分组:
        /* 要实现多级分组，我们可以使用一个由双参数版本的Collectors.groupingBy工厂方法创 建的收集器，它除了普通的分类函数之外，还可以接受collector类型的第二个参数。
          那么要进 行二级分组的话，我们可以把一个内层groupingBy传递给外层groupingBy，并定义一个为流 中项目分类的二级标准。*/
         Map<String, Map<String, List<Product>>> prodMap5= prodList.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.groupingBy(item -> {
             if(item.getNum() < 3) {
                 return "3";
             }else {
                 return "other";
             }
         })));
         System.out.println("prodMap5:"+prodMap5);

         //按子组收集数据:求总数       根据分类Category,求各自分类下的数据条数
         Map<String, Long> prodMap6 = prodList.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
         System.out.println("prodMap6:"+prodMap6);   //prodMap6:{啤酒=2, 零食=3}

         //按子组收集数据:求和         根据分类Category,求分类下num的和
         Map<String, Integer> prodMap7 = prodList.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.summingInt(Product::getNum)));
         System.out.println("prodMap7:"+prodMap7);   //prodMap7:{啤酒=13, 零食=6}

         //按子组收集数据:把收集器的结果转换为另一种类型         根据分类Category  收集各自分类下num为最大的数据
         Map<String, Product> prodMap8 = prodList.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Product::getNum)), Optional::get)));
         System.out.println("prodMap8:"+prodMap8);
//prodMap8:{啤酒=Product(id=5, num=10, price=15, name=百威啤酒, category=啤酒, sumPrice=null),零食=Product(id=3, num=3, price=30, name=月饼, category=零食, sumPrice=null)}

         //按子组收集数据:联合其他收集器           根据分类Category  收集名称Name
         Map<String, Set<String>> prodMap9 = prodList.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.mapping(Product::getName, Collectors.toSet())));
         System.out.println("prodMap9:"+prodMap9);   //prodMap9:{啤酒=[青岛啤酒, 百威啤酒], 零食=[面包, 饼干, 月饼]}


         return null;
     }

    public List test2(List<Product> prodList ){
         //根据类目来分组:
        Map<String, List<Product>> prodMap1= prodList.stream().collect(Collectors.groupingBy(Product::getCategory));  //List数组变成一个key,value的map
        for (Map.Entry<String, List<Product>> stringListEntry : prodMap1.entrySet()) {
            String key = stringListEntry.getKey();   //啤酒
            List<Product> value = stringListEntry.getValue();    //啤酒的两条数据
            //numValueList:[3,10]   map=>形成一个处理过后的数组
            List<Integer> numValueList = value.stream().map(b -> b.getNum() == null ? 0 : b.getNum()).collect(Collectors.toList());  //map=>形成一个处理过后的数组
            //待分配含量汇总
            BigDecimal sumPrice = BigDecimal.ZERO;
            for (Integer s : numValueList) {
                sumPrice=sumPrice.add(BigDecimal.valueOf(s));
            }
            for (Product product: value) {
                product.setSumPrice(sumPrice.toString());
            }
            System.out.println("sumPrice:"+sumPrice);

        }
        System.out.println("prodList:"+prodList);

        return null;
    }

    public static void main(String[] args) {
        Product prod1 = new Product(1L, 1, new BigDecimal("15.5"), "面包", "零食");
        Product prod2 = new Product(2L, 2, new BigDecimal("20"), "饼干", "零食");
        Product prod3 = new Product(3L, 3, new BigDecimal("30"), "月饼", "零食");
        Product prod4 = new Product(4L, 3, new BigDecimal("10"), "青岛啤酒", "啤酒");
        Product prod5 = new Product(5L, 10, new BigDecimal("15"), "百威啤酒", "啤酒");
        List<Product> prodList = Lists.newArrayList(prod1, prod2, prod3, prod4, prod5);

        new Product().test1(prodList);
        new Product().test2(prodList);
    }



}
