package com.jinbiao.designPatterns.protoype;

import lombok.Data;

import java.io.Serializable;

/**
 * 注意:数组[], ArrayList的clone方法,都是浅拷贝,并不会把数组里面引用类型对象(也就是可变元素)进行拷贝。 要实现里面元素的拷贝,得自己去实现里面的clone克隆方法
 * 原型模式:指原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象
 * 1.java的深拷贝.
 * 2.通过序列号与反序列化机制完成深拷贝 --不被推荐使用,要使用I/O解析流的操作,比较消耗性能的,速度比较慢~
 */
public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {

        BaseInfo baseInfo = new BaseInfo("Rise");
        Product product = new Product("part1", 110, 110l,baseInfo);
        //new Product  ....
        Product clone = product.clone();   //克隆Product产品对象

        //输出原始对象和克隆对象:
        System.out.println("输出原始对象和克隆对象==========:");
        System.out.println("product:"+product);
        System.out.println("clone:"+clone);
        System.out.println(product==clone);   //原始对象和对象不一样

        //不进行深拷贝的话(BaseInfo implements Cloneable):改变原始产品的baseInfo对象,会导致克隆对象的baseInfo也改变!!!
        product.getBaseInfo().setCompanyName("Rise111");
        product.setPart1("part111");
        product.setPart2(111);
        product.setPart3(111L);
        System.out.println("product:"+product);
        System.out.println("clone:"+clone);
    }
}

@Data
class BaseInfo implements Cloneable,Serializable{
    private static final long serialVersionUID = 42L;  //版本号:持久化到磁盘上,动态修改类的属性
    private String companyName;

    public BaseInfo(String companyName){
        this.companyName=companyName;
    }

    @Override
    protected BaseInfo clone() throws CloneNotSupportedException {
        return (BaseInfo) super.clone();
    }

    @Override
    public String toString() {
        return  "BaseInfo{" +
                "companyName='" + companyName + '\'' +
                '}';
    }
}

/**
 * 对于8种基础类型和String,Integer,Long等不可变的包装类型。的拷贝是浅拷贝。 提供一个克隆方法就可以达到目的
 * 但是如果该对象里面还有可变对象的引用(baseInfo)的话,就需要再进行一次拷贝。 深拷贝
 * 我的理解:也就是如果不对可变对象进行深拷贝。 那么原始Product对象和克隆Product对象会共享同一个baseInfo. 改变原始对象的baseInfo，克隆对象的baseInfo也会变化,因为他们共享同一份堆内存空间
 java.io.NotSerializableException: 在序列化与反序列化product对象的时候,因为内部含有baseInfo对象,所以Product,BaseInfo都需要实现序列号接口!
 */
@Data
class Product implements Cloneable,Serializable{  //只有实现Serializable接口,具备序列化与反序列化的能力。
    private static final long serialVersionUID = 42L;  //版本号:持久化到磁盘上,动态修改类的属性
    private String part1;
    private Integer part2;
    private Long part3;
    private BaseInfo baseInfo;    //可变对象
    @Override
    protected Product clone() throws CloneNotSupportedException {
        Product cloneProduct = (Product) super.clone();
        BaseInfo cloneBaseInfo = cloneProduct.getBaseInfo().clone();   //可变对象也需要克隆
        cloneProduct.setBaseInfo(cloneBaseInfo);   //给克隆产品设置克隆cloneBaseInfo对象
        return cloneProduct;
    }
    //用java的序列号和反序列化机制实现深拷贝
//   @Override
//    protected Product clone() throws CloneNotSupportedException {
//       ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
//       try{
//           //输出流:意思是将内存中的数据输出到硬盘上
//           ObjectOutputStream oos=new ObjectOutputStream(byteArrayOutputStream);
//           oos.writeObject(this);
//       }catch (IOException e){
//            e.printStackTrace();
//       }
//        // 输入流。意思是将硬盘里的数据输入到内存里，也就是内存读取文件。
//       //把内存中的数据给恢复出来
//       ByteArrayInputStream  byteArrayInputStream=new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
//
//       try{
//           ObjectInputStream ois=new ObjectInputStream(byteArrayInputStream);
//           Product product = (Product)ois.readObject();
//           return product;
//       }catch (IOException | ClassNotFoundException e) {
//           e.printStackTrace();
//       }
//       return null;
//    }

    public Product(){

    }
    public Product(String part1, Integer part2, Long part3, BaseInfo baseInfo) {
        this.part1 = part1;
        this.part2 = part2;
        this.part3 = part3;
        this.baseInfo=baseInfo;
    }

    @Override
    public String toString() {
        return  "Product{" +
                "part1='" + part1 + '\'' +
                ", part2='" + part2 + '\'' +
                ", part3='" + part3 + '\'' +
                ", baseInfo=" + baseInfo +
                '}';
    }
}
