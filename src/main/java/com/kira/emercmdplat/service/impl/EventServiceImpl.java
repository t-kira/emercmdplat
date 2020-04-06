package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.EventMapper;
import com.kira.emercmdplat.pojo.Event;
import com.kira.emercmdplat.pojo.EventDevelopment;
import com.kira.emercmdplat.pojo.EventParam;
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

    @Override
    public int insert(Event pojo) {
        return em.insert(pojo);
    }

    @Override
    public boolean delete(Event pojo) {
        return em.delete(pojo);
    }

    @Override
    public boolean update(Event pojo) {
        return em.update(pojo);
    }

    @Override
    public Event selectById(Integer id) {
        return em.selectById(id);
    }

    @Override
    public List<Event> queryForAll(Event pojo) {
        return em.queryForAll(pojo);
    }

    @Override
    public List<Event> queryForPage(Event pojo, Integer page, Integer pageSize) {
        Map<String, Object> paramMap = new HashMap<>();
        return em.queryForPage(paramMap);
    }

    @Override
    public Long queryForCounts(Event pojo) {
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
}
