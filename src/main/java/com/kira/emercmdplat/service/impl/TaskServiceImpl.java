package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.ContactMapper;
import com.kira.emercmdplat.mapper.TaskMapper;
import com.kira.emercmdplat.pojo.ContactsResult;
import com.kira.emercmdplat.pojo.Task;
import com.kira.emercmdplat.pojo.TaskExtend;
import com.kira.emercmdplat.pojo.Feedback;
import com.kira.emercmdplat.service.TaskService;
import com.kira.emercmdplat.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public int insert(TaskExtend taskExtend) {
        try {
            //添加事件任务指派时间
            taskExtend.setStartTime(DateUtil.getNowStr("yyyy-MM-dd HH:mm:ss"));
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
                for (Map<String, String> contactMap : taskExtend.getContactList()) {
                    taskExtend.setContactName(contactMap.get("contactName"));
                    taskExtend.setTelephone(contactMap.get("telephone"));
                    tm.insert(taskExtend);
                }
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
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

    @Override
    public int insertFeedback(Feedback feedback) {
        return tm.insertFeedback(feedback);
    }

    @Override
    public List<Feedback> selectFeedbackByTaskId(Long taskId) {
        return tm.selectFeedbackByTaskId(taskId);
    }
}
