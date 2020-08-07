package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.Media;
import com.kira.emercmdplat.pojo.Task;
import com.kira.emercmdplat.pojo.TaskExtend;
import com.kira.emercmdplat.pojo.Feedback;

import java.util.List;
import java.util.Map;

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

    List<Feedback> selectFeedbackByTaskId(Long taskId, boolean isApp);

    List<Task> selectByTaskType(Map<String, Object> paramMap);

    Feedback selectLatestFeedbackByTaskId(Long taskId);

    int insertMedia(Media media);

    boolean updateMedia(Media media);
}
