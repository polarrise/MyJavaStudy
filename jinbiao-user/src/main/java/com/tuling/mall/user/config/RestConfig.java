package com.tuling.mall.user.config;


import com.tuling.mall.user.component.MyLoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Fox
 * Ribbon调用链：
 * 1.底层也是通过restTemplate发送rest请求， http://微服务名+接口路径
 * 2.负载均衡拦截器LoadBalancerInterceptor拦截请求,做以下工作:1.负载均衡策略确定调用哪个目标服务，2.替换微服务名称为目标地址ip+端口号
 * 3.LoadBalancerClient通过http请求调用目标服务
 */
@Configuration
public class RestConfig {


    @Bean
    @LoadBalanced  // 微服务名替换为具体的ip:port， openfign底层是通过ribbon来实现客户端负载均衡的，通过LoadBlanceInterceptor拦截服务名然后选择负载均衡策略，通过LoadBalancerClient调用http接口
    //@MyLoadBalanced  //RestTemplate整合LoadBalancer
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
