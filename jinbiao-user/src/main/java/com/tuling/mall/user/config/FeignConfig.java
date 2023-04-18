package com.tuling.mall.user.config;

import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author：Jinbiao
 * @Date：2023/4/10 14:28
 * @Desc：注意： 此处配置@Configuration注解就会全局生效，如果想指定对应微服务生效，就不能配置@Configuration
 */
@Configuration
public class FeignConfig {
    /**
     * 日志级别
     *
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    //@Bean
    //public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
    //    return new BasicAuthRequestInterceptor("jinbiao", "123456");
    //}

    /**
     * 自定义拦截器
     * @return
     */
    @Bean
    public FeignAuthRequestInterceptor feignAuthRequestInterceptor(){
        return new FeignAuthRequestInterceptor();
    }

    /**
     * socket连接超时，处理请求超时配置
     */
    @Bean
    public Request.Options options() {
        return new Request.Options(2000, 100000);
    }

}
