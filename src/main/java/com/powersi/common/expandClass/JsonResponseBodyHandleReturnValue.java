package com.powersi.common.expandClass;

import com.powersi.annotation.DealResult;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.AsyncHandlerMethodReturnValueHandler;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

/**
 * @author WangJinbiao
 * @date 2023/2/19 22:48
 * @desc 自定义注解的方式统一对Controller返回值做脱敏处理：
 */
@Component
public class JsonResponseBodyHandleReturnValue implements HandlerMethodReturnValueHandler, AsyncHandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return true;
    }

    @Override
    public void handleReturnValue(@Nullable Object returnValue, MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest){
        mavContainer.setRequestHandled(true); // 表示此函数可以处理请求，不必交给别的代码处理
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        AnnotatedElement annotatedElement = methodParameter.getAnnotatedElement();
        System.out.println("annotatedElement:"+annotatedElement);
        Method method = methodParameter.getMethod();
        //获得所有的注解,如果这个方法上加了DealResult注解
        if(method.isAnnotationPresent(DealResult.class)) {//方法上是否使用DealResult注解
            System.out.println(method.getDefaultValue());
        }

        //response.getWriter().write(reuslt.toJSONString(baseResponse));
    }

    // 开启异步处理
    @Override
    public boolean isAsyncReturnValue(Object returnValue, MethodParameter returnType) {
        return supportsReturnType(returnType);
    }

}
