package com.powersi.component;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @Author：Jinbiao
 * @Date：2023/4/6 23:04
 * @Desc：接收事件的对象--使用@EventListener注解
 */


@Component
public class EventTestListenerByAnnotation {
    @EventListener
    public void listener1(EventTest event) {
        System.out.println("注解中jfs今年" + event.getAge() + "岁。");
    }
}
