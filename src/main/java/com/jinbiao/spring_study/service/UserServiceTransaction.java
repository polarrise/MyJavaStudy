package com.jinbiao.spring_study.service;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangjinbiao
 * @date 2023/2/15 11:46
 * @desc
 * Spring事务的大概执行流程：
 *
 * class UserServiceTransactionProxy extends UserServiceTransaction{
 *
 *   UserServiceTransaction target;  //目标对象
 *
 *   public void testTransaction(){
 *     //方法上加了@Transactional注解
 *    //1.事务管理器新建一个数据库联系conn
 *    //2.conn.autocommit = false
 *    //3.target.test()   //执行sql，操作数据库逻辑
 *    //4.上述发生异常则conn.rollback, 成功则conn.commit();
 *   }
 * }
 */
@Component
public class UserServiceTransaction {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * 方式1：新建一个代理对象userServiceTransactionBase去保证事务不失效
   */
  @Autowired
  private UserServiceTransactionBase userServiceTransactionBase;


  /**
   * 测试内部调用其他的方法让事务不失效的情况：2.自己注入自己。因为Spring容器里面单例池获取出来的是代理对象userServiceTransaction
   */
  @Autowired
  private UserServiceTransaction userServiceTransaction;

  /**
   * 测试发生异常事务回滚：
   * attention、attention、attention注意三遍：如果在JDBCConfig上没加@Configuration,事务是不会生效的！！！
   */
  @Transactional
  public void testTransaction(){
    jdbcTemplate.execute("insert into xiaofa_user values (3,'rise3','wang1234..','17673635094','快手')");
    throw new NullPointerException();
  }

  /**
   * 测试事务失效，1：发生this(目标对象)调用问题：
   * 代理对象会对加了@Transactional的方法做额外的逻辑
   */
  @Transactional
  public void test1(){
    jdbcTemplate.execute("insert into xiaofa_user values (3,'rise3','wang1234..','17673635094','快手')");
    this.a();   //this对象是目标对象不是代理对象，所以不会被代理到，造成a()上面的事务注解会失效
  }

  /**
   * Propagation.NEVER：以非事务方式执行，如果存在事务，则抛出异常
   */
  @Transactional(propagation = Propagation.NEVER)
  public void a(){
    jdbcTemplate.execute("insert into xiaofa_user values (5,'rise5','wang1234..','17673635094','快手')");
  }

  /**
   * 测试内部调用其他的方法让事务不失效的情况：1.再新建一个代理对象userServiceTransactionBase去调用a()
   */
  @Transactional
  public void test2(){
    jdbcTemplate.execute("insert into xiaofa_user values (3,'rise3','wang1234..','17673635094','快手')");
    userServiceTransactionBase.a();   //报错 Existing transaction found for transaction marked with propagation 'never'
  }

  /**
   * 测试内部调用其他的方法让事务不失效的情况：2.自己注入自己。因为Spring容器里面单例池获取出来的是代理对象userServiceTransaction
   */
  @Transactional
  public void test22(){
    jdbcTemplate.execute("insert into xiaofa_user values (3,'rise3','wang1234..','17673635094','快手')");
    userServiceTransaction.a();   //报错 Existing transaction found for transaction marked with propagation 'never'
  }

}
