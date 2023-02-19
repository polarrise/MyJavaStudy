package com.powersi.config;

import com.powersi.common.expandClass.JsonResponseBodyHandleReturnValue;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
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
    }
}
