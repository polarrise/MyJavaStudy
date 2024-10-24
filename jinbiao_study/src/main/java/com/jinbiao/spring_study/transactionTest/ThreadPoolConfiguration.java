package com.jinbiao.spring_study.transactionTest;

import com.powersi.config.TulingMallThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutorService;

@Configuration
public class ThreadPoolConfiguration {

    @Bean("commonPool")
    public ExecutorService commonThreadPoolExecutor(){
        return new TulingMallThreadPoolExecutor("测试公共线程池",16,50).getLhrmsThreadPoolExecutor();
    }
}
