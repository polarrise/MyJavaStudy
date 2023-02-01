package com.powersi.service.impl;

import com.powersi.entity.ws.SendMessageSuccessVO;
import com.powersi.entity.ws.WSMsgReqParam;
import com.powersi.entity.ws.WSMsgResult;
import com.powersi.enums.MsgSendStatusEnum;
import com.powersi.service.WsService;
import com.powersi.utils.DesensitizationUtil;
import com.powersi.utils.JsonUtils;
import com.powersi.utils.RegexpUtil;
import com.powersi.vo.WSMsgVO;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * @author wangjinbiao
 * @date 2023/2/1 14:09
 * @desc
 */
@Service
@Slf4j
public class WsServiceImpl implements WsService {
  private static final Logger wsLog = LoggerFactory.getLogger("WEBSOCKET");

  @Override
  public void login(ChannelHandlerContext ctx, WSMsgReqParam param) {
    Channel channel = ctx.channel();
    System.out.println("channel"+channel);
    ctx.channel().writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(WSMsgResult.success(WSMsgResult.LOGIN, "登录成功!", null))));

    wsLog.info("登录成功!");
  }

  /**
   * 如何实时监测消息状态:
   * 可参考:
   * 1.在消息入库的时候,定义状态为:1--服务器已接收
   * 2.在服务器推送给接收者消息的时候,定义状态为:2--服务器已发送给接收者
   * 3.在接收者通过消息确认机制确认收到消息时候,定义状态为:3--接收者已确认收到信息
   * @param ctx
   * @param param
   */
  @Override
  public void sendMsg(ChannelHandlerContext ctx,WSMsgReqParam param) {
    Channel channel = ctx.channel();
    WSMsgVO wsMsgVO = new WSMsgVO();
    BeanUtils.copyProperties(param,wsMsgVO);
    channel.writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(WSMsgResult.success(WSMsgResult.TEXT_MSG_TO_SENDER, "服务端通知发送者:发送成功!", wsMsgVO))));

    if("xiaofa_lawyer".equals(param.getReceiveRole())){
      channel.writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(WSMsgResult.success(WSMsgResult.TEXT_MSG, "律师接收消息成功!", wsMsgVO))));
      wsLog.info("律师消息接收成功:{}", wsMsgVO);
    }else{
         // 律师发送出去的消息，进行数据脱敏
        String msgContent = wsMsgVO.getMsgContent();
        // 手机号脱敏
        if (DesensitizationUtil.checkText(msgContent)) {
          msgContent = DesensitizationUtil.phoneDesensitization(msgContent);
        } else if (Pattern.matches(RegexpUtil.SPECIAL_COMBINATION, msgContent)) {
          // 六位以及六位以上的字符/字母/数字组合(两种即两种以上即可)
          msgContent = DesensitizationUtil.exceedSixCharacter(msgContent);
        }
      wsMsgVO.setMsgContent(msgContent);
      channel.writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(WSMsgResult.success(WSMsgResult.TEXT_MSG, "用户接收消息成功!", wsMsgVO))));
      wsLog.info("用户/客服消息接收成功:{}", wsMsgVO);

    }

  }

  /**
   * 在接收者通过消息确认机制确认收到消息时候,定义状态为:3--接收者已确认收到信息
   * @param ctx
   * @param param
   */
  @Override
  public void messageConfirmation(ChannelHandlerContext ctx,WSMsgReqParam param) {

    WSMsgVO wsMsgVO = new WSMsgVO();
    BeanUtils.copyProperties(param,wsMsgVO);

    // 向发送者发送消息，确认消息发送成功
    SendMessageSuccessVO sendMessageSuccessVO = new SendMessageSuccessVO();
    sendMessageSuccessVO.setReceiveMessageId(param.getReceiveMessageId());

    //更新消息状态为
    sendMessageSuccessVO.setMsgStatus(MsgSendStatusEnum.SERVER_SEND_TO_RECEIVER.getIndex());
    Channel channel = ctx.channel();
    if (param.getReceiveMessageId() != null) {
      sendMessageSuccessVO.setAck(true);
      ctx.channel();
      log.info("向消息发送方发送接收消息的确认信息开始获取到sendChannel={}", channel);
      channel.writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(WSMsgResult.success(WSMsgResult.SEND_MESSAGE_SUCCESS, "对方已收到消息!", sendMessageSuccessVO))));
    }
  }
}
