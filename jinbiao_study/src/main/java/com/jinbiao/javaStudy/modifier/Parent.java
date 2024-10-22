package com.jinbiao.javaStudy.modifier;

public class Parent {

    protected void test(){
        System.out.println("父类的保护方法");
    }

    void test2(){
        System.out.println("父类的默认方法执行了..");
    }

    public final void test3(){
        System.out.println("父类的final方法执行了..");
    }
}
