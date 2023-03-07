package com.jinbiao.lambdas.Predicate;

import java.util.function.Predicate;

/**
 Predicate接口中有一个方法and,表示并且关系,也可以用于连接两个判断条件
 */
public class MyPredicateAnd {

    public static boolean validateStr(String str, Predicate<String> pre1, Predicate<String> pre2){
//        return pre1.test(str) && pre2.test(str);
        return pre1.and(pre2).test(str);
    }

    public static void main(String[] args) {
        String s = "abcdef";
        //完整写法:  main方法体/一般方法体写法:
        boolean a = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 5;
            }
        }.and(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("a");
            }
        }).test(s);
        System.out.println("方法体测试:"+a);



        //lambda表达式写法
        boolean b = validateStr(s,str->str.length()>5,str->str.contains("a"));
        System.out.println(b);
    }
}