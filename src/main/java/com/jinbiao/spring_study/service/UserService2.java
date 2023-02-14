package com.jinbiao.spring_study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author wangjinbiao
 * @date 2023/2/14 14:30
 * @desc Spring函数的推断：
 * Bean的生命周期:
 * UserService->无参构造方法->普通对象->依赖注入->初始化前(@PostConstruct)->
 * 初始化(InitializingBean)->初始化后(AOP)->代理对象->放入单例池Map->Bean
 *
 * Spring构造方法的推断:
 * 1.如果没有构造方法默认就用无参构造方法，如果既有有参构造方法又有无参的构造方法，用无参构造方法
 * 2.如果只有多个有参的构造方法，那么Spring创建这个Bean会报错：java.lang.NoSuchMethodException异常
 * 3.如果有多个有参的构造方法，可以在构造方法上面加Auwowired注解，告诉Spring哪个构造方法构造对象
 */
@Component
public class UserService2 {

  @Autowired
  private OrderService orderService;

  //public UserService2(){
  //  System.out.println("无参构造执行=====");
  //  this.orderService = orderService;
  //}

  /**
   * 构造方法推断的简单原理：
   * @param orderService2
   * 1.有三个Bean(orderService、orderService1、orderService2),使用其中之前都不会报错
   * 2.使用orderService123会报错，Spring先通过类型找打有三个Bean,然后通过名字找不到就报错：Caused by: org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.jinbiao.spring_study.service.OrderService' available: expected single matching bean but found 3: orderService,orderService1,orderService2
   * 3.如果把AppConfig里面的两个手动注册的Bean注释掉，使用orderService123就不会报错
   * Spring构造方法推断,如果byType推断出有多个Bean，再byName根据bean名称去取bean
   * 如果byType只推断出一个bean，那么就使用这个bean去构造
   */
  @Autowired  //告诉Spring去使用哪个构造方法构造对象
  public UserService2(OrderService orderService2){ //byType->byName  Map<"orderService",OrderService对象>
    System.out.println("一个参数的有参构造执行=====");
    this.orderService = orderService2;
  }

  public UserService2(OrderService orderService,OrderService orderService1){
    this.orderService = orderService;
    System.out.println("UserService2中有两个参数的有参构造执行=====");
  }

  public void test(){
    System.out.println("UserService2的test方法执行===");
  }


}
