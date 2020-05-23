package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.pojo.*;

import java.util.List;
/**
 * @Author: kira
 * @Date: 2020/4/21 22:50
 * @Description:
 */
public interface EventTaskMapper {

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
