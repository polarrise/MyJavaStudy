package com.powersi.enums;

import lombok.Getter;

/**
 * @author wangjinbiao
 * @date 2023/2/1 17:14
 * @desc
 */
@Getter
public enum JudgeTaskFinishedWaysEnum {

  IS_TERMINATED("使用线程池的isTerminated方法去判断线程池任务是否执行成功了","isTerminated"),

  COUNTDOWNLATCH("使用闭锁countDownLatch去判断线程池任务是否执行成功了","countDownLatch"),

  AWAITTERMINATION("使用awaitTermination方法去判断线程池任务是否执行成功了","awaitTermination");


  private String name;

  private String value;

  JudgeTaskFinishedWaysEnum(String name, String value) {
    this.name = name;
    this.value = value;
  }
}
