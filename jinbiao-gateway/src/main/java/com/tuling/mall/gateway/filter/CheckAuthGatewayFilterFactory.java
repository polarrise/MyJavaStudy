package com.tuling.mall.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * 限定在订单服务下新增过滤器
 * 自定义过滤器工厂，继承AbstractNameValueGatewayFilterFactory且我们的自定义名称必须要以GatewayFilterFactory结尾并交给spring管理。
 */
//@Component
@Slf4j
public class CheckAuthGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(NameValueConfig config) {

        return (exchange, chain) -> {
            log.info("调用CheckAuthGatewayFilterFactory===" + config.getName() + ":" + config.getValue());
            //调用CheckAuthGatewayFilterFactory===jinbiao:男
            // TODO
            return chain.filter(exchange);
        };
    }
}
