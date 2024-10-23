package com.jinbiao.javaStudy.modifier;

public class Target {

    private void test1(){
        System.out.println("目标对象的私有方法执行了..");
    }

    protected void test2(){
        System.out.println("目标对象的保护方法执行了..");
    }

    void test3(){
        System.out.println("目标对象的默认方法执行了..");
    }

    public final void test4(){
        System.out.println("目标对象的final方法执行了..");
    }

    public static void test5(){
        System.out.println("目标类的static方法执行了..");
    }
    public void test6(){
        System.out.println("目标对象的public方法执行了..");
    }
}
