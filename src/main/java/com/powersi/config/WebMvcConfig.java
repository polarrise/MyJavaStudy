package com.powersi.config;

import com.powersi.common.expandClass.JsonResponseBodyHandleReturnValue;
import com.powersi.controller.springmvc_study.methodArgumentResolver.MyHandlerMethodArgumentResolver;
import com.powersi.controller.springmvc_study.methodReturnValueHandler.MyMethodHandleReturnValue;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author WangJinbiao
 * @date 2023/2/19 23:07
 * @desc
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
