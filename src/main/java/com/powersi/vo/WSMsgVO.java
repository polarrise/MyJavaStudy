package com.powersi.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author wangjinbiao
 * @date 2023/2/1 15:17
 * @desc
 */
@Data
public class WSMsgVO {
  @ApiModelProperty(value = "发送用户id")
  private Long sendId;

  @ApiModelProperty(value = "发送用户角色")
  private String sendRole;

  @ApiModelProperty(value = "接收用户id")
  private Long receiveId;

  @ApiModelProperty(value = "接受者用户角色")
  private String receiveRole;

  @ApiModelProperty(value = "消息内容")
  private String msgContent;

  @ApiModelProperty(value = "消息类型")
  private Integer type;

  @ApiModelProperty(value = "发送时间(日期格式)")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createTime;

  @ApiModelProperty(value = "发送时间(字符串格式)")
  private String createTimeStr;

  @ApiModelProperty(value = "用户手机号")
  private String phone;

  @ApiModelProperty(value = "消息状态：0=聊天；1=留言；2=留言回复")
  private Integer msgStatus = 0;

  @ApiModelProperty(value = "未读数量")
  private Integer unreadCount;

  @ApiModelProperty(value= "消息序列号")
  private Long offset;

  @ApiModelProperty(value = "是否在线")
  private boolean online;

  @ApiModelProperty(value = "消息确认ID")
  private String receiveMessageId;
}
