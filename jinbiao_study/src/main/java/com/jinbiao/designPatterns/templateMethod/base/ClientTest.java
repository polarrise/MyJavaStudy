package com.jinbiao.designPatterns.templateMethod.base;

/**
 * @Author：Jinbiao
 * @Date：2023/3/6 18:39
 * @Desc：客户端测试类
 */
public class ClientTest {

    public static void main(String[] args) {
        AbstractClass concreteClass1 = new ConcreteClass1();
        AbstractClass concreteClass2 = new ConcreteClass2();
        concreteClass1.temllateMethod();
        concreteClass2.temllateMethod();
    }
}
