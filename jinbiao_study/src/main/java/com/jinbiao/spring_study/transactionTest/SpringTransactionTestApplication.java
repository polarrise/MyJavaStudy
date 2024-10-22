package com.jinbiao.spring_study.transactionTest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author WangJinbiao
 * @date 2023/2/14 21:54
 * @desc
 */
public class SpringTransactionTestApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JDBCConfig.class);
        UserServiceTransaction userServiceTransaction = (UserServiceTransaction)applicationContext.getBean("userServiceTransaction");

        // 编程式事务测试：
        // userServiceTransaction.programmingTransaction1();
        // userServiceTransaction.programmingTransaction2();

        // 声明式事务测试：
        // userServiceTransaction.test();
        // userServiceTransaction.test2();
        // userServiceTransaction.test3();
        userServiceTransaction.test4();
    }
}
