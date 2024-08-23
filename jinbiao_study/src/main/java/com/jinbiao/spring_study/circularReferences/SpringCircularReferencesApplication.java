package com.jinbiao.spring_study.circularReferences;


import com.jinbiao.spring_study.circularReferences.service.AService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wangjinbiao
 * @date 2023/2/14 14:11
 * @desc
 */
public class SpringCircularReferencesApplication {
  public static void main(String[] args) {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //加载spring配置文件

    //像这种AService前面两个都是首字母大写的，Spring的beanName不会去首位字母小些了
    AService aService = (AService) applicationContext.getBean("AService");
    aService.testA();
  }
}
