package com.powersi.config;

import com.powersi.common.expandClass.JsonResponseBodyHandleReturnValue;
import com.powersi.controller.springmvcStudy.methodArgumentResolver.MyHandlerMethodArgumentResolver;
import com.powersi.controller.springmvcStudy.methodReturnValueHandler.MyMethodHandleReturnValue;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author WangJinbiao
 * @date 2023/2/19 23:07
 * @desc 注册自定义方法参数解析器、方法返回值解析器的时候需要手动添加到对应的解析器集合中：
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(new JsonResponseBodyHandleReturnValue());
        returnValueHandlers.add(new MyMethodHandleReturnValue());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new MyHandlerMethodArgumentResolver());
    }
}
