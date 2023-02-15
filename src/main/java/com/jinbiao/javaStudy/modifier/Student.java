package com.jinbiao.javaStudy.modifier;

/**
 * @author com.jinbiao
 * @date 2022/1/7
 * @apiNote
 */
public class Student {

    public void test() {
        /**
         ，我们需要知道的一点，final修饰的变量为编译期常量，所以只需要在变量b前面新增final关键字，这会使得在编译阶段c也变成了编译期常量，
         所以保证了 a 与 c相等，但 d 无法在编译期被确定，所以 b 与 e 的内存地址是不相同的。
         **/
        String a = "hello2";
        final String b = "hello";       //final修饰的变量为编译期常量
        String d = "hello";
        String c = b + "2";
        String e = d + "2";
        System.out.println(b==d);        //true
        System.out.println(a==c);        //true
        System.out.println(a==e);        //false

    }

    public static void main(String[] args) {
        new Student().test();
    }
}
