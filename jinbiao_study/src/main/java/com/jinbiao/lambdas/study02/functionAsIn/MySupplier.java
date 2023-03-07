package com.jinbiao.lambdas.study02.functionAsIn;

import java.util.function.Supplier;

/**
 * 提供类型：Supplier<T>接口
 * 特点：只出不进，作为方法/构造参数、方法返回值
 * java.util.function.Supplier<T>接口仅包含一个无参的方法：T get()。
 * 用来获取一个泛型参数指定类型的对象数据。
 * Supplier<T>接口被称之为生产型接口,指定接口的泛型是什么类型,那么接口中的get方法就会生产什么类型的数据
 */
public class MySupplier {
    public static String getString(Supplier<String> supplier){
        return supplier.get();
    }

    public static void main(String[] args) {
        getString(new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("null");
                return null;
            }
        });

        String s = getString(()->"Eason");
        System.out.println(s);
    }
}
