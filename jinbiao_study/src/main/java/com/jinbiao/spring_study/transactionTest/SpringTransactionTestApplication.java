package com.jinbiao.spring_study.transactionTest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutionException;

/**
 * @author WangJinbiao
 * @date 2023/2/14 21:54
 * @desc
 */
public class SpringTransactionTestApplication {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JDBCConfig.class);
        UserServiceTransaction userServiceTransaction = (UserServiceTransaction)applicationContext.getBean("userServiceTransaction");

        // 编程式事务测试：
        // userServiceTransaction.programmingTransaction1();
        // userServiceTransaction.programmingTransaction2();

        // 声明式事务测试：
        // userServiceTransaction.test();
        // userServiceTransaction.test2();
        // userServiceTransaction.test3();
        // userServiceTransaction.test4();
        // userServiceTransaction.test6();
        // userServiceTransaction.test7();
        // userServiceTransaction.test8();
        // userServiceTransaction.test9();
        // userServiceTransaction.test10();


        userServiceTransaction.programmingTransaction11();

    }
}
