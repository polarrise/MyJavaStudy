package com.powersi.controller.springmvcStudy.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("/test2")
public class JinbiaoController2 implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        System.out.println("实现了HttpRequestHandler接口的handler执行了===");
        // 设置服务器字符集为 UTF-8
        httpServletResponse.setCharacterEncoding("UTF-8");
        // 通过响应头，设置浏览器也使用 UTF-8 字符集
        httpServletResponse.setHeader("Content-Type", "text/html; charset=UTF-8");
        httpServletResponse.getWriter().write("实现了HttpRequestHandler接口的handler执行了===");

    }
}
