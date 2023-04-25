package com.tuling.mall.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @Author Fox
 */
@Configuration
public class RateLimiterConfig {

    @Bean
    KeyResolver keyResolver() {
        //url限流
        return exchange -> Mono.just(exchange.getRequest().getURI().getPath());
        //参数限流
        //return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("user"));
    }
}
