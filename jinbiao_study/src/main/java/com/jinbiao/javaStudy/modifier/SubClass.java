package com.jinbiao.javaStudy.modifier;

public class SubClass extends Parent {

    protected void subTest(){
        System.out.println("子类的测试方法执行...");
    }

    public static void main(String[] args) {

        Parent par = new Parent();
        par.test();

        SubClass sub = new SubClass();
        sub.test();
    }
}
