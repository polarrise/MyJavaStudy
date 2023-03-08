package com.jinbiao.cloud.im.enums;

import lombok.Getter;

/**
 * @author wangjinbiao
 * @date 2023/2/1 17:14
 * @desc
 */
@Getter
public enum MsgSendStatusEnum {

  SERVER_RECEIVED((byte) 1, "服务器已接收"),

  SERVER_SEND_TO_RECEIVER((byte)2, "服务器已发送给接收者"),

  RECEIVER_ACKED((byte)3, "接收者已确认收到信息");

  private Byte index;

  private String desc;

  MsgSendStatusEnum(Byte index, String desc) {
    this.index = index;
    this.desc = desc;
  }
}
