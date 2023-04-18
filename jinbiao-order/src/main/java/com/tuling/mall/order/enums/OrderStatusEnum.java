package com.tuling.mall.order.enums;

import lombok.Getter;

/**
 * @author wangjinbiao
 * @date 2023/2/1 17:14
 * @desc
 */
@Getter
public enum OrderStatusEnum {

  INIT("初始状态",0),

  SUCCESS("完成状态",1);



  private String name;

  private Integer value;

  OrderStatusEnum(String name, Integer value) {
    this.name = name;
    this.value = value;
  }
}
