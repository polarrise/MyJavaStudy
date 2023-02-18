package com.jinbiao.spring_study.circularReferences.demo;


import com.jinbiao.spring_study.circularReferences.service.AService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wangjinbiao
 * @date 2023/2/14 14:11
 * @desc
 */
public class SpringTest_circularReferences {
  public static void main(String[] args) {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DemoConfig.class); //加载spring配置文件

    //像这种AService前面两个都是首字母大写的，Spring的beanName不会去首位字母小些了
    A a = (A) applicationContext.getBean("a");
    System.out.println(a.getName());
  }
}
