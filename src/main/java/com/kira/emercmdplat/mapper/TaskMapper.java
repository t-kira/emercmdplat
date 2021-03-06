package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/4/21 22:50
 * @Description:
 */
public interface TaskMapper {

    int insert(Task task);

    boolean delete(Task task);

    boolean update(Task task);

    Task selectById(Long id);

    List<Task> queryForAll(TaskExtend taskExtend);

    List<Task> queryForPage(TaskExtend taskExtend);

    Long queryForCounts(TaskExtend taskExtend);

    int insertFeedback(Feedback feedback);

    List<Feedback> selectFeedbackByTaskId(Long taskId);

    List<Task> selectByTaskType(Map<String, Object> paramMap);

    Feedback selectLatestFeedbackByTaskId(Long taskId);

    int insertMedia(Media media);

    boolean updateMedia(Media media);
}
