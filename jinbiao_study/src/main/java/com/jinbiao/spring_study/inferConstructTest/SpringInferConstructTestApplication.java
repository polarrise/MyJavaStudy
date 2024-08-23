package com.jinbiao.spring_study.inferConstructTest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author wangjinbiao
 * @date 2023/2/14 14:11
 * @desc
 *
 * Spring注册bean的方式。
 * 1.加@Controller.Service,Component,Repostory等注解
 * 2.构造方法注入，@AutoWired熟悉注入，
 */
public class SpringInferConstructTestApplication {

  public static void main(String[] args) {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(InferConstructConfig.class); //加载spring配置文件

    //手动注册bean对象
    Object o =new Object();
    applicationContext.getBeanFactory().registerSingleton( "o",o);
    System.out.println("手动注册bean对象:"+applicationContext.getBean("o"));

    UserServiceInferConstruct userServiceInferConstruct1 = (UserServiceInferConstruct) applicationContext.getBean("userServiceInferConstruct");
    UserServiceInferConstruct userServiceInferConstruct2 = (UserServiceInferConstruct) applicationContext.getBean("userServiceInferConstruct");
    System.out.println("Spring创建的Bean是否相等：答:从单例池里面的bean:userServiceInferConstruct是相等的！");
    System.out.println(userServiceInferConstruct1 == userServiceInferConstruct2);
    userServiceInferConstruct1.test();

    // 1.测试Spring创建的bean默认都是单例bean
    System.out.println("orderService、orderService1、orderService2是不相等的,他们是三个单独的OrderService类型的Bean");
    System.out.println(applicationContext.getBean("orderService"));
    System.out.println(applicationContext.getBean("orderService1"));
    System.out.println(applicationContext.getBean("orderService2"));

    //反射看实例对象的属性是否加了Autowired注解：
    for (Field field: userServiceInferConstruct1.getClass().getDeclaredFields()) {
      if(field.isAnnotationPresent(Autowired.class)){

      }
    }
    //通过反射看userService这个对象的哪些方法上加了PostConstruct注解
    for (Method method : userServiceInferConstruct1.getClass().getMethods()) {
      if(method.isAnnotationPresent(PostConstruct.class)){

      }
    }
  }
}
