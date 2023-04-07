package com.tuling.mall.user.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequestFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.URI;

/**
 * @author Fox
 *
 * Spring Cloud LoadBalancer是Spring Cloud官方自己提供的客户端负载均衡器, 用来替代Ribbon。
 * RestTemplate整合LoadBalancer  实现客户端http请求拦截器
 * Ribbon底层调用链：
 * 1.底层也是通过restTemplate发送rest请求， http://微服务名+接口路径
 * 2.负载均衡拦截器LoadBalancerInterceptor拦截请求,做以下工作:1.负载均衡策略确定调用哪个目标服务，2.替换微服务名称为目标地址ip+端口号
 * 3.LoadBalancerClient通过http请求调用目标服务
 */
@Slf4j
public class MyLoadBalancerInterceptor implements ClientHttpRequestInterceptor {

    private LoadBalancerClient loadBalancer;
    private LoadBalancerRequestFactory requestFactory;

    public MyLoadBalancerInterceptor(LoadBalancerClient loadBalancer, LoadBalancerRequestFactory requestFactory) {
        this.loadBalancer = loadBalancer;
        this.requestFactory = requestFactory;
    }

    public MyLoadBalancerInterceptor(LoadBalancerClient loadBalancer) {
        this(loadBalancer,new LoadBalancerRequestFactory(loadBalancer));
    }

    /**
     * Ribbon调用链：
     * 1.底层也是通过restTemplate发送rest请求， http://微服务名+接口路径
     * 2.负载均衡拦截器LoadBalancerInterceptor拦截请求,做以下工作:1.负载均衡策略确定调用哪个目标服务，2.替换微服务名称为目标地址ip+端口号
     * 3.LoadBalancerClient通过http请求调用目标服务
     * @param request
     * @param body
     * @param execution
     * @return
     * @throws IOException
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        final URI originalUri = request.getURI();
        String serviceName = originalUri.getHost();
        log.info("进入自定义的请求拦截器中" + serviceName);
        Assert.state(serviceName != null, "Request URI does not contain a valid hostname: " + originalUri);
        return this.loadBalancer.execute(serviceName, requestFactory.createRequest(request, body, execution));
    }
}
