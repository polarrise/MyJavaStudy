package com;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@MapperScan({"cn.hsa.tps.*.dao"})
@MapperScan({"com.powersi.dao"})
@SpringBootApplication(scanBasePackages = {"com.powersi"})
@ComponentScan(basePackages = {"com.powersi.*", "com.config"})   //@ComponentScan 用于指定 Spring 在初始化容器时要扫描的包。basePackages 属性用于指定要扫描的包。
//@EnableDubbo   启用dubbo
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

}
