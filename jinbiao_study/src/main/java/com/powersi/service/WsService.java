package com.powersi.service;

import com.powersi.entity.ws.WSMsgReqParam;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author wangjinbiao
 * @date 2023/2/1 14:08
 * @desc
 */
public interface WsService {

  void login(ChannelHandlerContext ctx, WSMsgReqParam param);

  /**
   * 发送消息
   */
  void sendMsg(ChannelHandlerContext ctx,WSMsgReqParam param);

  /**
   * 新的消息确认：将消息状态修改为3-接收者已确认收到消息
   *
   * @param param
   */
  void messageConfirmation(ChannelHandlerContext ctx,WSMsgReqParam param);
}
