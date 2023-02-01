package com.powersi.component;

import com.powersi.config.IMApplicationConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Slf4j
@Component
public class WebSocketServer {

    @Autowired
    private IMApplicationConfig imApplicationConfig;

    private final EventLoopGroup parentGroup = new NioEventLoopGroup();
    private final EventLoopGroup childGroup = new NioEventLoopGroup();

    /**
     * 服务端启动
     */
    @PostConstruct
    public void start() {
        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(imApplicationConfig.getServerPort()))
                    .childOption(NioChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChildHandlerInitializer());
            ChannelFuture future = bootstrap.bind().sync();
            if (future.isSuccess()) {
                log.info("wsServer启动成功");
            }
        } catch (InterruptedException e) {
            log.error("WebSocketServer启动异常:{},{}", e.getMessage(), e);
        }
    }

    /**
     * 销毁创建的线程池,释放资源
     */
    @PreDestroy
    public void destroy() {
        parentGroup.shutdownGracefully().syncUninterruptibly();
        childGroup.shutdownGracefully().syncUninterruptibly();
        log.info("wsServer资源释放完成");
    }
}
