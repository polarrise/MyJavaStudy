package com.powersi.common.expandClass;
import com.powersi.annotation.Mobile;
import com.powersi.common.api.CommonResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author wangjinbiao
 * @date 2023/2/7 11:06
 * @desc ResponseBodyAdvice 是对 Controller 返回的内容在 HttpMessageConverter 进行类型转换之前拦截，进行相应的处理操作后，再将结果返回给客户端。
 * 那这样就可以把统一包装的工作放到这个类里面：
 * supports： 判断是否要交给 beforeBodyWrite 方法执行，ture：需要；false：不需要
 * beforeBodyWrite： 对 response 进行具体的处理
 */
@RestControllerAdvice(basePackages = "com.powersi.controller")
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 如果不需要进行封装的，可以添加一些校验手段，比如添加标记排除的注解
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        Method method = returnType.getMethod();
        for (Parameter parameter : method.getParameters()) {
            if(parameter.isAnnotationPresent(Mobile.class)){
                System.out.println("入参有Mobile注解存在==");
            }
        }
        // 提供一定的灵活度，如果body已经被包装了，就不进行包装
        if (body instanceof CommonResult) {
            return body;
        }
        return CommonResult.success(body);
    }
}
