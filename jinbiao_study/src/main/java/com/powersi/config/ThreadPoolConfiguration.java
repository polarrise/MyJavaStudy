package com.powersi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutorService;

/**
 * @author WangJinbiao
 * @date 2024/09/16 23：54
 */
@Configuration
@EnableAsync
public class ThreadPoolConfiguration {

    @Bean("commonPool")
    public ExecutorService commonThreadPoolExecutor(){
        return new TulingMallThreadPoolExecutor("测试公共线程池",16,50).getLhrmsThreadPoolExecutor();
    }
}
