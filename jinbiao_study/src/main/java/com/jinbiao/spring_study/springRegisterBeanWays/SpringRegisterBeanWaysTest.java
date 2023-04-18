package com.jinbiao.spring_study.springRegisterBeanWays;

import com.jinbiao.spring_study.springRegisterBeanWays.beans.OrderService;
import com.jinbiao.spring_study.springRegisterBeanWays.beans.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author：Jinbiao
 * @Date：2023/4/11 21:49
 * @Desc：
 */
public class SpringRegisterBeanWaysTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //加载spring配置文件

        //2.输出容器中定义的所有bean信息
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(String.format("%s->%s", beanName, applicationContext.getBean(beanName)));
        }

        System.out.println();
        System.out.println("第1种方式注册Bean:使用@Component注解方式注册OrderService=");
        OrderService orderService = (OrderService) applicationContext.getBean("orderService");
        orderService.test();

        System.out.println();
        System.out.println("第2种方式注册Bean:使用@Configuration+@Bean的方式注册orderService1==");
        OrderService orderService1 = (OrderService) applicationContext.getBean("orderService1");
        orderService1.test();

        System.out.println();
        System.out.println("第3种方式注册Bean:使用FactoryBean方式注册OrderService===");
        OrderService orderServiceFactoryBean = (OrderService) applicationContext.getBean("way3_FactoryBean");
        orderServiceFactoryBean.test();

        Student student = (Student) applicationContext.getBean("student");
        student.test();


    }
}
