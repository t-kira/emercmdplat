package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.annotation.MyLog;
import com.kira.emercmdplat.config.InitData;
import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.enums.*;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.ContactService;
import com.kira.emercmdplat.service.EventService;
import com.kira.emercmdplat.service.PushService;
import com.kira.emercmdplat.service.impl.TaskServiceImpl;
import com.kira.emercmdplat.utils.*;
import com.kira.emercmdplat.utils.file.FileResult;
import com.kira.emercmdplat.utils.file.FileuploadUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/5/23 15:23
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(name = "app接口", value = "/app")
public class AppController extends BaseController {

    @Autowired
    private EventService es;
    @Autowired
    private TaskServiceImpl ts;
    @Autowired
    private ContactService cs;
    @Autowired
    private PushService ps;

    @ResponseBody
    @PostMapping(name = "登录", value = "login")
    public AlvesJSONResult login(@RequestBody Contacts contacts) {
        if (null == contacts.getUsername() || null == contacts.getPassword()) {
            return AlvesJSONResult.errorMsg("用户名密码不能为空");
        }
        ContactsResult user = cs.selectByUserName(contacts.getUsername());
        if (user == null || !StringUtil.isEq(user.getPassword(), MD52.MD5Encode(contacts.getPassword()))) {
            throw new CustomException(ResultEnum.ERROR_PARAMETER.getNo(), "用户名密码错误");
        } else {
            TokenVO tokenVo = cs.createToken(user);
            JSONObject json = new JSONObject();
            json.put("loginToken", tokenVo);
            json.put("user", user);
            return AlvesJSONResult.ok(json);
        }
    }
    @ResponseBody
    @PostMapping(name = "事件查询接口，查询app登录用户所上报的事件列表", value = "query_event")
    public AlvesJSONResult queryEventList(@RequestBody Event event, HttpServletRequest request) {
        String token = TokenUtil.getRequestToken(request);
        ContactsResult contactsResult = cs.findByToken(token);
        event.setContactId(contactsResult.getId());
        List<EventResult> eventResultList = es.queryByTitle(event);
        return AlvesJSONResult.ok(eventResultList);
    }
    @ResponseBody
    @GetMapping(name = "返回登录人员所属的单位信息", value = "mechanism/{contactId}")
    public AlvesJSONResult mechanism(@PathVariable Long contactId) {
        ContactsResult contactsResult = cs.selectById(contactId);
        JSONObject resultJson = new JSONObject();
        resultJson.put("mId", contactsResult.getmId());
        resultJson.put("mName", contactsResult.getmName());
        return AlvesJSONResult.ok(resultJson);
    }
    @ResponseBody
    @PostMapping(name = "开始处理 处理完成事件任务", value = "update_task")
    public AlvesJSONResult updateTask(@RequestBody TaskExtend taskExtend) {
            //开始处理事件任务,添加事件任务的响应时间
        if (taskExtend.getStatus() == TaskStatus.TASK_PROCESSING.getNo()) {
            taskExtend.setResponseTime(DateUtil.getNowStr());
            //事件任务完成按钮，添加事件任务的完成时间
        } else if(taskExtend.getStatus() == TaskStatus.TASK_PROCESSED.getNo()) {
            taskExtend.setEndTime(DateUtil.getNowStr());
        }
        boolean result = ts.update(taskExtend);
        if (result) {
            return AlvesJSONResult.ok("任务处理成功");
        } else {
            return AlvesJSONResult.errorMsg("任务处理失败");
        }
    }
    @ResponseBody
    @PostMapping(name = "查询任务", value = "list_task")
    public AlvesJSONResult eventTaskList(@RequestBody(required = false) TaskExtend taskExtend, HttpServletRequest request) {
        String token = TokenUtil.getRequestToken(request);
        ContactsResult contactsResult = cs.findByToken(token);
        if (taskExtend == null) {
            taskExtend = new TaskExtend();
        }
        taskExtend.setContactId(contactsResult.getId());
        List<Task> taskList = ts.queryForAll(taskExtend);
        return AlvesJSONResult.ok(taskList);
    }
    @ResponseBody
    @GetMapping(name = "查看事件任务详情", value = "task/{id}")
    public AlvesJSONResult eventTask(@PathVariable Long id) {
        Task task = ts.selectById(id);
        return AlvesJSONResult.ok(task);
    }
    @ResponseBody
    @GetMapping(name = "查询反馈信息集合", value = "list_feedback/{taskId}")
    public AlvesJSONResult feedbackList(@PathVariable Long taskId) {
        List<Feedback> feedbackList = ts.selectFeedbackByTaskId(taskId, true);

        return AlvesJSONResult.ok(feedbackList);
    }
    @ResponseBody
    @PostMapping(name = "添加反馈任务 并设置事件任务为已到场状态，添加事件任务到场事件", value = "add_feedback")
    public AlvesJSONResult insertFeedback(@RequestBody Feedback feedback) {
        feedback.setFeedbackTime(DateUtil.getNowStr());
        int result = ts.insertFeedback(feedback);
        if (result > 0) {
            return AlvesJSONResult.ok("反馈成功");
        } else {
            return AlvesJSONResult.errorMsg("反馈失败");
        }
    }
    @ResponseBody
    @GetMapping(name = "指派任务到场", value = "arrive/{taskId}")
    public AlvesJSONResult arrive(@PathVariable Long taskId) {
        TaskExtend taskExtend = new TaskExtend();
        taskExtend.setId(taskId);
        taskExtend.setIsArrive(0);
        taskExtend.setArriveTime(DateUtil.getNowStr());
        boolean result = ts.update(taskExtend);
        if (result) {
            return AlvesJSONResult.ok("到场成功");
        } else {
            return AlvesJSONResult.errorMsg("到场失败");
        }
    }
    @ResponseBody
    @PostMapping(name = "文件上传", value = "upload_files")
    public AlvesJSONResult upload(@RequestBody FilesReq filesReq) {
        String path = PropertiesUtils.getInstance().getProperty("attachmentPath");
        String attachmentGainPath = PropertiesUtils.getInstance().getProperty("attachmentGainPath");
        List<String> fileList = FileuploadUtil.saveFileByBase64(filesReq, path, attachmentGainPath);
        return AlvesJSONResult.ok(fileList);
    }
    @MyLog(value = 1)
    @ResponseBody
    @PostMapping(name = "手机端事件接报", value = "add_event")
    public AlvesJSONResult insert(@RequestBody EventDomain eventDomain, HttpServletRequest request) {
//      Event event = eventDomain.getEvent();
        int result = es.insert(eventDomain, request);
        if (result > 0) {
            return AlvesJSONResult.ok("录入成功");
        } else {
            return AlvesJSONResult.errorMsg("录入失败");
        }
    }
    @ResponseBody
    @PostMapping(name = "获取实时坐标位置", value = "point_receive")
    public AlvesJSONResult receiveCurrentPoint(@RequestBody Point point) {
        point.setReportTimeStamp(DateUtil.getNowTimestamp().getTime());
        point.setResourceType(PointResourceType.AUTO.getNo());
        int random = (int)(1 + Math.random() * (10 -1 + 1));
        double d = random / 100.0;
        double lat = point.getLat();
        double lng = point.getLng();
        point.setLat(lat + d);
        point.setLng(lng + d);
        ps.pushMsg(point);
        Integer time = StringUtil.toIntDefValue(InitData.getVal(BaseDataType.REPORT_TIME.getNo()), 1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("time", time);
        return AlvesJSONResult.ok(jsonObject);
    }

    @ResponseBody
    @PostMapping(name = "附件上传", value = "fileUpload")
    public AlvesJSONResult upload(@RequestParam(value = "file") MultipartFile file, @RequestParam int mediaType) {
        try {
            //#服务器静态文件地址
            String path = PropertiesUtils.getInstance().getProperty("mediaPath");
            String extension = PropertiesUtils.getInstance().getProperty("mediaExtension");
            FileResult fileResult = FileuploadUtil.saveFile(file, path, extension);
            Media media = new Media();
            media.setMediaType(mediaType);
            media.setMediaUrl(fileResult.getServerPath());
            int result = ts.insertMedia(media);
            if (result > 0) {
                //临时处理
                String videoHtml = InitData.getVal(BaseDataType.VIDEO_HTML.getNo());
                String audioHtml = InitData.getVal(BaseDataType.AUDIO_HTML.getNo());
                String baseUrl = InitData.getVal(BaseDataType.URL.getNo());
                String param = InitData.getVal(BaseDataType.PARAM.getNo());
                StringBuffer buffer = new StringBuffer(baseUrl);
                if (mediaType == MediaType.AUDIO.getNo())
                    buffer.append(audioHtml);
                else
                    buffer.append(videoHtml);
                buffer.append(param).append(baseUrl).append(media.getMediaUrl());
                media.setMediaUrl(buffer.toString());
                return AlvesJSONResult.ok(media);
            }else {
                return AlvesJSONResult.errorMsg("上传失败");
            }
        } catch (IOException e) {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "附件上传失败");
        }
    }
}
