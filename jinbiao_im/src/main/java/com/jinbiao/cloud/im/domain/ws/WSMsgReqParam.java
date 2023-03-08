package com.jinbiao.cloud.im.domain.ws;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * websocket 前端请求消息实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WSMsgReqParam {

  @ApiModelProperty(value = "发送者ID")
  private Long sendId;

  @ApiModelProperty(value = "用户ID")
  private Long userId;

  @ApiModelProperty(value = "发送者角色")
  private String role;

  @ApiModelProperty(value = "接收者ID")
  private Long receiveId;

  @ApiModelProperty(value = "接收者角色")
  private String receiveRole;

  @ApiModelProperty(value = "用户token")
  private String token;

  @ApiModelProperty(value = "操作码")
  private Integer code;

  @ApiModelProperty(value = "消息类型")
  private Integer type;

  @ApiModelProperty(value = "消息内容")
  private String msgContent;

  @ApiModelProperty(value = "在线状态 0:下线 1:上线 2:忙碌 3:暂离")
  private Integer state;

  @ApiModelProperty(value = "机构id")
  private Long unitId;

  @ApiModelProperty(value = "机构名称")
  private String unitName;

  @ApiModelProperty(value = "来源")
  private String source;

  @ApiModelProperty(value = "应用类型:h5/app/mp")
  private String terminalType;

  @ApiModelProperty(value = "案情id")
  private Long caseId;

  @ApiModelProperty(value = "工单id")
  private Long workOrderId;

  @ApiModelProperty(value = "订单ID")
  private Long orderId;

  @ApiModelProperty(value = "排队发送的消息,不计入免费咨询次数")
  private boolean sendMsgFlag = true;

  @ApiModelProperty(value = "已付款/待使用订单ID")
  private Long usePaidOrderId;

  @ApiModelProperty(value = "消息序列号")
  private Long offset;

  @ApiModelProperty(value = "用户当前定位")
  private String userCurrentLocation;

  @ApiModelProperty(value = "视频流id")
  private String streamId;

  @ApiModelProperty(value = "咨询内容")
  private String consultContent;

  @ApiModelProperty(value = "视频房间号id")
  private String roomId;

  @ApiModelProperty(value = "消息确认ID(前端生成)")
  private String receiveMessageId;

  @ApiModelProperty(value = "用户登陆ID")
  private String userLoginId;

  @ApiModelProperty(value = "会议标题")
  private String meetingTitle;

  @ApiModelProperty(value = "会议时间")
  private String meetingTime;

  @ApiModelProperty(value = "通话时长")
  private Long duration;
}

