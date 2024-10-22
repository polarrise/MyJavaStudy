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
    public void insertRole() {
        jdbcTemplate.execute("insert into jinbiao_role values (1,'admin')");
    }
}
