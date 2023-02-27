package com.powersi.controller.springmvcStudy.interceptor;

import cn.hutool.core.collection.CollectionUtil;
import com.powersi.common.api.CommonResult;
import com.powersi.controller.springmvcStudy.uploadDownload.XiaofaUser;
import com.powersi.service.XiaoFaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class MyController {

    @Autowired
    private XiaoFaUserService xiaoFaUserService;

    //用户登录
    @RequestMapping("login")
    public CommonResult<String> login(HttpServletRequest request) {
        Map map = new HashMap<>(1);
        map.put("id", 1);
        List<XiaofaUser> xiaofaUserList = xiaoFaUserService.getXiaofaUserList(map);
        if (CollectionUtil.isEmpty(xiaofaUserList)) {
            return CommonResult.failed("No This User");
        }
        //将已经登录的用户信息添加到session中、
        request.getSession().setAttribute("user", xiaofaUserList.get(0));
        return CommonResult.success("Login Success");
    }

    //不需要登录也能访问的请求
    @RequestMapping("/noLg")
    public CommonResult<String> noLg() {
        return CommonResult.success("Everyone Can See");
    }

    //必须要登录才能访问的请求
    @RequestMapping("/mustLg")
    public CommonResult<String> mustLg() {
        return CommonResult.success("Only Login User Can See here");
    }

    //如果用户未登录访问了需要登录才能访问的请求会跳转到这个错误提示页面
    @RequestMapping("/error")
    public CommonResult<String> error() {
        return CommonResult.success("You Must Login");
    }
}

