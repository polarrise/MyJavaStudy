package com.jinbiao.netty.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * 作者：Mark
 * 类说明：客户端的业务Handler
 *
 * 一定要注意：：：自己写入站handler要么继续往后传递，要么继承extends SimpleChannelInboundHandler<ByteBuf>，它会帮我们去释放buffer。不然一定会存在内存泄漏
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    //读取到网络数据后进行业务处理,并关闭连接
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("client Accept"+msg.toString(CharsetUtil.UTF_8));
        //关闭连接
        //ctx.close();
    }

    //channel活跃后，做业务处理
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer(
                "Hello,Netty",CharsetUtil.UTF_8));
        //ctx.pipeline().write()
        //ctx.channel().write()
        ctx.alloc().buffer();
    }
}
