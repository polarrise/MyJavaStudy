package com.powersi.service;

import com.powersi.entity.WSMsgReqParam;
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
}
