package com.powersi.common.expandClass;

import com.powersi.annotation.DealResult;
import com.powersi.common.api.CommonResult;
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
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author WangJinbiao
 * @date 2023/2/19 22:48
 * @desc 自定义注解的方式统一对Controller返回值做脱敏处理：
 */
@Component
public class JsonResponseBodyHandleReturnValue implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return returnType.hasMethodAnnotation(DealResult.class);
    }

    @Override
    public void handleReturnValue(@Nullable Object returnValue, MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest nativeWebRequest) throws IOException {
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        mavContainer.setRequestHandled(true); // 表示此函数可以处理请求，不必交给别的代码处理
        AnnotatedElement annotatedElement = methodParameter.getAnnotatedElement();
        System.out.println("annotatedElement:" + annotatedElement);
        Method method = methodParameter.getMethod();
        //获得所有的注解,如果这个方法上加了DealResult注解
        if (!method.isAnnotationPresent(DealResult.class)) {//方法上是否使用DealResult注解
            return;
        }
        DealResult dealResult = method.getDeclaredAnnotation(DealResult.class);
        String[] value = dealResult.value();
        if (!Optional.ofNullable(value).isPresent()) {
           return;
        }
        Boolean isCommonResult = returnValue instanceof CommonResult;
        Boolean isCaseQO = ((CommonResult<?>) returnValue).getData() instanceof CaseQO;
        Arrays.stream(value).forEach(a->{
            System.out.println("类中方法上的注解的值" + a);
            if ("email".equals(a) && isCommonResult && isCaseQO) {
                CaseQO caseQO = (CaseQO) ((CommonResult<?>) returnValue).getData();
                caseQO.setEmail(PhoneOrEmailUtil.emailNum(caseQO.getEmail()));
            }else if("phone".equals(a) && isCommonResult){
                CaseQO caseQO = (CaseQO) ((CommonResult<?>) returnValue).getData();
                caseQO.setPhone(PhoneOrEmailUtil.phoneNum(caseQO.getPhone()));
            }
        });
        response.getWriter().write(JsonUtils.objectToJson(returnValue));
    }

}
