package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.EventTask;
import com.kira.emercmdplat.pojo.EventTaskExtend;
import com.kira.emercmdplat.pojo.EventTaskResult;
import com.kira.emercmdplat.pojo.Feedback;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/5/22 22:19
 * @Description:
 */
public interface EventTaskService {

    int insert(EventTask eventTask);

    boolean delete(EventTask eventTask);

    boolean update(EventTask eventTask);

    EventTaskResult selectById(Long id);

    List<EventTaskResult> queryForAll(EventTaskExtend eventTaskExtend);

    List<EventTaskResult> queryForPage(EventTaskExtend eventTaskExtend);

    Long queryForCounts(EventTaskExtend eventTaskExtend);

    int insertFeedback(Feedback feedback);

    List<Feedback> selectFeedbackByTaskId(Long taskId);
}
