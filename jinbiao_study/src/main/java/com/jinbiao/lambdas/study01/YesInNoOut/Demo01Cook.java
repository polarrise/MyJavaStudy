package com.jinbiao.lambdas.study01.YesInNoOut;

/**
 * （参数列表）->{一些重要方法的代码};
 * ():接口中抽象方法的参数列表，没有参数，就空着；有参数就写出参数，多个参数用逗号分隔。
 * ->：传递：把参数传递给方法体{}             --接口里面的抽象方法有参,所以 ->之前就一定要写参数
 * {}:重写接口的抽象方法的方法体              --相当于重写了接口的抽象方法。  只写方法体就好了，方法名省略了
 *
 * 箭头操作符的左侧对应接口中参数列表(lambda表达式的参数列表)，
 * 箭头右侧为该抽象方法的实现（lambda表达式所需执行的功能）。
 */
public class Demo01Cook {
    public static void invokeCook(Cook cook){
        String str1="hello";
        String str2=" world!";
        cook.makeFood(str1,str2);   //调用接口里面的方法
    }

    public static void main(String[] args) {
        /**怎么理解?
         * 1.main函数里面当我们正常通过类名Demo01Cook调用静态方法invokeCook时候,需要传入一个Cook类型的对象,接口又不能直接new(不能直接传接口对象)
         * 我们就只能以匿名内部类的形式传入
         * 2.正确把Cook对象的匿名内部类传入之后,开始执行String str1="hello";     String str2=" world!";
         *
         */
        Demo01Cook.invokeCook(new Cook(){
            @Override
            public void makeFood(String str1, String str2) {
                System.out.println(str1+str2);
            }
        });

        //使用Lambda
        invokeCook((a,b)->{                       //():有参数就写出参数，多个参数用逗号分隔。；  {}:重写接口的抽象方法的方法体
            System.out.println(a+b);
        });

        //优化lambda
        invokeCook((a,b)-> System.out.println(a+b));   //{一些代码} ：如果{}中的代码只有一行，无论是否有返回值，都可以省略（{}，return，分号）
    }
}
/**
 lambda表达式:
 lambda表达式为匿名内部类的简写，类似于匿名内部类的语法糖；但又区别于匿名内部类(后文会讲解)。
 匿名内部类特点：
 基于多态（多数基于接口编程）
 实现类无需名称
 允许多个抽象方法
*/