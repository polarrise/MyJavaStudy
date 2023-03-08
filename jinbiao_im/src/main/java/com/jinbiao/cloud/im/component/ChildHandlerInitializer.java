package com.jinbiao.cloud.im.component;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;


public class ChildHandlerInitializer extends io.netty.channel.ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        WSServerHandler wsServerHandler = new WSServerHandler();
        ChannelPipeline pipeline = ch.pipeline();
        // Heartbeat detection
        pipeline.addFirst(new IdleStateHandler(3, 3, 5));
        // Http编解码器
        pipeline.addLast(new HttpServerCodec());
        // 最大的消息大小
        pipeline.addLast(new HttpObjectAggregator(512 * 1024));
        // 主要用于处理大数据流，比如一个1G大小的文件如果你直接传输肯定会撑暴jvm内存的; 增加之后就不用考虑这个问题了
        pipeline.addLast(new ChunkedWriteHandler());
        // WebSocket数据压缩
        pipeline.addLast(new WebSocketServerCompressionHandler());
        // 业务逻辑
        pipeline.addLast(wsServerHandler);
        // 协议包长度限制
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws", null, true));
    }
}