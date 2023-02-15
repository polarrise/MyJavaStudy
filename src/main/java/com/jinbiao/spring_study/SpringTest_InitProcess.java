package com.jinbiao.spring_study;

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
        applicationContext.close();
    }
}
