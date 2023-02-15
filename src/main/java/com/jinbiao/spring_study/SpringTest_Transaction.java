package com.jinbiao.spring_study;

import com.jinbiao.spring_study.AppConfig;
import com.jinbiao.spring_study.service.UserServiceInitProcess;
import com.jinbiao.spring_study.service.UserServiceTransaction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author WangJinbiao
 * @date 2023/2/14 21:54
 * @desc
 */
public class SpringTest_Transaction {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        UserServiceTransaction userServiceTransaction = (UserServiceTransaction)applicationContext.getBean("userServiceTransaction");
        //userServiceTransaction.test1();
        //userServiceTransaction.test2();
        userServiceTransaction.test22();
    }
}
