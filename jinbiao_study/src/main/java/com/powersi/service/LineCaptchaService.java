package com.powersi.service;

import com.powersi.common.api.CommonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author：Jinbiao
 * @Date：2023/3/4 18:22
 * @Desc：生成图形验证码Service
 */
public interface LineCaptchaService {

    /**
     * @return 生成图形验证码-用redis来存
     */
    CommonResult<String> generateLineCaptcha1(HttpServletRequest request, HttpServletResponse response);

    /**
     * @return 校验验证码-用redis里面的验证码来校验
     */
    CommonResult<String> verifyCode1(HttpServletRequest request);

    /**
     * @return 生成图形验证码-用session来存
     */
    CommonResult<String> generateLineCaptcha2(HttpServletRequest request, HttpServletResponse response);

    /**
     * @return 校验验证码-从session来取
     */
    CommonResult<String> verifyCode2(HttpServletRequest request);
}
