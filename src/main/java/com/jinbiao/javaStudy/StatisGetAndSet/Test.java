package com.jinbiao.javaStudy.StatisGetAndSet;

/**
 * private关键字的作用
 * 被private修饰的属性，只能在本类中访问,想要在外部访问私有属性,我们需要提供公有方法来间接访问。
 * 上面的例子就是在测试类（非Target类本身内部）中访问Target类的私有属性，结果不能直接访问。
 * 所以private的作用就是对类的属性进行“封装”。
 * ————————————————
 */
public class Test {
    public static void main(String[] args) {
        Person person1=new Person();
//        target.age;           被private修饰的属性，只能在本类中访问,想要在外部访问私有属性,我们需要提供公有方法来间接访问。
        person1.setName("WangJinbiao");
        person1.setAge(23);
        person1.setSex("男");
        person1.evl.add("帅哥罢了");
        System.out.println(person1);

        Person person2=new Person();
        person2.setName("晚风");           //一个对象如果对静态属性name做了改变
        System.out.println(person2);

        Person person3=new Person();
        System.out.println(person3);     //其他的对象的name属性都会受到影响。

        //静态属性,静态方法,静态代码块 只会在类首次加载的时候初始化一次。 是属于类所有
        //我们发现，给age属性加了static关键字之后，Person对象就不再拥有age属性了，age属性会统一交给Person类去管理，
        // 即多个Person对象只会对应一个age属性，一个对象如果对age属性做了改变，其他的对象都会受到影响。
    }
}
