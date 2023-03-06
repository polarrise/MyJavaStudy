package com.jinbiao.designPatterns.templateMethod.base;

/**
 * @Author：Jinbiao
 * @Date：2023/3/6 18:37
 * @Desc：具体模板类2
 */
public class ConcreteClass2 extends AbstractClass{

    /**
     * 实现基本方法
     */
    @Override
    protected void doSomething() {
        //业务逻辑处理:
        System.out.println("ConcreteClass2的doSomething方法执行===");
    }

    /**
     * 实现基本方法
     */
    @Override
    protected void doAnything() {
        //业务逻辑处理:
        System.out.println("ConcreteClass2的doAnything方法执行===");
    }
}
