package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/6 22:47
 * @Description:
 */
public interface EventService{
    int insert(Event event, HttpServletRequest request);

    boolean delete(Event event);

    boolean update(Event event);

    EventResult selectById(Long id);

    List<EventResult> queryForAll(EventExtend eventExtend);

    Long queryForCounts(EventExtend eventExtend);

    int insertParam(EventParam eventParam);

    int deleteParam(Long id);

    List<EventResult> queryForPage(EventExtend eventExtend);

    List<EventParamResult> selectParamByEventId(Long eventId);

    //app事件标题模糊查询
    List<EventResult> queryByTitle(Event event);

}
