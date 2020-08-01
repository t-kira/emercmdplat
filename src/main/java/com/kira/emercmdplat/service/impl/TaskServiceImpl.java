package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.enums.EventProcess;
import com.kira.emercmdplat.enums.EventStatus;
import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.enums.TaskStatus;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.mapper.ContactMapper;
import com.kira.emercmdplat.mapper.EventMapper;
import com.kira.emercmdplat.mapper.TaskMapper;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.TaskService;
import com.kira.emercmdplat.utils.DateUtil;
import com.kira.emercmdplat.utils.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/5/22 22:19
 * @Description:
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper tm;
    @Autowired
    private ContactMapper cm;
    @Autowired
    private EventMapper em;

    @Override
    public int insert(TaskExtend taskExtend) {
        EventResult eventResult = em.selectById(taskExtend.getEventId());
        if (eventResult == null) {
            throw new CustomException(ResultEnum.NON_DATA.getNo());
        }
        if (eventResult.getStatus() == EventStatus.FINISH.getNo() && eventResult.getProcess() == EventProcess.EVENT_FINISH.getNo()) {
            throw new CustomException(ResultEnum.EVENT_FINISH.getNo());
        }
        try {
            //添加事件任务指派时间
            taskExtend.setStartTime(DateUtil.getNowStr());
            //默认未处理
            taskExtend.setStatus(TaskStatus.TASK_PENDING.getNo());
            //默认未到场
            taskExtend.setIsArrive(1);
            if (taskExtend.getTaskType() == 2 ) {
                taskExtend.setDataTypeId(20l);
            }
            if (taskExtend.getContactIdList() != null && taskExtend.getContactIdList().size() > 0) {
                for (Long contactId : taskExtend.getContactIdList()) {
                    ContactsResult contactsResult = cm.selectById(contactId);
                    taskExtend.setContactId(contactId);
                    taskExtend.setContactName(contactsResult.getContactName());
                    taskExtend.setTelephone(contactsResult.getTelephone());
                    tm.insert(taskExtend);
                }
            }
            if (taskExtend.getContactList() != null && taskExtend.getContactList().size() > 0) {
                for (JSONObject json : taskExtend.getContactList()) {
                    taskExtend.setContactName(StringUtil.toStr(json.get("contactName")));
                    taskExtend.setTelephone(json.getString("telephone"));
                    tm.insert(taskExtend);
                }
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "任务指派失败");
        }
    }

    @Override
    public boolean delete(TaskExtend taskExtend) {
        return tm.delete(taskExtend);
    }

    @Override
    public boolean update(TaskExtend taskExtend) {
        return tm.update(taskExtend);
    }

    @Override
    public Task selectById(Long id) {
        return tm.selectById(id);
    }

    @Override
    public List<Task> queryForAll(TaskExtend taskExtend) {
        return tm.queryForAll(taskExtend);
    }

    @Override
    public List<Task> queryForPage(TaskExtend taskExtend) {
        taskExtend.setPage((taskExtend.getPage() - 1) * taskExtend.getPageSize());
        return tm.queryForPage(taskExtend);
    }

    @Override
    public Long queryForCounts(TaskExtend taskExtend) {
        return tm.queryForCounts(taskExtend);
    }

    @Transactional
    @Override
    public int insertFeedback(Feedback feedback) {
        int result = tm.insertFeedback(feedback);
        if (result > 0) {
            if (feedback.getMediaList() != null && feedback.getMediaList().size() > 0) {
                for (Media media : feedback.getMediaList()) {
                    media.setFeedbackId(feedback.getId());
                    tm.updateMedia(media);
                }
            }
        }
        return 1;
    }

    @Override
    public List<Feedback> selectFeedbackByTaskId(Long taskId) {
        return tm.selectFeedbackByTaskId(taskId);
    }

    @Override
    public List<Task> selectByTaskType(Map<String, Object> paramMap) {
        return tm.selectByTaskType(paramMap);
    }

    @Override
    public Feedback selectLatestFeedbackByTaskId(Long taskId) {
        return tm.selectLatestFeedbackByTaskId(taskId);
    }

    @Override
    public int insertMedia(Media media) {
        return tm.insertMedia(media);
    }

    @Override
    public boolean updateMedia(Media media) {
        return tm.updateMedia(media);
    }
}
