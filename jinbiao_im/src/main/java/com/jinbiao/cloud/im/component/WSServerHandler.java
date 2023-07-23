package com.jinbiao.cloud.im.component;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.jinbiao.cloud.im.config.SpringContextHolder;
import com.jinbiao.cloud.im.service.Impl.WsServiceImpl;
import com.jinbiao.cloud.im.service.WsService;
import com.jinbiao.cloud.im.domain.ws.WSMsgReqParam;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@ChannelHandler.Sharable
@Slf4j
@Component
@DependsOn(value = {"wsServiceImpl", "springContextHolder"})
// 一定要注意：：：自己写入站handler要么继续往后传递，要么继承extends SimpleChannelInboundHandler<ByteBuf>，它会帮我们去释放buffer。不然一定会存在内存泄漏
public class WSServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static final WsService wsService = SpringContextHolder.getBean("wsServiceImpl", WsServiceImpl.class);

    private static final Logger wsLog = LoggerFactory.getLogger("WEBSOCKET");

    /**
     * 当Channel 连接成功触发
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.channel().config().setWriteBufferHighWaterMark(20 * 1024 * 1024);
        wsLog.info("Client " + ctx.channel().remoteAddress() + " connected");
    }

    /**
     * 当Channel  离开活动状态并且不再连接它的远程节点是被调用
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        //queueConnectionHelper.offLine(ctx);
        wsLog.info("Client " + ctx.channel().remoteAddress() + " closed");
    }

    /**
     * 心跳
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.ALL_IDLE)) {
                //ctx.channel().writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(WSMsgResult.success(WSMsgResult.HEART_BEAT, "心跳检测成功", null))));
            }
        }
    }

    /**
     * 显式处理特定类型的消息
     *
     * @param ctx     ChannelHandlerContext
     * @param message TextWebSocketFrame  数据帧:文本数据
     * @Description: [object Object]: 前端传的异常数据
     * 本地连接:ws://127.0.0.1:40002/ws。   在线websocket调试工具:http://coolaf.com/tool/chattest
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame message) {
        if (!StringUtils.isEmpty(message.text()) && !message.text().equals("[object Object]")) {
            try {
                JSONObject jsonObject = JSON.parseObject(message.text());
                WSMsgReqParam param = JSON.parseObject(jsonObject.toJSONString(), new TypeReference<WSMsgReqParam>() {
                });
                Integer code = param.getCode();
                if (!code.equals(WSReqTypeConstant.PING) && !code.equals(WSReqTypeConstant.TYPING)) {
                    wsLog.info("客户端websocket消息内容:{}", message.text());
                }
                switch (code) {
                    // 心跳检测
                    case WSReqTypeConstant.PING:
                        //wsService.heartbeatHandler(param);
                        break;
                    // 上线
                    case WSReqTypeConstant.LOGIN:
                        wsService.login(ctx, param);
                        break;
                    // 发送消息
                    case WSReqTypeConstant.SEND_MSG:
                        wsService.sendMsg(ctx,param);
                        break;
                    // 消息确认机制
                    case WSReqTypeConstant.MESSAGE_CONFIRMATION:
                        wsService.messageConfirmation(ctx,param);
                        break;
                    default:
                        log.info("默认未知类型消息");
                }
            } catch (Exception e) {
                log.error("******************未知类型消息:{},{}", e.getMessage(), e);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        wsLog.info("socket连接异常{},{}", cause.getMessage(), cause);
        ctx.channel().close();
    }
}
