package com.powersi.controller.springmvc_study.methodReturnValueHandler;

import com.powersi.annotation.DealResult;
import com.powersi.common.api.CommonResult;
import com.powersi.controller.springmvc_study.annotation.MyReturnValueResolver;
import com.powersi.controller.springmvc_study.methodParam.User;
import com.powersi.qo.CaseQO;
import com.powersi.utils.JsonUtils;
import com.powersi.utils.PhoneOrEmailUtil;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author WangJinbiao
 * @date 2023/2/19 22:48
 * @desc 自定义注解的方式统一对Controller返回值做脱敏处理：
 */
@Component
public class MyMethodHandleReturnValue implements HandlerMethodReturnValueHandler{

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.hasMethodAnnotation(MyReturnValueResolver.class);
    }

    @Override
    public void handleReturnValue(@Nullable Object returnValue, MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest nativeWebRequest) throws IOException {
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        mavContainer.setRequestHandled(true); // 表示此函数可以处理请求，不必交给别的代码处理
        Method method = methodParameter.getMethod();
        //获得所有的注解,如果这个方法上加了DealResult注解
        if (!method.isAnnotationPresent(MyReturnValueResolver.class)) {//方法上是否使用MyReturnValueResolver注解
            return;
        }
        MyReturnValueResolver myReturnValueResolver = method.getDeclaredAnnotation(MyReturnValueResolver.class);
        String[] value = myReturnValueResolver.value();
        if (!Optional.ofNullable(value).isPresent()) {
           return;
        }
        Boolean isCommonResult = returnValue instanceof CommonResult;
        if(!isCommonResult){
            return;
        }
        if(!isCommonResult || !(((CommonResult<?>) returnValue).getData() instanceof User)){
            return;
        }
        Arrays.stream(value).forEach(a->{
            System.out.println("类中方法上的注解的值" + a);
            if ("email".equals(a)) {
                User user = (User) ((CommonResult<?>) returnValue).getData();
                user.setEmail(PhoneOrEmailUtil.emailNum(user.getEmail()));
            }else if("phone".equals(a)){
                User user = (User) ((CommonResult<?>) returnValue).getData();
                user.setPhone(PhoneOrEmailUtil.phoneNum(user.getPhone()));
            }
        });
        //解决响应乱码问题：
        response.setCharacterEncoding("UTF-8");
        response.setHeader (" content-type ","text/html;charset=UTF-8");
        response.getWriter().write(JsonUtils.objectToJson(returnValue));
    }
}
