package com.kira.emercmdplat.websocket;

import com.kira.emercmdplat.config.NettyConfig;
import com.kira.emercmdplat.pojo.WebSocketUser;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: kira
 * @Date: 2020/7/24 10:39
 * @Description:
 */
@Component
@ChannelHandler.Sharable
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static final Logger log = LoggerFactory.getLogger(WebSocketHandler.class);



    /**
     * Do nothing by default, sub-classes may override this method.
     *
     * @param ctx
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerAdded 被调用"+ctx.channel().id().asLongText());
        NettyConfig.getChannelGroup().add(ctx.channel());
    }

    /**
     * <strong>Please keep in mind that this method will be renamed to
     * {@code messageReceived(ChannelHandlerContext, I)} in 5.0.</strong>
     * <p>
     * Is called for each message of type {@link I}.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *            belongs to
     * @param msg the message to handle
     * @throws Exception is thrown if an error occurred
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        log.info("服务器接收到的消息: {}", msg.text());

        JSONObject jsonObject = JSONObject.fromObject(msg.text());
        long resourceId = 0;
        if (jsonObject.containsKey("resourceId")) {
            resourceId = jsonObject.getLong("resourceId");
        }
        if (jsonObject.containsKey("token")) {
            String token = jsonObject.getString("token");

            WebSocketUser webSocketUser = new WebSocketUser();
            webSocketUser.setResourceId(resourceId);
            webSocketUser.setToken(token);
            if (NettyConfig.getUserChannelMap().containsKey(webSocketUser)) {
                NettyConfig.getUserChannelMap().remove(webSocketUser);
            }
            NettyConfig.getUserChannelMap().put(webSocketUser, ctx.channel());

            AttributeKey<WebSocketUser> key = AttributeKey.valueOf("userToken");
            ctx.channel().attr(key).set(webSocketUser);
//            ctx.channel().attr(key).setIfAbsent(webSocketUser);
        }
    }

    /**
     * Do nothing by default, sub-classes may override this method.
     *
     * @param ctx
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerRemoved 被调用" + ctx.channel().id().asLongText());
        NettyConfig.getChannelGroup().remove(ctx.channel());
        removeUserToken(ctx);
        ctx.close();
    }

    /**
     * Calls {@link ChannelHandlerContext#fireExceptionCaught(Throwable)} to forward
     * to the next {@link ChannelHandler} in the {@link ChannelPipeline}.
     * <p>
     * Sub-classes may override this method to change behavior.
     *
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("异常: {}", cause.getMessage());

        NettyConfig.getChannelGroup().remove(ctx.channel());
        removeUserToken(ctx);
        ctx.close();
    }

    private void removeUserToken(ChannelHandlerContext ctx) {
        AttributeKey<WebSocketUser> key = AttributeKey.valueOf("userToken");
        WebSocketUser webSocketUser = ctx.channel().attr(key).get();
        NettyConfig.getUserChannelMap().remove(webSocketUser);
    }

}
