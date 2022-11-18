package com.jinbiao.Optional;


import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 认识Optional并使用:
 简单来说，Opitonal类就是Java提供的为了解决大家平时判断对象是否为空用
 会用 null!=obj 这样的方式存在的判断，从而令人头疼导致NPE（Null Pointer Exception 空指针异常），
 同时Optional的存在可以让代码更加简单，可读性跟高，代码写起来更高效.
 */
public class OptionalTest {
    public String test1(){
        String str1=null;
        if (null==str1){   //常规判空:
            return "str1为null";
        }
        return str1;
    }

    /**
     * orElse:如果str2不为空返回str2,  为空则返回other
     * Optional.orElse()方法(为空返回对象)
     * 常用方法之一，这个方法意思是如果包装对象为空的话，就执行orElse方法里的value，如果非空，则返回写入对象
     */
    public String orElse(){  //使用Optional判空:
        String str2=null;
        return Optional.ofNullable(str2).orElse("str2为null");
    }

    /**
     * 我们关于创建Optional对象的内部方法大致分析完毕，接下来也正式的进入Optional的学习与使用中
     */
    public void test3(){  //使用Optional判空:
        // 1、创建一个包装对象值为空的Optional对象
        Optional<String> optEmpty = Optional.empty();
        System.out.println(optEmpty==null);   //false,说明这个对象optEmpty已经存在(在堆内存中已经开辟了地址)

        //System.out.println(optEmpty.get());          //如果创建的optEmpty只完成了实例化,内部的value为null,调用get方法会报异常:No value present

       // 2、创建包装对象值非空的Optional对象
        Optional<String> optOf = Optional.of("optional");
        System.out.println("test3:"+optOf.get());

        // 3、创建包装对象值允许为空也可以不为空的Optional对象
        Optional<String> optOfNullable1 = Optional.ofNullable(null);
        //System.out.println(optOfNullable1.get());   //如果创建的optEmpty只完成了实例化,内部的value为null,调用get方法会报异常:No value present

        Optional<String> optOfNullable2 = Optional.ofNullable("optional");
        System.out.println("test3:"+optOfNullable2.get());

    }

    /**
     * Optional.get()方法(返回对象的值)
     * 如果value不为空则做返回，如果为空则抛出异常 "No value present"简单实例展示
     */
    public void get(){
        Person person=new Person();
        person.setAge(2);
        Person person1 = Optional.ofNullable(person).get();  //只要person不为null,则输出person对象,否则抛出异常 "No value present"
        System.out.println("get:"+person1);
    }

    /**
     * isPresent()方法就是会返回一个boolean类型值，如果对象不为空则为真，如果为空则false
     */
    public void isPresent(){
        Person person=new Person();
        person.setAge(2);
        if (Optional.ofNullable(person).isPresent()){
            //写不为空的逻辑
            System.out.println("不为空");
        }else{
            //写为空的逻辑
            System.out.println("为空");
        }
    }

    /**
     * Optional.ifPresent()方法(判读是否为空并返回函数)
     * 这个意思是如果对象非空，则运行函数体，源码:
     * public void ifPresent(Consumer<? super T> consumer) {
     *         //如果value不为空，则运行accept方法体
     *         if (value != null)
     *             consumer.accept(value);
     *     }
     */
    public void ifPresent(){
        Person person1=new Person();
        person1.setAge(1);
        Optional.ofNullable(person1).ifPresent(a->{
            if(a.getAge()==1){
                a.setName("王1岁");
            }
        });
        /**
         * 内部就是 1.lambda表达式 作为一个Consumer对象,传入到ifPresent方法中
         *         2.lambda表达式调用内部的方法体实现
         *         3.方法体(也就是接口里抽象方法的实现)。如果age为1，设置年龄为1
         */
        System.out.println("ifPresent1:"+person1);

        Person person2=new Person();
        person2.setAge(2);
        Consumer<Person> consumer = new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                if (person.getAge() == 2) {
                    person.setName("王2岁");
                }
            }
        };
        Optional.ofNullable(person2).ifPresent(consumer);
        System.out.println("ifPresent2:"+person2);
    }

    /**
     * Optional.orElseGet()方法(为空返回Supplier对象)  orElseGet(生产者接口)
     * 这个与orElse很相似，入参不一样，入参为Supplier对象，为空返回传入对象的.get()方法，如果非空则返回当前对象
     */
    public void orElseGet(){
        Person person=new Person();
        person.setAge(1);
        Optional<Supplier<Person>> sup=Optional.ofNullable(Person::new);
        //调用get()方法，此时才会调用对象的构造方法，即获得到真正对象
        Person person2 = Optional.ofNullable(person).orElseGet(sup.get());
        System.out.println("optionalTest:"+person2);
    }

    /**
     * Optional.orElseThrow()方法(为空返回异常)
     * 这个我个人在实战中也经常用到这个方法，方法作用的话就是如果为空，就抛出你定义的异常，如果不为空返回当前对象，在实战中所有异常肯定是要处理好的，为了代码的可读性
     */
    public void orElseThrow() throws MyException {
        //简单的一个查询
        Person person =null;
        Optional.ofNullable(person).orElseThrow(() -> new MyException("person为空异常"));
    }

    public void testZ(){
        Person person=new Person();
        person.setName("");
        person.setAge(2);
       //普通判断
        if(StringUtils.isNotBlank(person.getName())){
            //名称不为空执行代码块
        }
        //使用Optional做判断
        Optional.ofNullable(person).map(p -> p.getName()).orElse("name为空");
    }
    public static void main(String[] args) throws MyException {
        OptionalTest optionalTest = new OptionalTest();
        System.out.println("test1:"+optionalTest.test1());
        System.out.println("orElse2:"+optionalTest.orElse());
        System.out.println("===========");
        optionalTest.test3();
        optionalTest.get();
        optionalTest.isPresent();
        optionalTest.ifPresent();
        optionalTest.orElseGet();
        optionalTest.orElseThrow();

    }
    /**
     *  相似方法进行对比分析
     * 可能小伙伴看到这，没用用过的话会觉得orElse()和orElseGet()还有orElseThrow()很相似，map()和flatMap()好相似
     * 哈哈哈不用着急，都是从这一步过来的，我再给大家总结一下不同方法的异同点
     * orElse()和orElseGet()和orElseThrow()的异同点:方法效果类似，如果对象不为空，则返回对象，如果为空，则返回方法体中的对应参数，所以可以看出这三个方法体中参数是不一样的
     * orElse（T 对象）
     * orElseGet（Supplier < T >对象）
     * orElseThrow（异常）
     * 方法效果类似，对方法参数进行二次包装，并返回,入参不同
     * map（function函数）
     * flatmap（Optional< function >函数）
     * 具体要怎么用，要根据业务场景以及代码规范来定义，下面可以简单看一下我在实战中怎用使用神奇的Optional
     */
}

class Person {
    private String name;
    private Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class MyException extends Exception{
    public MyException(String message) {
        // 调用父类的有参构造函数
        super(message);
    }
}
