package com.powersi.controller.springmvc_study.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 什么是Handler？
 * Handler表示请求处理器，在SpringMVC中有四种Handler：
 * 1. 实现了Controller接口的Bean对象
 * 2. 实现了HttpRequestHandler接口的Bean对象
 * 3. 添加了@RequestMapping注解的方法
 * 4. 一个HandlerFunction对象
 */

@Component("/test1")
public class JinbiaoController1 implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        System.out.println("实现了Controller接口的handler了===");
        return null;
    }
}
