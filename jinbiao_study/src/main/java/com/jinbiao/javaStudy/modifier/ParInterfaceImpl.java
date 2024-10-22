package com.jinbiao.javaStudy.modifier;

public class ParInterfaceImpl implements ParInterface{
    @Override
    public void test() {
        System.out.println("ParInterfaceImpl执行了...");
    }

    protected void test2() {
        System.out.println("ParInterfaceImpl的prodected方法执行了...");
    }
}
