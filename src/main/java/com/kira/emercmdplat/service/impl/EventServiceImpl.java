package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.EventMapper;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/6 22:47
 * @Description:事件service
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventMapper em;

    public int insert(Event event) {
        return em.insert(event);
    }

    @Override
    public boolean delete(Event event) {
        return em.delete(event);
    }

    @Override
    public boolean update(Event event) {
        return em.update(event);
    }

    @Override
    public EventResult selectById(Long id) {
        return em.selectById(id);
    }

    @Override
    public List<EventResult> queryForAll(EventExtend eventExtend) {
        return em.queryForAll(eventExtend);
    }

    @Override
    public List<EventResult> queryForPage(EventExtend eventExtend) {
        eventExtend.setPage((eventExtend.getPage() - 1) * eventExtend.getPageSize());
        return em.queryForPage(eventExtend);
    }

    @Override
    public Long queryForCounts(EventExtend pojo) {
        return em.queryForCounts(pojo);
    }

    @Override
    public int insertParam(EventParam eventParam) {
        return em.insertParam(eventParam);
    }

    @Override
    public int deleteParam(Long id) {
        return em.deleteParam(id);
    }

    @Override
    public List<EventParamResult> selectParamByEventId(Long eventId) {
        return em.selectParamByEventId(eventId);
    }

    @Override
    public List<EventResult> queryByTitle(Event event) {
        return em.queryByTitle(event);
    }
}
