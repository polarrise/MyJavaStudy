package com.powersi.controller.springmvcStudy.methodArgumentResolver;

import cn.hutool.core.bean.BeanUtil;
import com.powersi.controller.springmvcStudy.annotation.MyArgumentResolver;
import com.powersi.controller.springmvcStudy.methodParam.User;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * 有时候我们注入的对象需要满足如下这些要求：
 * 注入的对象包含多个字段，并且对应的字段值需要从多个源进行读取
 * 注入的对象并不是一个表单对象
 * 注入的对象不是从request body中传入
 * 如果我们需要满足上述说的这些要求，就需要创建一个自定义的HandlerMethodArgumentResolver，那这篇博客就是为了描述我们如何做到这一点。
 *
 * 1.创建一个自定义的HandlerMethodArgumentResolver：实现HandlerMethodArgumentResolver 接口
 * 实现supportsParameter(MethodParameter methodParameter)方法，如果方法参数是FooBar类型返回true，其余返回false
 * 实现resolveArgument() 方法，逻辑如下：
 （1）从请求参数获bar的值
  (2）从请求参数获foo的值
 （3）如果请求参数中不存在bar的值，使用默认值
 （4）如果请求参数中不存在foo的值，使用默认值
 （5）创建一个新的FooBar对象并返回

 * 2.配置web应用上下文信息：
 * 在我们注入FooBar 对象到Controller方法之前，我们需要注册CustomMethodArgumentResolver 作为方法参数的解析器，下面我们将介绍如何在springboot中进行注册
 * 新建CustomWebMvcExtend类，并实现WebMvcConfigurer接口
 * 覆写addArgumentResolvers方法，并将CustomMethodArgumentResolver 添加进resolvers列表对象中

*/
@Component
public class MyHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if(methodParameter.getParameter().isAnnotationPresent(MyArgumentResolver.class)){
            System.out.println("有MyArgumentResolver注解存在==");
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        System.out.println("进入我的自定义参数解析器==");
        Map<String, String[]> parameterMap = nativeWebRequest.getParameterMap();
        User user = new User("defaultName","123456","微信小程序");
        if(parameterMap.isEmpty()){
            return user;
        }
        Map<String,Object> parseMap = new HashMap(parameterMap.size());
        //转参数map
        parameterMap.entrySet().forEach(a->{
            parseMap.put(a.getKey(),a.getValue()[0]);
        });
        BeanUtil.copyProperties(parseMap,user);
        return user;
    }
}
