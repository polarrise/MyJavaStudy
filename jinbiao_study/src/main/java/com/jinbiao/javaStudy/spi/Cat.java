package com.jinbiao.javaStudy.spi;

/**
 * @Author：Jinbiao
 * @Date：2023/5/26 21:49
 * @Desc：
 */
public class Cat implements Animal{
    @Override
    public void say() {
        System.out.println("miao miao miao");
    }
}
