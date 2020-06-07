package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.Task;
import com.kira.emercmdplat.pojo.TaskExtend;
import com.kira.emercmdplat.pojo.Feedback;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/5/22 22:19
 * @Description:
 */
public interface TaskService {

    int insert(TaskExtend taskExtend);

    boolean delete(TaskExtend taskExtend);

    boolean update(TaskExtend taskExtend);

    Task selectById(Long id);

    List<Task> queryForAll(TaskExtend taskExtend);

    List<Task> queryForPage(TaskExtend taskExtend);

    Long queryForCounts(TaskExtend taskExtend);

    int insertFeedback(Feedback feedback);

    List<Feedback> selectFeedbackByTaskId(Long taskId);

    List<Task> selectByTaskType(Integer taskType);

    Feedback selectLatestFeedbackByTaskId(Long taskId);
}
