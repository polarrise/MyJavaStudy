package com.tuling.mall.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@MapperScan({"com.tuling.mall.order.dao,com.jinbiao.cloud.mbg.*"})
public class MallUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallUserApplication.class, args);
    }

}
