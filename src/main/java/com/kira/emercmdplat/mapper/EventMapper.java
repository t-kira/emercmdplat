package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.mapper.base.BaseMapper;
import com.kira.emercmdplat.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/4/6 22:46
 * @Description:
 */
public interface EventMapper{

    int insert(Event event);

    boolean delete(Event event);

    boolean update(Event event);

    EventResult selectById(Long id);

    List<EventResult> queryForAll(EventExtend eventExtend);

    Long queryForCounts(EventExtend eventExtend);

    int insertParam(EventParam eventParam);

    List<EventResult> queryForPage(EventExtend eventExtend);

    List<EventParamResult> selectParamByEventId(Long eventId);
    //app事件标题模糊查询
    List<EventResult> queryByTitle(Event event);

    int deleteParam(Long id);

    Map<String, Integer> countEventLevel();

    List<Map<String, Object>> countEventPtId();

    Map<String, Object> selectParentId(Long ptId);
}
