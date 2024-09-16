package com.powersi.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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
@Slf4j
public class MailEventListener {

    @Autowired
    private ThreadPoolTaskExecutor commonPool;

    // commonPool是我自定义线程池命名的bean名称
    @Async("commonPool")
    @EventListener
    public void listener1(MailEvent mailEvent) {
        log.info("侦听器1侦听到邮件事件啦...当前执行线程：{}",Thread.currentThread().getName());
        mailEvent.sendMail();
        throw new RuntimeException("侦听器1异常啦....");
    }

    @Async("commonPool")
    @EventListener
    public void listener3(MailEvent mailEvent) {
        log.info("侦听器3侦听到邮件事件啦...当前执行线程：{}",Thread.currentThread().getName());
        mailEvent.sendMail();
    }

    @Async("commonPool")
    @EventListener
    public void listener2(MailEvent mailEvent) {
        log.info("侦听器2侦听到邮件事件啦...当前执行线程：{}",Thread.currentThread().getName());
        mailEvent.sendMail();
    }
}
