package com.powersi.common.exception;

import com.powersi.common.api.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.net.SocketTimeoutException;

/**
 * 全局异常处理
 * Created by macro on 2020/2/27.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public CommonResult handle(Exception e, HttpServletRequest request) {
        if (e instanceof ApiException) {
            log.info("全局异常,请求路径:" + request.getRequestURI());
            log.info("HOST:" + request.getRemoteHost());
            request.getParameterMap().forEach((key, value) -> log.info("*****请求参数*****:{},{}", key, value));
            log.info("*********异常信息:{},{}", e.getMessage(), e);
        } else {
            log.error("全局异常,请求路径:" + request.getRequestURI());
            log.error("HOST:" + request.getRemoteHost());
            request.getParameterMap().forEach((key, value) -> log.error("*****请求参数*****:{},{}", key, value));
            log.error("*********异常信息:{},{}", e.getMessage(), e);
        }
        if (e instanceof ApiException) {
            ApiException e1 = (ApiException) e;
            if (e1.getErrorCode() != null) {
                return CommonResult.failed(e1.getErrorCode(), "服务器请求异常");
            }
            return CommonResult.failed(e1.getMessage());
        } else if (e instanceof SocketTimeoutException) {
            return CommonResult.failed("网络异常，请稍后重试");
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            String method = request.getMethod();
            return CommonResult.failed(String.format(
                    "请求方法错误，当前使用的是`%s`，请检查是否应为`%s`",
                    method, "GET".equals(method) ? "POST" : "GET"
            ));
        } else if (!"GET".equals(request.getMethod()) && (e instanceof HttpMediaTypeException || e instanceof HttpMessageConversionException || e instanceof IllegalArgumentException || e instanceof IllegalStateException)) {
            return CommonResult.failed("请求格式错误，请检查 Content-Type 以及 请求参数字段名/参数类型 是否正确");
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            return CommonResult.failed(exception.getBindingResult().getFieldError().getDefaultMessage());
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) e;
            return CommonResult.failed(exception.getName() + "参数无效");
        }
        if (StringUtils.isEmpty(e.getMessage())) {
            return CommonResult.failed("服务器异常");
        } else {
            return CommonResult.failed(e.getMessage());
        }
    }
}