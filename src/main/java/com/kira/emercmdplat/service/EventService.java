package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/4/6 22:47
 * @Description:
 */
public interface EventService{
    int insert(EventDomain eventDomain, HttpServletRequest request);

    boolean delete(Event event);

    boolean update(EventDomain eventDomain, HttpServletRequest request);

    boolean update(Event event);

    EventResult selectById(Long id);

    List<EventResult> queryForAll(Event event);

    Long queryForCounts(Event event);

    List<EventResult> queryForPage(Event event);

    List<EventParamResult> selectParamByEventId(Long eventId);

    //app事件标题模糊查询
    List<EventResult> queryByTitle(Event event);

    boolean mergeEvent(EventResult coverEvent, HttpServletRequest request, VerifyEventReq eventReq, EventResult mainEvent);

    Map<String,Object> statistics();

    boolean verifyEvent(VerifyEventReq eventReq, HttpServletRequest request);

    boolean mergeEvent(VerifyEventReq eventReq, HttpServletRequest request);
}
