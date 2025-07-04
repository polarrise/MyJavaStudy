package com.jinbiao.spring_study.transactionTest.v2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;

/**
 * @author wangjinbiao
 * @date 2023/2/15 11:46
 * @desc Spring事务的大概执行流程：
 * <p>
 * class UserServiceTransactionProxy extends UserServiceTransaction{
 * <p>
 * UserServiceTransaction target;  //目标对象
 * <p>
 * public void testTransaction(){
 * //方法上加了@Transactional注解
 * //1.事务管理器新建一个数据库联系conn
 * //2.conn.autocommit = false
 * //3.target.test()   //执行sql，操作数据库逻辑
 * //4.上述发生异常则conn.rollback, 成功则conn.commit();
 * }
 * }
 */
@Slf4j
@Service
public class TestTransactionInService {

    @Resource
    private TestTransactionInService userServiceTransaction;

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 测试事务失效3：方法内部调用，发生this调用问题，
     */
    public void callback() throws Exception {
        jdbcTemplate.execute("insert into jinbiao_order(order_sn) values ('250105PPC0YFM6')");
        // 生成担保单和担保流程分开：担保流程业务异常、生成的担保单未回滚,打印事务方法名称
        // ((TestTransactionInService) AopContext.currentProxy()).getGuaranteeOrderSuccess();
        // ((TestTransactionInService) AopContext.currentProxy()).orderGuaranteeException();

        // 生成担保单和担保流程分开：担保流程业务异常、生成的担保单未回滚
        ((TestTransactionInService) AopContext.currentProxy()).handleGuarantee();
    }

    @Transactional(rollbackFor = Exception.class)
    public void getGuaranteeOrderSuccess(){
        // 展示当前事务名称
        showCurrentTransaction();

        jdbcTemplate.execute("insert into jinbiao_user values (3,'normalMethod1','wang1234..','10086','小程序')");
    }

    @Transactional(rollbackFor = Exception.class)
    public void getGuaranteeOrderException() throws Exception {
        jdbcTemplate.execute("insert into jinbiao_user values (3,'exceptionMethod1','wang1234..','10086','小程序')");
        throw new Exception("生成担保单异常啦,请回滚...");
    }

    @Transactional(rollbackFor = Exception.class)
    public void orderGuaranteeSuccess(){
        jdbcTemplate.execute("insert into jinbiao_user values (3,'normalMethod2','wang1234..','10086','小程序')");
    }

    @Transactional(rollbackFor = Exception.class)
    public void orderGuaranteeException() throws Exception {
        // 展示当前事务名称
        showCurrentTransaction();

        jdbcTemplate.execute("insert into jinbiao_user values (4,'exceptionMethod2','wang1234..','10086','小程序')");
        throw new Exception("订单担保异常啦,请回滚...");
    }

    /**
     * 只要 handleGuarantee 方法本身有 @Transactional 注解，那么它内部调用的所有方法，即使没有 @Transactional 注解，
     * 都会参与到事务中。这个行为是由 Spring 的 事务传播机制决定的。
     *
     * 其他情况ps:这里改成private，事务会失效、
     */
    @Transactional(rollbackFor = Exception.class)
    public void handleGuarantee() throws Exception {
        // 生成担保单
        // getGuaranteeOrderSuccess();
        getGuaranteeOrderSuccess2();

        // 担保流程
        // orderGuaranteeException();
        orderGuaranteeException2();
    }

    @Transactional(rollbackFor = Exception.class)
    public void getGuaranteeOrderSuccess2(){
        // 展示当前事务名称
        showCurrentTransaction();

        jdbcTemplate.execute("insert into jinbiao_user values (3,'normalMethod1','wang1234..','10086','小程序')");
    }

    @Transactional(rollbackFor = Exception.class)
    public void orderGuaranteeException2() throws Exception {
        // 展示当前事务名称
        showCurrentTransaction();

        jdbcTemplate.execute("insert into jinbiao_user values (4,'exceptionMethod2','wang1234..','10086','小程序')");

        handleOtherBiz();

        throw new Exception("订单担保异常啦,请回滚...");
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