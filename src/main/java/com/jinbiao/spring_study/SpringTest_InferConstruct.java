package com.jinbiao.spring_study;


import com.jinbiao.spring_study.service.UserServiceInitProcess;
import com.jinbiao.spring_study.service.UserServiceInferConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author wangjinbiao
 * @date 2023/2/14 14:11
 * @desc
 */
public class SpringTest_InferConstruct {
  public static void main(String[] args) {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //加载spring配置文件
    UserServiceInitProcess userServiceInitProcess = (UserServiceInitProcess) applicationContext.getBean("userServiceInitProcess");
    userServiceInitProcess.test();


    UserServiceInferConstruct userServiceInferConstruct1 = (UserServiceInferConstruct) applicationContext.getBean("userServiceInferConstruct");
    UserServiceInferConstruct userServiceInferConstruct2 = (UserServiceInferConstruct) applicationContext.getBean("userServiceInferConstruct");
    System.out.println("Spring创建的Bean是否相等：答:从单例池里面的bean:userServiceInferConstruct是相等的！");
    System.out.println(userServiceInferConstruct1 == userServiceInferConstruct2);
    userServiceInferConstruct1.test();

    System.out.println("orderService、orderService1、orderService2是不相等的,他们是三个单独的OrderService类型的Bean");
    System.out.println(applicationContext.getBean("orderService"));
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
