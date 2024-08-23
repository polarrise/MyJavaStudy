package com.jinbiao.spring_study.initProcessTest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author WangJinbiao
 * @date 2023/2/14 21:54
 * @desc
 */
public class SpringInitProcessTestApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(InitProcessConfig.class);
        UserServiceInitProcess userServiceInitProcess = (UserServiceInitProcess)applicationContext.getBean("userServiceInitProcess");
        userServiceInitProcess.test();
        System.out.println(userServiceInitProcess.getAdmin());

        /**
         * 原型bean
         */
        UserServiceInitProcess userServiceInitProcess2 = SpringContextHolder.getPrototypeBean("userServiceInitProcess");
        System.out.println(userServiceInitProcess);
        System.out.println(userServiceInitProcess2);

        applicationContext.close();
    }
}
