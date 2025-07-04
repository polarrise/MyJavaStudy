package com.jinbiao.spring_study.transactionTest.v2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;

/**
 * @author: rise
 * @date: 2025-01-19 19:52:59
 * @Description: 订单担保Service
 * @Copyright: www.xshopee.com All rights reserved.
 * 注意：本内容仅限于深圳市多米诺跨境电商有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Slf4j
@Service
public class BlcOrderGuaranteeService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 模拟调用担保方法
     */
    public void invokeGuarantee() throws Exception {
        log.info("调用担保逻辑......");
        showCurrentTransaction();
        jdbcTemplate.execute("insert into jinbiao_user values (6,'担保逻辑','wang1234..','10086','小程序')");
        throw new Exception("冻结失败, 回滚事务..");
    }

    /**
     * 展示当前事务名称
     */
    private void showCurrentTransaction(){
        // 获取当前事务是否存在
        boolean hasTransaction = TransactionSynchronizationManager.isActualTransactionActive();
        System.out.println("getGuaranteeOrderSuccess - 当前是否有事务: " + hasTransaction);

        if (hasTransaction) {
            String transactionName = TransactionSynchronizationManager.getCurrentTransactionName();
            System.out.println("getGuaranteeOrderSuccess - 当前事务名称: " + transactionName);
        }
    }
}
