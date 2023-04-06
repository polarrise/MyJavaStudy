package com.powersi.component;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author：Jinbiao
 * @Date：2023/4/6 23:03
 * @Desc：对应3个对象的第三个，使用onApplicationEvent方法或者使用@EventListener注解的方法。
 * 概念
 * spring的事件机制是A调用一个发布事件的方法，就能自动调用B监听这个事件的方法，所以这个过程有3个对象：
 * 1. 事件本身，其实就是一个对象，这个对象对应的类要继承spring的ApplicationEvent，这样才能被spring识别。
 * 2. 发布事件的对象，一般都是使用ApplicationContext的publishEvent方法来发布；
 * 3. 接收（或者叫监听）事件的对象，使用onApplicationEvent方法或者使用@EventListener注解的方法；
 */

@Component
public class EventTestListener implements ApplicationListener<EventTest> {
    @Override
    public void onApplicationEvent(EventTest eventTest) {
        System.out.println("非注解中jfs今年" + eventTest.getAge() + "岁。");
    }
}
