package com.powersi.entity.ws;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangjinbiao
 * @date 2023/2/1 17:06
 * @desc
 */
@Data
public class SendMessageSuccessVO implements Serializable {

  private static final long serialVersionUID = -6520259877498977529L;

  @ApiModelProperty(value = "消息确认ID(前端生成)")
  private String receiveMessageId;

  @ApiModelProperty(value = "消息是否发送成功")
  private Boolean ack;

  @ApiModelProperty(value = "消息状态")
  private Byte msgStatus;
}
