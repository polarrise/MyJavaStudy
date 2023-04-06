package com.powersi.component;

import org.springframework.context.ApplicationEvent;

/**
 * @Author：Jinbiao
 * @Date：2023/4/6 22:59
 * @Desc：对应3个对象中的第一个，对象的类需要实现spring的ApplicationEvent。
 *
 * 概念
 * spring的事件机制是A调用一个发布事件的方法，就能自动调用B监听这个事件的方法，所以这个过程有3个对象：
 * 1. 事件本身，其实就是一个对象，这个对象对应的类要继承spring的ApplicationEvent，这样才能被spring识别。
 * 2. 发布事件的对象，一般都是使用ApplicationContext的publishEvent方法来发布；
 * 3. 接收（或者叫监听）事件的对象，使用onApplicationEvent方法或者使用@EventListener注解的方法；
 */
public class EventTest extends ApplicationEvent {

    private final int age;

    public EventTest(Object source, int age) {
        super(source);
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}

