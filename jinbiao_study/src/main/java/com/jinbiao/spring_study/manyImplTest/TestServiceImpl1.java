package com.jinbiao.spring_study.manyImplTest;

import com.jinbiao.spring_study.transactionTest.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author：Jinbiao
 * @Date：2023/3/3 23:28
 * @Desc：
 */
@Component
public class TestServiceImpl1 implements TestService {
    @Override
    public List<User> findAll() {
        System.out.println("testService1的findAll方法执行了===");
        return null;
    }
}
