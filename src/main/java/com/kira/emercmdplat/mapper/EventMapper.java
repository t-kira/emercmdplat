package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.mapper.base.BaseMapper;
import com.kira.emercmdplat.pojo.Event;
import com.kira.emercmdplat.pojo.EventDevelopment;
import com.kira.emercmdplat.pojo.EventParam;

/**
 * @Author: kira
 * @Date: 2020/4/6 22:46
 * @Description:
 */
public interface EventMapper extends BaseMapper<Event> {

    int insertParam(EventParam eventParam);

    int insertDevelopment(EventDevelopment eventDevelopment);

    int updateDevelopment(EventDevelopment eventDevelopment);
}
