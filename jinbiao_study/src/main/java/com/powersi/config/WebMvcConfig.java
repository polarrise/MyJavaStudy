package com.powersi.config;

import com.powersi.common.expandClass.JsonResponseBodyHandleReturnValue;
import com.powersi.controller.springmvcStudy.interceptor.MyInterceptor;
import com.powersi.controller.springmvcStudy.methodArgumentResolver.MyHandlerMethodArgumentResolver;
import com.powersi.controller.springmvcStudy.methodReturnValueHandler.MyMethodHandleReturnValue;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author WangJinbiao
 * @date 2023/2/19 23:07
 * @desc 注册自定义方法参数解析器、方法返回值解析器的时候需要手动添加到对应的解析器集合中：
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 添加自定义方法返回值处理器
     *
     * @param returnValueHandlers
     */
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(new JsonResponseBodyHandleReturnValue());
        returnValueHandlers.add(new MyMethodHandleReturnValue());
    }

    /**
     * 添加自定义方法参数解析器
     *
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new MyHandlerMethodArgumentResolver());
    }

    /**
     * 添加自定义拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns拦截的路径
        String[] addPathPatterns = {"/user/**"};

        //excludePathPatterns排除的路径
        String[] excludePathPatterns = {"/user/login", "/user/noLg", "/user/error"};
        registry.addInterceptor(new MyInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }
}
