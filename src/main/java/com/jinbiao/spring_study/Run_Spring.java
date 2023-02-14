package com.jinbiao.spring_study;

import com.jinbiao.spring_study.service.UserService1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author WangJinbiao
 * @date 2023/2/14 21:54
 * @desc
 */
public class Run_Spring {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService1 userService1 = (UserService1)applicationContext.getBean("userService1");
        userService1.test();
        System.out.println(userService1.getAdmin());
    }
}
