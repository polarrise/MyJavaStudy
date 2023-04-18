package com.tuling.mall.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.tuling")
@MapperScan({"com.tuling.mall.order.dao,com.jinbiao.cloud.mbg.*"})
@EnableFeignClients
//@EnableDiscoveryClient
public class MallOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallOrderApplication.class, args);
    }

}
