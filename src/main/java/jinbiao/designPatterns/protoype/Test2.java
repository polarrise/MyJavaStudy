package jinbiao.designPatterns.protoype;

import java.util.ArrayList;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        List list=new ArrayList<Product>();
        list.add("111");
        Product Product=new Product();
        Product.setPart1("121");
        list.add(Product);
        System.out.println(list);

        Product product2=new Product();
        product2.setPart1("222");

        //Stream不会改变源对象，并且能返回一个持有结果的新流. 原对象是name为121的产品。  不能重新赋值改成222的产品!!!
        list.stream().forEach(a->{
            if(a instanceof Product){
                a=product2;
            }
        });
        System.out.println("111:"+list);


        for (Object a:list) {
            if(a instanceof Product){
                a=product2;
            }
        }
        System.out.println("222:"+list);

    }
}
