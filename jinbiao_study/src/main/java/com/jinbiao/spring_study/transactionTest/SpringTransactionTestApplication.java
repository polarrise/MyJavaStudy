package com.jinbiao.spring_study.transactionTest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author WangJinbiao
 * @date 2023/2/14 21:54
 * @desc
 */
public class SpringTransactionTestApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TransactionConfig.class);
        UserServiceTransaction userServiceTransaction = (UserServiceTransaction)applicationContext.getBean("userServiceTransaction");
        //userServiceTransaction.test1();
        //userServiceTransaction.test2();
        userServiceTransaction.test22();
    }
}
