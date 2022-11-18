package com.jinbiao.lambdas.study01;

/**
 * （参数列表）->{一些重要方法的代码};
 * ():接口中抽象方法的参数列表，没有参数，就空着；有参数就写出参数，多个参数用逗号分隔。
 * ->：传递：把参数传递给方法体{}             --接口里面的抽象方法有参,所以 ->之前就一定要写参数
 * {}:重写接口的抽象方法的方法体              --相当于重写了接口的抽象方法。  只写方法体就好了，方法名省略了
 *
 * 箭头操作符的左侧对应接口中参数列表(lambda表达式的参数列表)，
 * 箭头右侧为该抽象方法的实现（lambda表达式所需执行的功能）。
 */
public class MyLambda {
    public static void main(String[] args) {
        new Thread(new Runnable() {   //Thread线程类的构造方法需要传入一个Rnnable接口,需要实现接口里面的run方法
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"新线程创建了");
            }
        }).start();

        //使用Lambda
        new Thread(()->{                 //():接口中抽象方法的参数列表，没有参数，就空着；有参数就写出参数，多个参数用逗号分隔。
            System.out.println(Thread.currentThread().getName()+"新线程创建了");
        }).start();

        //优化lambda
        new Thread(()->System.out.println(Thread.currentThread().getName()+"新线程创建了")).start();
    }
}
