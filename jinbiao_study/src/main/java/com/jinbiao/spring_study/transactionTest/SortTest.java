package com.jinbiao.spring_study.transactionTest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author: rise
 * @date: 2025-01-15 16:41:58
 * @Description: 排序测试
 * @Copyright: www.xshopee.com All rights reserved.
 * 注意：本内容仅限于深圳市多米诺跨境电商有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Slf4j
@Service
public class SortTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void test() {


        for (int i = 0; i < 10000; i++) {

            jdbcTemplate.execute("insert into sort_test1 values (1,'rise1','wang1234..','10086','小程序')");
        }
    }

}
