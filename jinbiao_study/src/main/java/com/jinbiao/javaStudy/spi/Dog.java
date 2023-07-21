package com.jinbiao.javaStudy.spi;

/**
 * @Author：Jinbiao
 * @Date：2023/5/26 21:49
 * @Desc：
 */
public class Dog implements Animal{
    @Override
    public void say() {
        System.out.println("wang wang wang");
    }
}
