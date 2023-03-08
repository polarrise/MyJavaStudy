package com.jinbiao.cloud.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@MapperScan({"com.jinbiao.cloud.common.dao"})
@SpringBootApplication(scanBasePackages = {"com.jinbiao.cloud.common"})
//@ComponentScan(basePackages = {"com.powersi.*", "com.config"})  @ComponentScan 用于指定 Spring 在初始化容器时要扫描的包。basePackages 属性用于指定要扫描的包。
@EnableAspectJAutoProxy  //添加@EnableAspectJAutoProxy注解来放开代理的使用
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

}
