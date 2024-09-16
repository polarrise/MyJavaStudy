package com.powersi.controller;

import com.powersi.component.MailEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：Jinbiao
 * @Date：2023/4/6 22:57
 * @Desc：对应3个对象的第二个，使用ApplicationContext的publishEvent方法。
 * 概念
 * spring的事件机制是A调用一个发布事件的方法，就能自动调用B监听这个事件的方法，所以这个过程有3个对象：
 * 1. 事件本身，其实就是一个对象，这个对象对应的类要继承spring的ApplicationEvent，这样才能被spring识别。
 * 2. 发布事件的对象，一般都是使用ApplicationContext的publishEvent方法来发布；
 * 3. 接收（或者叫监听）事件的对象，使用onApplicationEvent方法或者使用@EventListener注解的方法；
 */
@RestController
@RequestMapping("/testPubEvent")
@Slf4j
public class EventTestController {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/pub-event")
    public void pub() {
        log.info("业务逻辑处理中....");
        applicationContext.publishEvent(new MailEvent(this));
    }

}
