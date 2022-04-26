package jinbiao.designPatterns.protoype;

import lombok.Data;

/**
 * 原型模式:指原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象
 */
public class Test {
    public static void main(String[] args)  throws Exception{
        Product1  product= new Product1("rise");
        Object [] arr={"数组的拷贝是浅拷贝:",product};
        Object[] clone = arr.clone();

        Product1 product1 = (Product1) arr[1];
        product1.setName("rise1");
        System.out.println("只改变了原始数组对象arr的产品,克隆对象的产品为rise1也跟着变化了");
        for (Object i:arr) {
            System.out.print(i);
        }
        System.out.println();
        for (Object i:clone) {
            System.out.print(i);
        }

    }
}

@Data
class Product1 implements Cloneable{
    private  String name;
    public Product1(String name){
        this.name=name;
    }

//    @Override
//    protected Product1 clone() throws CloneNotSupportedException {
//        return (Product1) super.clone();
//    }

    @Override
    public String toString() {
        return "Product1{" +
                "name='" + name + '\'' +
                '}';
    }
}


