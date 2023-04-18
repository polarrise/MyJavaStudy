package com.tuling.storage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.tuling")
@MapperScan({"com.tuling.mall.order.dao,com.jinbiao.cloud.mbg.*"})
public class StockServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockServiceApplication.class, args);
    }

}
