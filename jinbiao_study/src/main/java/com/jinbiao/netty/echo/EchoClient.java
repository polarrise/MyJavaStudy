package com.jinbiao.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * 类说明：基于Netty的客户端
 * Bootstrap：Netty框架的启动类和主入口类，分为客户端类Bootstrap和服务器类ServerBootstrap两种。
 * Channel：可以看作是传入（入站）或者传出（出站）数据的载体。因此，它可以被打开或者被关闭，连接或者断开连接。
 * EventLoop暂时可以看成一个线程、EventLoopGroup自然就可以看成线程组。
 */
public class EchoClient {

    private final int port;
    private final String host;

    public EchoClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void start() throws InterruptedException {

        //线程组
        EventLoopGroup group  = new NioEventLoopGroup();
        try {
            //客户端启动必备，和服务器的不同点
            Bootstrap b = new Bootstrap();
            b.group(group)
                    //指定使用NIO的通信模式
                    .channel(NioSocketChannel.class)
                    //指定服务器的IP地址和端口，和服务器的不同点
                    .remoteAddress(new InetSocketAddress(host,port))
                    //和服务器的不同点
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new EchoClientHandler());

                        }
                    });
            //异步连接到服务器，sync()会阻塞到完成，和服务器的不同点
            ChannelFuture f = b.connect().sync();

            //阻塞当前线程，直到客户端的Channel被关闭
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new EchoClient(9999,"127.0.0.1").start();
    }
}
