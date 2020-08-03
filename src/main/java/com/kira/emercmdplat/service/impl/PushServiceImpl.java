package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.config.NettyConfig;
import com.kira.emercmdplat.config.WebSecurityConfig;
import com.kira.emercmdplat.mapper.PointMapper;
import com.kira.emercmdplat.pojo.Point;
import com.kira.emercmdplat.pojo.WebSocketUser;
import com.kira.emercmdplat.service.PushService;
import com.kira.emercmdplat.utils.DateUtil;
import com.kira.emercmdplat.utils.RedisUtil;
import com.kira.emercmdplat.utils.StringUtil;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: kira
 * @Date: 2020/7/24 12:02
 * @Description:推送service
 */
@Service
public class PushServiceImpl implements PushService {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private PointMapper pm;
//    /**
//     * 单个推送
//     *
//     * @param userToken 用户标志
//     * @param msg       推送内容
//     */
//    @Override
//    public void pushToSingle(WebSocketUser webSocketUser, String msg) {
//        ConcurrentHashMap<WebSocketUser, Channel> userChannelMap = NettyConfig.getUserChannelMap();
//
//        Channel channel = userChannelMap.get(webSocketUser);
//        channel.writeAndFlush(new TextWebSocketFrame(msg));
//    }

    /**
     * 推送给所有人
     *
     * @param msg 推送内容
     */
    @Override
    public void pushMsg(Point point) {
        ConcurrentHashMap<WebSocketUser, Channel> userChannelMap = NettyConfig.getUserChannelMap();
        String str = JSONObject.fromObject(point).toString();
        if (userChannelMap.keySet().size() > 0) {
            for (WebSocketUser webSocketUser : userChannelMap.keySet()) {
                if (webSocketUser.getResourceId() == point.getResourceId() && webSocketUser.getResourceType() == point.getResourceType()) {
                    Channel channel = userChannelMap.get(webSocketUser);
                    channel.writeAndFlush(new TextWebSocketFrame(str));
                }
            }
        }
        String item = point.getResourceId() + "::" + point.getReportTimeStamp() + "::" + point.getResourceType();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lng", point.getLng());
        jsonObject.put("lat", point.getLat());
        redisUtil.hset(WebSecurityConfig.RESOURCE_POINT_KEY, item, jsonObject.toString());
    }

    @Transactional
    @Override
    public void transPointFromRedisToDB() {
        Long begin = DateUtil.getNowTimestamp().getTime();
        Cursor<Map.Entry<Object, Object>> cursor = redisUtil.scan(WebSecurityConfig.RESOURCE_POINT_KEY);
        List<Point> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            Object obj = entry.getValue();
            JSONObject json = JSONObject.fromObject(obj);
            String[] split = key.split("::");
            Point point = new Point();
            point.setLat(json.getDouble("lat"));
            point.setLng(json.getDouble("lng"));
            point.setResourceId(StringUtil.toLongDefValue(split[0], 0l));
            point.setReportTimeStamp(StringUtil.toLongDefValue(split[1], 0l));
            point.setResourceType(StringUtil.toIntDefValue(split[2], 0));

            //组装成 UserLike 对象
            list.add(point);
            //存到 list 后从 Redis 中删除
            redisUtil.hdel(WebSecurityConfig.RESOURCE_POINT_KEY, key);
        }
        if (list.size() > 0) {
            pm.insertBatch(list);
        }
        //关闭游标
        try {
            cursor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Long end = DateUtil.getNowTimestamp().getTime();
        System.out.println("耗时：" + (end - begin) + "ms");
    }
}
