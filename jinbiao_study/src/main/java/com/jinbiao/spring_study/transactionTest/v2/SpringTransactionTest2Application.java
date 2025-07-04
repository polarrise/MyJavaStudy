package com.jinbiao.spring_study.transactionTest.v2;

import com.jinbiao.spring_study.transactionTest.JDBCConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author WangJinbiao
 * @date 2023/2/14 21:54
 * @desc
 */
public class SpringTransactionTest2Application {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(JDBCConfig.class);

        // 测试事务在serice处理
        // TestTransactionInService testTransactionInService = (TestTransactionInService)applicationContext.getBean("testTransactionInService");
        // testTransactionInService.callback();

        // 测试事务统一在biz处理
        TestTransactionBean testTransactionBean = (TestTransactionBean)applicationContext.getBean("testTransactionBean");
        testTransactionBean.callback();
    }
}
