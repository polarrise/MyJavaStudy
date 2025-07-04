package com.jinbiao.spring_study.transactionTest.v2;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: rise
 * @date: 2025-01-19 14:59:23
 * @Description: 事务处理层
 * @Copyright: www.xshopee.com All rights reserved.
 * 注意：本内容仅限于深圳市多米诺跨境电商有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class TestTransactionBean {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private TestTransactionInBiz testTransactionInBiz;

    /**
     * 测试事务失效3：方法内部调用，发生this调用问题，
     */
    public void callback() {
        jdbcTemplate.execute("insert into jinbiao_order(order_sn) values ('250105PPC0YFM6')");

        // 生成担保单和担保流程分开：担保流程业务异常、生成的担保单未回滚
        testTransactionInBiz.handleGuarantee();
    }
}
