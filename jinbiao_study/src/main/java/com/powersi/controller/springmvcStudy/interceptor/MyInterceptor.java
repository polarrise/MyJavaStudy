package com.powersi.controller.springmvcStudy.interceptor;

import com.powersi.common.api.CommonResult;
import com.powersi.controller.springmvcStudy.uploadDownload.XiaofaUser;
import com.powersi.utils.JsonUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从session中获取user的信息
        XiaofaUser user = (XiaofaUser) request.getSession().getAttribute("user");
        //判断用户是否登录
        if (null == user) {
            //解决响应乱码问题：
            response.setCharacterEncoding("UTF-8");
            response.setHeader(" content-type ", "text/html;charset=UTF-8");
            response.getWriter().write(JsonUtils.objectToJson(CommonResult.failed("请登录")));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器当前请求,postHandle方法执行===");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("拦截器当前请求,afterCompletion方法执行===");
    }
}
