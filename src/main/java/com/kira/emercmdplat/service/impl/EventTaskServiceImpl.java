package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.EventTaskMapper;
import com.kira.emercmdplat.pojo.EventTask;
import com.kira.emercmdplat.pojo.EventTaskExtend;
import com.kira.emercmdplat.pojo.EventTaskResult;
import com.kira.emercmdplat.pojo.Feedback;
import com.kira.emercmdplat.service.EventTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/5/22 22:19
 * @Description:
 */
@Service
public class EventTaskServiceImpl implements EventTaskService {

    @Autowired
    private EventTaskMapper etm;

    @Override
    public int insert(EventTask eventTask) {
        return etm.insert(eventTask);
    }

    @Override
    public boolean delete(EventTask eventTask) {
        return etm.delete(eventTask);
    }

    @Override
    public boolean update(EventTask eventTask) {
        return etm.update(eventTask);
    }

    @Override
    public EventTaskResult selectById(Long id) {
        return etm.selectById(id);
    }

    @Override
    public List<EventTaskResult> queryForAll(EventTaskExtend eventTaskExtend) {
        return etm.queryForAll(eventTaskExtend);
    }

    @Override
    public List<EventTaskResult> queryForPage(EventTaskExtend eventTaskExtend) {
        eventTaskExtend.setPage((eventTaskExtend.getPage() - 1) * eventTaskExtend.getPageSize());
        return etm.queryForPage(eventTaskExtend);
    }

    @Override
    public Long queryForCounts(EventTaskExtend eventTaskExtend) {
        return etm.queryForCounts(eventTaskExtend);
    }

    @Override
    public int insertFeedback(Feedback feedback) {
        return etm.insertFeedback(feedback);
    }

    @Override
    public List<Feedback> selectFeedbackByTaskId(Long taskId) {
        return etm.selectFeedbackByTaskId(taskId);
    }
}
