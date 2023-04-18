package com.tuling.mall.order.config;

import feign.Logger;
import feign.Request;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fox
 */
@Configuration
public class FeignConfig {
    
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * socket连接超时，处理请求超时配置
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(2000, 100000);
    }
}
