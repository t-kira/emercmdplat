package com.kira.emercmdplat.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * @Author: kira
 * @Date: 2020/7/24 10:33
 * @Description:
 */
@Component
public class NettyServer {

    private static final Logger log = LoggerFactory.getLogger(NettyServer.class);

    private static final String WEBSOCKET_PROTOCOL = "WebSocket";

    @Value("${webSocket.netty.port:58088}")
    private int port;

    @Value("${webSocket.netty.path:/webSocket}")
    private String webSocketPath;

    @Autowired
    private WebSocketHandler webSocketHandler;

    private EventLoopGroup bossGroup;

    private EventLoopGroup workGroup;

    /**
     * 启动
     * @throws InterruptedException
     */
    private void start() throws InterruptedException {
        bossGroup = new NioEventLoopGroup();
        workGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        //bossGroup辅助客户端tcp连接请求，workGroup辅助与客户端之间的读写操作
        bootstrap.group(bossGroup, workGroup);
        //设置NIO类型的channel
        bootstrap.channel(NioServerSocketChannel.class);
        //监听端口
        bootstrap.localAddress(new InetSocketAddress(port));
        //连接到达时会创建一个通道
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            /**
             * This method will be called once the {@link Channel} was registered. After the method returns this instance
             * will be removed from the {@link ChannelPipeline} of the {@link Channel}.
             *
             * @param ch the {@link Channel} which was registered.
             * @throws Exception is thrown if an error occurs. In that case it will be handled by
             *                   {@link #exceptionCaught(ChannelHandlerContext, Throwable)} which will by default close
             *                   the {@link Channel}.
             */
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                //流水线管理通道中的处理程序(Handler),用来处理业务
                //http编解码器
                ch.pipeline().addLast(new HttpServerCodec());
                ch.pipeline().addLast(new ObjectEncoder());
                //以块的方式来写的处理器
                ch.pipeline().addLast(new ChunkedWriteHandler());
                /*
                 * 说明
                 * 1、http数据在传输过程中是分段的，HttpObjectAggregator可以将多个段聚合
                 * 2、当浏览器发送大量数据时，就会发送多次http请求
                 */
                ch.pipeline().addLast(new HttpObjectAggregator(8192));
                /*
                说明：
                1、对应webSocket，它的数据是以帧（frame）的形式传递
                2、浏览器请求时 ws://localhost:58080/xxx 表示请求的uri
                3、核心功能是将http协议升级为ws协议，保持长连接
                */
                ch.pipeline().addLast(new WebSocketServerProtocolHandler(webSocketPath, WEBSOCKET_PROTOCOL, true, 65536 * 10));
                //自定义的handler,处理业务逻辑
                ch.pipeline().addLast(webSocketHandler);
            }
        });
        //配置完成，开始绑定server,通过调用sync同步方法阻塞直到绑定成功
        ChannelFuture future = bootstrap.bind().sync();
        log.info("Server started and listen on: {}", future.channel().localAddress());
        //对关闭通道进行监听
        future.channel().closeFuture().sync();
    }

    /**
     * 释放资源
     * @throws InterruptedException
     */
    @PreDestroy
    public void destroy() throws InterruptedException {
        if (bossGroup != null) {
            bossGroup.shutdownGracefully().sync();
        }
        if (workGroup != null) {
            workGroup.shutdownGracefully().sync();
        }
    }

    /**
     * init a new thread
     */
    @PostConstruct
    public void init() {
        //开启一个新的线程执行netty server服务
        new Thread(() -> {
            try {
                start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
