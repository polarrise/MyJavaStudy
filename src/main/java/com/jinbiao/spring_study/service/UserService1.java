package com.jinbiao.spring_study.service;

import com.jinbiao.dynamic_proxy.jdk.UserManager;
import com.powersi.dao.PersonMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author wangjinbiao
 * @date 2023/2/14 14:30
 * @desc
 * Bean的生命周期:
 * UserService->无参构造方法->普通对象->依赖注入->初始化前(@PostConstruct)->
 * 初始化(InitializingBean)->初始化后(AOP)->代理对象->放入单例池Map->Bean
 */
@Component
public class UserService1 implements InitializingBean {

  @Autowired
  private OrderService orderService;

  private User admin;

  //Spring创建Bean的初始化前方法。Spring启动时候会去调用这里注解里面的方法
  @PostConstruct
  public void setAdminUser(){
  //mysql->User->this.admin赋值
    System.out.println("初始化前方法执行==");
  }

  //Spring在创建Bean的时候，在属性设置完了之后，如果这个Bean实现了InitializingBean接口，会调用这个方法
  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("初始化方法执行==");
  }

  public void test(){
    System.out.println("test方法执行==");
  }
}
