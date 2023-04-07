package com.tuling.mall.user.config;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author：Jinbiao
 * @Date：2023/4/7 14:55
 * @Desc：修改默认负载均衡策略
 * 全局配置：调用的微服务，一律使用指定的负载均衡策略
 */

@Configuration
public class RibbonConfig {

    /**
     * 全局配置
     * 指定负载均衡策略
     * @return
     */
    @Bean
    public IRule ribbonRule() {
        // 指定使用Nacos提供的负载均衡策略（优先调用同一集群的实例，基于随机权重）
        return new NacosRule();
    }
}
