package com.tuling.mall.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Fox
 *
 * 每一个过滤器都必须指定一个int类型的order值，order值越小，过滤器优先级越高，执行顺序越靠前。
 *  GlobalFilter通过实现Ordered接口，来指定order值
 *
 */
//@Component
@Slf4j
public class CheckAuthFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取token
        String token = exchange.getRequest().getHeaders().getFirst("token");
        if (null == token) {
            log.info("token is null");
            ServerHttpResponse response = exchange.getResponse();
            response.getHeaders().add("Content-Type",
                    "application/json;charset=UTF-8");
            // 401 用户没有访问权限
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            byte[] bytes = HttpStatus.UNAUTHORIZED.getReasonPhrase().getBytes();
            DataBuffer buffer = response.bufferFactory().wrap(bytes);
            // 请求结束，不继续向下请求
            return response.writeWith(Mono.just(buffer));
        }
        //TODO 校验token进行身份认证
        log.info("校验token");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
