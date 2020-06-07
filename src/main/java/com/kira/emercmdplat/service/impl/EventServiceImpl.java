package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.EventMapper;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public int insertDevelopment(EventDevelopment eventDevelopment) {
        return em.insertDevelopment(eventDevelopment);
    }

    /**
     * 修改事件发展内容
     *
     * @param eventDevelopment
     * @return
     */
    @Override
    public int updateDevelopment(EventDevelopment eventDevelopment) {
        return em.updateDevelopment(eventDevelopment);
    }

    @Override
    public EventResult selectByEId(Long eId) {
        return em.selectByEId(eId);
    }

    @Override
    public List<EventParamResult> selectParamByEId(Long eId) {
        return em.selectParamByEId(eId);
    }

    @Override
    public List<EventResult> queryByTitle(Event event) {
        return em.queryByTitle(event);
    }

    @Override
    public int updateParam(List<EventParam> eventParamList) {
        if (eventParamList != null && eventParamList.size() >0) {
            for (EventParam eventParam : eventParamList) {
                em.updateParam(eventParam);
            }
        }
        return 1;
    }
}
