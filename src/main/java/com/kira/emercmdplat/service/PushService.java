package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.Point;

/**
 * @Author: kira
 * @Date: 2020/7/24 11:59
 * @Description:
 */
public interface PushService {
//    /**
//     * 单个推送
//     * @param userToken 用户标志
//     * @param msg 推送内容
//     */
//    void pushToSingle(WebSocketUser webSocketUser, String msg);

    /**
     * 推送给所有人
     * @param msg 推送内容
     */
    void pushMsg(Point point);

    void transPointFromRedisToDB();
}
