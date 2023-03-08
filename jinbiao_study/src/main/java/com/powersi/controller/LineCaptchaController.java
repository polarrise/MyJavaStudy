package com.powersi.controller;

import com.powersi.common.api.CommonResult;
import com.powersi.service.LineCaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Author：Jinbiao
 * @Date：2023/3/4 18:17
 * @Desc：登录用--生成图形验证码功能 快捷键设置注释：
 * Alt+SHilt+向上上下箭头 移动当前行。
 * Alt+向上箭头 找当前类的上一个方法
 * ctrl+1:格式化代码，ctrl+2 重命名当前类
 * Alt+insert 生成实现方法或者构造方法
 * ctrl+alt+o 格式化化导入包
 */
@Controller
@RequestMapping("/loginVerfy")
public class LineCaptchaController {

    @Autowired
    private LineCaptchaService lineCaptchaService;

    /**
     * 基于redis来实现业务场景:app登录，用户输入图形验证码的内容->再输入手机号获取短信验证码->登录。
     * 获取图形验证码
     * http://localhost:8011/jinbiao-cool/getShearCaptcha1?phone=xxx
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getShearCaptcha1", method = RequestMethod.GET)
    public CommonResult<String> getShearCaptcha1(HttpServletRequest request, HttpServletResponse response) {
        return lineCaptchaService.generateLineCaptcha1(request, response);
    }

    /**
     * 基于redis来实现业务场景:app登录，用户输入图形验证码的内容->再输入手机号获取短信验证码->登录。
     * 登录校验,验证图形码(这一步其实可以放到登录拦截器中去做)
     * http://localhost:8011/jinbiao-cool/verifyCode1?verifyCode=rnrl&phone=xxx
     * @param request
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/verifyCode1", method = RequestMethod.GET)
    public CommonResult verifyCode1(HttpServletRequest request) {
        return lineCaptchaService.verifyCode1(request);
    }

    /**
     * 基于session来实现业务场景:app登录，用户输入图形验证码的内容->再输入手机号获取短信验证码->登录。
     * 获取图形验证码
     * http://localhost:8011/jinbiao-cool/getShearCaptcha2?phone=xxx
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getShearCaptcha2", method = RequestMethod.GET)
    public CommonResult<String> getShearCaptcha(HttpServletRequest request, HttpServletResponse response) {
        return lineCaptchaService.generateLineCaptcha2(request, response);
    }

    /**
     * 基于session来实现业务场景:app登录，用户输入图形验证码的内容->再输入手机号获取短信验证码->登录。
     * 登录校验,验证图形码
     * http://localhost:8011/jinbiao-cool/verifyCode2?verifyCode=akdj
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/verifyCode2", method = RequestMethod.GET)
    public CommonResult verifyCode2(HttpServletRequest request) {
        return lineCaptchaService.verifyCode2(request);
    }
}
