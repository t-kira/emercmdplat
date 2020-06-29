package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.enums.TaskStatus;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.ContactService;
import com.kira.emercmdplat.service.EventService;
import com.kira.emercmdplat.service.impl.TaskServiceImpl;
import com.kira.emercmdplat.utils.*;
import com.kira.emercmdplat.utils.file.FileuploadUtil;
import net.sf.json.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    private ContactService cs;


    @ResponseBody
    @PostMapping(value = "login")
    public AlvesJSONResult login(@RequestBody Contacts contacts, HttpServletRequest request) {
        if (null == contacts.getUsername() || null == contacts.getPassword()) {
            return AlvesJSONResult.errorMsg("用户名密码不能为空");
        }
        ContactsResult user = cs.selectByUserName(contacts.getUsername());
        if (user == null || !StringUtil.isEq(user.getPassword(), MD52.MD5Encode(contacts.getPassword()))) {
            return AlvesJSONResult.errorMsg("用户名或密码错误");
        } else {
            TokenVO tokenVo = cs.createToken(user);
            JSONObject json = new JSONObject();
            json.put("loginToken", tokenVo);
            json.put("user", user);
            return AlvesJSONResult.ok(json);
        }
    }

    /**
     * 事件查询接口，查询app登录用户所上报的事件列表
     * @param event
     * @return
     */
    @ResponseBody
    @PostMapping("query_event")
    public AlvesJSONResult queryEventList(@RequestBody Event event, HttpServletRequest request) {
        String token = TokenUtil.getRequestToken(request);
        ContactsResult contactsResult = cs.findByToken(token);
        event.setDid(contactsResult.getId());
        List<EventResult> eventResultList = es.queryByTitle(event);
        return AlvesJSONResult.ok(eventResultList);
    }

    /**
     * 返回登录人员所属的单位信息
     * @param contactId 联系人ID
     * @return
     */
    @ResponseBody
    @GetMapping("mechanism/{contactId}")
    public AlvesJSONResult mechanism(@PathVariable Long contactId) {
        ContactsResult contactsResult = cs.selectById(contactId);
        JSONObject resultJson = new JSONObject();
        resultJson.put("mId", contactsResult.getmId());
        resultJson.put("mName", contactsResult.getmName());
        return AlvesJSONResult.ok(resultJson);
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
        if (taskExtend.getStatus() == TaskStatus.TASK_PROCESSING.getNo()) {
            taskExtend.setResponseTime(DateUtil.getNowStr("yyyy-MM-dd HH:mm:ss"));
            //事件任务完成按钮，添加事件任务的完成时间
        } else if(taskExtend.getStatus() == TaskStatus.TASK_PROCESSED.getNo()) {
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
     * 查询任务
     * @param taskExtend
     * @return
     */
    @ResponseBody
    @PostMapping("list_task")
    public AlvesJSONResult eventTaskList(@RequestBody TaskExtend taskExtend, HttpServletRequest request) {
        String token = TokenUtil.getRequestToken(request);
        ContactsResult contactsResult = cs.findByToken(token);
        taskExtend.setContactId(contactsResult.getId());
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
        feedback.setFeedbackTime(DateUtil.getNowStr("yyyy-MM-dd HH:mm:ss"));
        int result = ts.insertFeedback(feedback);
        if (result > 0) {
            return AlvesJSONResult.ok("success insert...");
        } else {
            return AlvesJSONResult.errorMsg("fail insert...");
        }
    }
    @ResponseBody
    @GetMapping("arrive/{taskId}")
    public AlvesJSONResult arrive(@PathVariable Long taskId) {
        TaskExtend taskExtend = new TaskExtend();
        taskExtend.setId(taskId);
        taskExtend.setIsArrive(0);
        taskExtend.setArriveTime(DateUtil.getNowStr("yyyy-MM-dd HH:mm:ss"));
        boolean result = ts.update(taskExtend);
        if (result) {
            return AlvesJSONResult.ok("success arrive...");
        } else {
            return AlvesJSONResult.errorMsg("fail arrive ...");
        }
    }

    @ResponseBody
    @PostMapping("upload_files")
    public AlvesJSONResult upload(@RequestBody FilesReq filesReq) {
        String path = PropertiesUtils.getInstance().getProperty("attachmentPath").toString();
        String attachmentGainPath = PropertiesUtils.getInstance().getProperty("attachmentGainPath").toString();
        List<String> fileList = FileuploadUtil.saveFileByBase64(filesReq, path, attachmentGainPath);
        return AlvesJSONResult.ok(fileList);
    }
}
