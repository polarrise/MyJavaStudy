package com.powersi.common.exception.customException;

/**
 * @author wangjinbiao
 * @date 2023/2/7 14:34
 * @desc 自定义异常
 */
public class ForbiddenException extends RuntimeException {
  public ForbiddenException(String message) {
    super(message);
  }
}
