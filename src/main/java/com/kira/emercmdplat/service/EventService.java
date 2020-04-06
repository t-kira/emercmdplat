package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.Event;
import com.kira.emercmdplat.pojo.EventDevelopment;
import com.kira.emercmdplat.pojo.EventParam;
import com.kira.emercmdplat.service.base.BaseService;

/**
 * @Author: kira
 * @Date: 2020/4/6 22:47
 * @Description:
 */
public interface EventService extends BaseService<Event> {
    /**
     * 新增事件参数
     * @param eventParam
     * @return
     */
    int insertParam(EventParam eventParam);

    /**
     * 新增事件发展
     * @param eventDevelopment
     * @return
     */
    int insertDevelopment(EventDevelopment eventDevelopment);

    /**
     * 修改事件发展内容
     * @param eventDevelopment
     * @return
     */
    int updateDevelopment(EventDevelopment eventDevelopment);
}
