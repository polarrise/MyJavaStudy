package com;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.powersi.spring_aop.v1.IUserService;
import com.powersi.spring_aop.v1.Impl.Person1ServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@MapperScan({"com.powersi.dao"})
@SpringBootApplication(scanBasePackages = {"com.powersi"})
@ComponentScan(basePackages = {"com.powersi.*", "com.config"})   //@ComponentScan 用于指定 Spring 在初始化容器时要扫描的包。basePackages 属性用于指定要扫描的包。
@EnableAspectJAutoProxy  //添加@EnableAspectJAutoProxy注解来放开代理的使用
public class SpringBootDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootDemoApplication.class, args);
//        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringBootDemoApplication.class);
//        IUserService bean = ac.getBean(IUserService.class);
//        bean.log1();
//        System.out.println("-------------------");
//        bean.log2();
//
//        System.out.println("********");
//        Person1ServiceImpl personService = ac.getBean(Person1ServiceImpl.class);
//        personService.show();
    }

}
