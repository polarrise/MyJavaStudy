package com.jinbiao.spring_study.transactionTest.v2;

import org.springframework.aop.framework.AopContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;

/**
 * @author: rise
 * @date: 2025-01-19 14:05:40
 * @Description: 事务处理业务层
 * @Copyright: www.xshopee.com All rights reserved.
 * 注意：本内容仅限于深圳市多米诺跨境电商有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class TestTransactionInBiz {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private TestTransactionInBiz testTransactionInBiz;

    @Resource
    private BlcOrderGuaranteeService blcOrderGuaranteeService;

    @Transactional(rollbackFor = Exception.class)
    public void getGuaranteeOrderSuccess(){
        // 展示当前事务名称
        showCurrentTransaction();

        jdbcTemplate.execute("insert into jinbiao_user values (3,'生成担保单成功','wang1234..','10086','小程序')");
    }

    @Transactional(rollbackFor = Exception.class)
    public void getGuaranteeOrderException() throws Exception {
        // 展示当前事务名称
        showCurrentTransaction();

        jdbcTemplate.execute("insert into jinbiao_user values (3,'生成担保单异常','wang1234..','10086','小程序')");
        throw new Exception("生成担保单异常啦,请回滚...");
    }

    @Transactional(rollbackFor = Exception.class)
    public void orderGuaranteeSuccess(){
        // 展示当前事务名称
        showCurrentTransaction();
        jdbcTemplate.execute("insert into jinbiao_user values (3,'订单担保成功','wang1234..','10086','小程序')");
    }

    @Transactional(rollbackFor = Exception.class)
    public void orderGuaranteeException() throws Exception {
        // 展示当前事务名称
        showCurrentTransaction();

        jdbcTemplate.execute("insert into jinbiao_user values (4,'订单担保异常','wang1234..','10086','小程序')");
        throw new Exception("订单担保异常啦,请回滚...");
    }

    /**
     * 只要 handleGuarantee 方法本身有 @Transactional 注解，那么它内部调用的所有方法，即使没有 @Transactional 注解，
     * 都会参与到事务中。这个行为是由 Spring 的 事务传播机制决定的。
     *
     * 其他情况ps:这里改成private，事务会失效、
     */
    @Transactional(rollbackFor = Exception.class)
    public void handleGuarantee() {
        // 生成担保单
        // getGuaranteeOrderException();
        // getGuaranteeOrderSuccess();
        getGuaranteeOrderSuccess2();

        // 担保流程
        // orderGuaranteeException();

        orderGuaranteeException2("1");
        // orderGuaranteeSuccess();
    }


    public void getGuaranteeOrderSuccess2(){
        // 展示当前事务名称
        showCurrentTransaction();

        jdbcTemplate.execute("insert into jinbiao_user values (3,'订单担保成功','wang1234..','10086','小程序')");
    }


    public void orderGuaranteeException2(String status) {
        // 展示当前事务名称
        showCurrentTransaction();

        try {
            if("1".equals(status) || "3".equals(status)){
                // 调用担保逻辑
                blcOrderGuaranteeService.invokeGuarantee();
            }
        } catch (Exception e){
            testTransactionInBiz.saveGuaranteeHanOut1();
            throw new RuntimeException("解冻失败, 回滚事务...");
        }

        try {
            if("RRAOC".equals(status)){
                jdbcTemplate.execute("insert into jinbiao_user values (4,'订单担保异常','wang1234..','10086','小程序')");
                throw new Exception("解冻流程异常啦,请回滚...");
            }
        } catch (Exception e){
            testTransactionInBiz.saveGuaranteeHanOut2();
            throw new RuntimeException("解冻失败, 回滚事务...");
        }
        handleOtherBiz();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveGuaranteeHanOut1() {
        // 展示当前事务名称.
        showCurrentTransaction();

        jdbcTemplate.execute("insert into jinbiao_exception(error) values ('订单担保流程异常')");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveGuaranteeHanOut2() {
        // 展示当前事务名称
        showCurrentTransaction();

        jdbcTemplate.execute("insert into jinbiao_exception(error) values ('订单解冻流程异常')");
    }

    private void handleOtherBiz(){
        jdbcTemplate.execute("insert into jinbiao_user values (5,'handleOtherBiz','wang1234..','10086','小程序')");
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
