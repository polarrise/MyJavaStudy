package com.tuling.mall.user.config;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Fox
 */
@Configuration
public class RestConfig {
    
    @Bean
    @LoadBalanced  // 微服务名替换为具体的ip:port
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
