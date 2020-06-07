package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/4/6 22:47
 * @Description:
 */
public interface EventService{
    int insert(Event event);

    boolean delete(Event event);

    boolean update(Event event);

    EventResult selectById(Long id);

    EventResult selectByEId(Long eId);

    List<EventResult> queryForAll(EventExtend eventExtend);

    Long queryForCounts(EventExtend eventExtend);

    int insertParam(EventParam eventParam);

    int insertDevelopment(EventDevelopment eventDevelopment);

    int updateDevelopment(EventDevelopment eventDevelopment);

    List<EventResult> queryForPage(EventExtend eventExtend);

    List<EventParamResult> selectParamByEId(Long eId);

    //app事件标题模糊查询
    List<EventResult> queryByTitle(Event event);

    int updateParam(List<EventParam> eventParamList);
}
