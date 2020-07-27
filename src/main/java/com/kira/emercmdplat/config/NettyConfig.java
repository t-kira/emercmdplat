package com.kira.emercmdplat.config;

import com.kira.emercmdplat.pojo.WebSocketUser;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: kira
 * @Date: 2020/7/24 10:27
 * @Description:
 */
public class NettyConfig {

    /**
     * channel组
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    /**
     * 存放用户与channel的对应信息，根据channel向客户端发送消息
     */
    private static ConcurrentHashMap<WebSocketUser, Channel> userChannelMap = new ConcurrentHashMap<>();

    private NettyConfig() {}

    public static ChannelGroup getChannelGroup() {
        return channelGroup;
    }

    public static ConcurrentHashMap<WebSocketUser, Channel> getUserChannelMap() {
        return userChannelMap;
    }
}
