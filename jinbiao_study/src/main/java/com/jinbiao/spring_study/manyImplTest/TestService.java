package com.jinbiao.spring_study.manyImplTest;

import com.jinbiao.spring_study.transactionTest.User;

import java.util.List;

/**
 * @author WangJinbiao
 * @date 2023/2/14 23:58
 * @desc
 */
public interface TestService {

    List<User> findAll();
}
