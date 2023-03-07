package com.powersi.common.exception.customException;

/**
 * @author wangjinbiao
 * @date 2023/2/7 14:35
 * @desc 自定义异常
 */
public class BusinessException extends RuntimeException {
  public BusinessException(String message) {
    super(message);
  }
}
