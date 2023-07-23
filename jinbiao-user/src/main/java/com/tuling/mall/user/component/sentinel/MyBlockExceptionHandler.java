package com.tuling.mall.user.component.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinbiao.cloud.common.entity.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author：Jinbiao
 * @Date：2023/4/27 15:06
 * @Desc：BlockException异常统一处理
 * springwebmvc接口资源限流入口在HandlerInterceptor的实现类AbstractSentinelInterceptor的preHandle方法中，对异常的处理是BlockExceptionHandler的实现类
 */
@Slf4j
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        log.info("BlockExceptionHandler BlockException================"+e.getRule());

        CommonResult r = null;

        if (e instanceof FlowException) {
            r = CommonResult.failed(100,"接口限流了");

        } else if (e instanceof DegradeException) {
            r = CommonResult.failed(101,"服务降级了");

        } else if (e instanceof ParamFlowException) {
            r = CommonResult.failed(102,"热点参数限流了");

        } else if (e instanceof SystemBlockException) {
            r = CommonResult.failed(103,"触发系统保护规则了");

        } else if (e instanceof AuthorityException) {
            r = CommonResult.failed(104,"授权规则不通过");
        }

        //返回json数据
        response.setStatus(500);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getWriter(), r);

    }
}
