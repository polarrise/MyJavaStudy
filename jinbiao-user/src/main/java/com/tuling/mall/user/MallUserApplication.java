package com.tuling.mall.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableFeignClients
@MapperScan({"com.tuling.mall.user.dao","com.jinbiao.cloud.mbg.*"})
@EnableScheduling   // 开启定时任务功能
public class MallUserApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MallUserApplication.class, args);

        while(true) {
            //当动态配置刷新时，会更新到 Enviroment中，因此这里每隔一秒中从Enviroment中获取配置
            String userName = applicationContext.getEnvironment().getProperty("common.name");
            String userAge = applicationContext.getEnvironment().getProperty("common.age");
            System.err.println("common name :" + userName + "; age: " + userAge);
            TimeUnit.SECONDS.sleep(1);
        }
    }

}
