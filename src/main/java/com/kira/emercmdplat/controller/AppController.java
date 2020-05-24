package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.enums.TaskStatus;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.EventService;
import com.kira.emercmdplat.service.impl.TaskServiceImpl;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import com.kira.emercmdplat.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/5/23 15:23
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/app")
public class AppController extends BaseController {

    @Autowired
    private EventService es;

    @Autowired
    private TaskServiceImpl ts;

    /**
     * 事件查询接口，查询app登录用户所上报的事件列表
     * @param event
     * @return
     */
    @ResponseBody
    @PostMapping("query_event")
    public AlvesJSONResult queryEventList(@RequestBody Event event) {
        List<EventResult> eventResultList = es.queryByTitle(event);
        return AlvesJSONResult.ok(eventResultList);
    }

    /**
     * 开始处理 处理完成事件任务
     * @param taskExtend
     * @return
     */
    @ResponseBody
    @PostMapping("update_task")
    public AlvesJSONResult updateTask(@RequestBody TaskExtend taskExtend) {
            //开始处理事件任务,添加事件任务的响应时间
        if (taskExtend.getStatus().equals(TaskStatus.TASK_PROCESSING.getNo())) {
            taskExtend.setResponseTime(DateUtil.getNowStr("yyyy-MM-dd HH:mm:ss"));
            //事件任务完成按钮，添加事件任务的完成时间
        } else if(taskExtend.getStatus().equals(TaskStatus.TASK_PROCESSED.getNo())) {
            taskExtend.setEndTime(DateUtil.getNowStr("yyyy-MM-dd HH:mm:ss"));
        }
        boolean result = ts.update(taskExtend);
        if (result) {
            return AlvesJSONResult.ok("success update...");
        } else {
            return AlvesJSONResult.errorMsg("fail update...");
        }
    }

    /**
     * 查询事件任务
     * @param taskExtend
     * @return
     */
    @ResponseBody
    @PostMapping("list_task")
    public AlvesJSONResult eventTaskList(@RequestBody TaskExtend taskExtend) {
        List<Task> taskList = ts.queryForAll(taskExtend);
        return AlvesJSONResult.ok(taskList);
    }

    /**
     * 查看事件任务详情
     * @param id 事件任务ID
     * @return
     */
    @ResponseBody
    @GetMapping("task/{id}")
    public AlvesJSONResult eventTask(@PathVariable Long id) {
        Task task = ts.selectById(id);
        return AlvesJSONResult.ok(task);
    }

    /**
     * 查询反馈信息集合
     * @param taskId 事件任务ID
     * @return
     */
    @ResponseBody
    @GetMapping("list_feedback/{taskId}")
    public AlvesJSONResult feedbackList(@PathVariable Long taskId) {
        List<Feedback> feedbackList = ts.selectFeedbackByTaskId(taskId);
        return AlvesJSONResult.ok(feedbackList);
    }

    /**
     * 添加反馈任务 并设置事件任务为已到场状态，添加事件任务到场事件
     * @param feedback
     * @return
     */
    @ResponseBody
    @PostMapping("add_feedback")
    public AlvesJSONResult insertFeedback(@RequestBody Feedback feedback) {
        int result = ts.insertFeedback(feedback);
        if (result > 0) {
            TaskExtend taskExtend = new TaskExtend();
            taskExtend.setId(feedback.getTaskId());
            taskExtend.setArriveTime(DateUtil.getNowStr("yyyy-MM-dd HH:mm:ss"));
            taskExtend.setIsArrive(1);
            ts.update(taskExtend);
            return AlvesJSONResult.ok("success insert...");
        } else {
            return AlvesJSONResult.errorMsg("fail insert...");
        }
    }
}
