package com.jinbiao.spring_study;

import com.jinbiao.spring_study.service.SpringContextHolder;
import com.jinbiao.spring_study.service.UserServiceInitProcess;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author WangJinbiao
 * @date 2023/2/14 21:54
 * @desc
 */
public class SpringTest_InitProcess {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
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
