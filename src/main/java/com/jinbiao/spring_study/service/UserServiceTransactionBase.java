package com.jinbiao.spring_study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangjinbiao
 * @date 2023/2/15 14:38
 * @desc
 */
@Component
public class UserServiceTransactionBase {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Transactional(propagation = Propagation.NEVER) //以非事务方式执行，如果存在事务，则抛出异常
  public void a(){
    jdbcTemplate.execute("insert into xiaofa_user values (5,'rise5','wang1234..','17673635094','快手')");
  }
}
