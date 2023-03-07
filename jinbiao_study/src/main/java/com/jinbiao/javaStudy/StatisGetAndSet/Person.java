package com.jinbiao.javaStudy.StatisGetAndSet;


import java.util.ArrayList;
import java.util.List;

//没有实现set和get方法的实体类
public class Person {
    private static String name ;  //姓名为私有属性
    private static String sex;    //性别为私有属性
    private static int age ;       //年龄为私有属性
    static {
        System.out.println("父类静态代码块执行==");
    }
    {
        System.out.println("父类代码块执行==");
    }
    public Person(){
    System.out.println("父类构造方法执行==");
    }
    public List<String>evl=new ArrayList<>();         //评价

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", evl=" + evl +
                '}';
    }

    public static void main(String[] args) {
        Person person=new Person();
        person.age=20;
        System.out.println(person.age);
    }
}
