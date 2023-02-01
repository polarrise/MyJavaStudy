package com.powersi.service.impl;

import com.powersi.entity.WSMsgReqParam;
import com.powersi.entity.WSMsgResult;
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
}
