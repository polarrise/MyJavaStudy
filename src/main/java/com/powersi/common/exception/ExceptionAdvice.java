package com.powersi.common.exception;

import com.powersi.common.api.CommonResult;
import com.powersi.common.api.ResultCode;
import com.powersi.common.exception.customException.BusinessException;
import com.powersi.common.exception.customException.ForbiddenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * @author wangjinbiao
 * @date 2023/2/7 14:31
 * @desc 统一拦截异常
 * 自定义异常是为了后面统一拦截异常时，对业务中的异常有更加细颗粒度的区分，拦截时针对不同的异常作出不同的响应。
 * 而统一拦截异常的目的:
 * 一个是为了可以与前面定义下来的统一包装返回结构能对应上，
 * 另一个是我们希望无论系统发生什么异常，Http 的状态码都要是 200,尽可能由业务来区分系统的异常。
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.powersi.controller")
public class ExceptionAdvice {

  @ExceptionHandler({BusinessException.class})
  public CommonResult<?> handleBusinessException(BusinessException ex) {
    return CommonResult.failed(ex.getMessage());
  }

  @ExceptionHandler(ForbiddenException.class)
  public CommonResult<?> handleForbiddenException(ForbiddenException ex) {
    return CommonResult.failed(ResultCode.FORBIDDEN);
  }

  /**
   * {@code @RequestBody} 参数校验不通过时抛出的异常处理
   */
  @ExceptionHandler({MethodArgumentNotValidException.class})
  public CommonResult<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    BindingResult bindingResult = ex.getBindingResult();
    StringBuilder sb = new StringBuilder("校验失败:");
    for (FieldError fieldError : bindingResult.getFieldErrors()) {
      sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
    }
    String msg = sb.toString();
    if (StringUtils.hasText(msg)) {
      return CommonResult.failed(ResultCode.VALIDATE_FAILED, msg);
    }
    return CommonResult.failed(ResultCode.VALIDATE_FAILED);
  }

  /**
   * {@code @PathVariable} 和 {@code @RequestParam} 参数校验不通过时抛出的异常处理
   */
  @ExceptionHandler({ConstraintViolationException.class})
  public CommonResult<?> handleConstraintViolationException(ConstraintViolationException ex) {
    if (StringUtils.hasText(ex.getMessage())) {
      return CommonResult.failed(ResultCode.VALIDATE_FAILED.getCode(), ex.getMessage());
    }
    return CommonResult.failed(ResultCode.VALIDATE_FAILED);
  }

  /**
   * 顶级异常捕获并统一处理，当其他异常无法处理时候选择使用
   */
  @ExceptionHandler({Exception.class})
  public CommonResult<?> handle(Exception ex, HttpServletRequest request) {
      log.error("全局异常,请求路径:" + request.getRequestURI());
      log.error("HOST:" + request.getRemoteHost());
      request.getParameterMap().forEach((key, value) -> log.error("*****请求参数*****:{},{}", key, value));
      log.error("*********异常信息:{},{}", ex.getMessage(), ex);
    return CommonResult.failed(ex.getMessage());
  }
}
