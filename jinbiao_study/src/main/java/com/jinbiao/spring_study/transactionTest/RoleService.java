package com.jinbiao.spring_study.transactionTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void insertRole1() {
        jdbcTemplate.execute("insert into jinbiao_role values (1,'admin')");
    }

    @Transactional
    public void insertRole2() {
        jdbcTemplate.execute("insert into jinbiao_role values (2,'admin')");
        throw new RuntimeException("子线程事务方法出错啦...");
    }
}
