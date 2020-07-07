package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.annotation.MyLog;
import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.enums.*;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.*;
import com.kira.emercmdplat.utils.*;
import com.kira.emercmdplat.utils.file.FileResult;
import com.kira.emercmdplat.utils.file.FileuploadUtil;
import net.sf.json.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * @Author: kira
 * @Date: 2020/4/6 22:49
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping(name = "事件接口", value = "/event")
public class EventController extends BaseController {
    @Autowired
    private EventService es;
    @Autowired
    private PlanTypeService pts;
    @Autowired
    private MechanismService ms;
    @Autowired
    private VerifyReportService vrs;
    @Autowired
    private PlanVersionService pvs;
    @Autowired
    private LeaderInstructService lis;
    @Autowired
    private ReservePlanService rps;
    @Autowired
    private MessageService mas;
    @Autowired
    private SysLogService sls;
    @Autowired
    private ContactService cs;
    @Autowired
    private QuickReportService qrs;

    @MyLog(value = 1)
    @ResponseBody
    @PostMapping(name = "事件接报", value = "add")
    public AlvesJSONResult insert(@RequestBody EventDomain eventDomain, HttpServletRequest request) {
        Event event = eventDomain.getEvent();
        List<EventParam> eventParamList = eventDomain.getEventParamList();
        String preEventNumber = DateUtil.getNowStr("yyyyMMdd");
        EventExtend eventExtend = new EventExtend();
        eventExtend.setOrder("e_id");
        eventExtend.setOrderType("desc");
        eventExtend.setEventNumber(preEventNumber);
        List<EventResult> eventResults = es.queryForAll(eventExtend);
        if (eventResults != null && eventResults.size() > 0) {
            EventResult eventResult = eventResults.get(0);
            String eventNumber = eventResult.getEventNumber();
            event.setEventNumber(preEventNumber + StringUtil.genEventNumber(eventNumber));
        } else {
            event.setEventNumber(preEventNumber + "00001");
        }
        String token = TokenUtil.getRequestToken(request);
        ContactsResult contactsResult = cs.findByToken(token);
        event.setContactId(contactsResult.getId());
        event.setVerifyStatus(0);
        event.setProcess(EventProcess.EVENT_RECEIVE.getNo());
        event.setReceiveTime(DateUtil.getNowStr("yyy-MM-dd HH:mm:ss"));
        int result = es.insert(event);
        if (result > 0) {
            if (eventParamList != null && eventParamList.size() > 0) {
                for (EventParam eventParam : eventParamList) {
                    eventParam.setEventId(event.getId());
                    eventParam.setEventNumber(event.getEventNumber());
                    es.insertParam(eventParam);
                }
            } else {
                throw new CustomException(ResultEnum.MISSING_PARAMETER.getNo());
            }
            return AlvesJSONResult.ok(event);
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo());
        }
    }
    @MyLog(value = 7)
    @ResponseBody
    @PostMapping(name = "终结事件", value = "end")
    public AlvesJSONResult end(@RequestBody Event event) {
        event.setProcess(EventProcess.EVENT_FINISH.getNo());
        boolean result = es.update(event);
        if (result) {
            return AlvesJSONResult.ok("success close");
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo());
        }
    }
    @ResponseBody
    @GetMapping(name = "获取事件类型列表", value = "type_list")
    public AlvesJSONResult listPlanType() {
        List<Node> list = pts.listTypeTree(null);
        return AlvesJSONResult.ok(list);
    }
    @ResponseBody
    @GetMapping(name = "根据事件类型返回事件参数集合", value = "event_param/{id}")
    public AlvesJSONResult listParam(@PathVariable int id) {
        List<PlanParam> planParamList = pts.listParamsByPtId(id,true);
        return AlvesJSONResult.ok(planParamList);
    }
    @ResponseBody
    @GetMapping(name = "获取值班人员集合", value = "duty_list")
    public AlvesJSONResult listDuty(){
        ContactsExtend contactsExtend = new ContactsExtend();
        contactsExtend.setPersonAttribute(1);
        List<ContactsResult> contactsList = cs.queryForAll(contactsExtend);
        return AlvesJSONResult.ok(contactsList);
    }
    @ResponseBody
    @GetMapping(name = "获取值班人员信息", value = "duty/{contactId}")
    public AlvesJSONResult selectDuty(@PathVariable Long contactId){
        ContactsResult contactsResult = cs.selectById(contactId);
        return AlvesJSONResult.ok(contactsResult);
    }
    @ResponseBody
    @GetMapping(name = "查询机构集合", value = "mechanism_list")
    public AlvesJSONResult listMechanism() {
        List<Mechanism> mechanismList = ms.queryForAll(new Mechanism());
        return AlvesJSONResult.ok(mechanismList);
    }
    @MyLog(value = 3)
    @ResponseBody
    @PostMapping(name = "添加核实报告内容", value = "add_verify")
    public AlvesJSONResult insertVerifyReport(@RequestBody VerifyReport verifyReport) {
//        VerifyReport verifyReport = verifyQuickReport.getVerify();
        int result = vrs.insert(verifyReport);
        if (result > 0) {
            EventResult eventResult = es.selectById(verifyReport.getEventId());
            QuickReport quickReport = new QuickReport();
            quickReport.setTitle(eventResult.getEventTitle());
            quickReport.setContent(eventResult.getEventDesc());
            quickReport.setEventId(verifyReport.getEventId());
            quickReport.setOrigin(1);
            quickReport.setEditId(eventResult.getContactId());
            quickReport.setIssueTime(DateUtil.getNowStr("yyyy-MM-dd HH:mm:ss"));
            JSONObject json = new JSONObject();
            json.put("richText", verifyReport.getCreateTime());

            // 文件的实际路径
            String path = PropertiesUtils.getInstance().getProperty("attachmentPath");
            String attachmentGainPath = PropertiesUtils.getInstance().getProperty("attachmentGainPath");
            String uuid = UUID.randomUUID().toString();

            String toPath = FilenameUtils.separatorsToSystem(attachmentGainPath + path + uuid + ".pdf");
            String content = PDFTemplateUtil.freeMarkerRender(json, "/ftlFile/pdf.ftl");
            try {
                PDFTemplateUtil.createPdf(content, toPath);
                verifyReport.setQuickReportAddr(path + uuid + ".pdf");
                vrs.update(verifyReport);
            } catch (Exception e) {
                e.printStackTrace();
            }
            quickReport.setPdfAddr(path + uuid + ".pdf");
            quickReport.setSubmitId(eventResult.getContactId().intValue());
            qrs.insert(quickReport);
            //生成PDF
            Event event = es.selectById(verifyReport.getEventId());
            event.setId(verifyReport.getEventId());
            event.setProcess(EventProcess.VERIFY_REPORT.getNo());
            //新增一条消息
            Message message = new Message();
            message.setEventId(event.getId());
            message.setContactId(verifyReport.getContactId());
            message.setVId(verifyReport.getId());
            message.setStatus(MessageStatus.MESSAGE_UNREAD.getNo());
            message.setType(0);
            mas.insert(message);

            es.update(event);
            ReservePlan reservePlan = new ReservePlan();
            reservePlan.setEventId(verifyReport.getEventId());
            reservePlan.setStatus(ReservePlanStatus.UNEDIT.getNo());
            reservePlan.setStartTime(DateUtil.getNowStr("yyyy-MM-dd HH:mm:ss"));
            rps.insert(reservePlan);
            return AlvesJSONResult.ok(EventProcess.VERIFY_REPORT.getNo());
        } else {
            return AlvesJSONResult.errorMsg("fail insert verifyReport...");
        }
    }
    @ResponseBody
    @GetMapping(name = "根据事件ID查询核实报告", value = "verify/{eventId}")
    public AlvesJSONResult selectVerifyReportByEventId(@PathVariable Long eventId) {
        VerifyReport verifyReport = new VerifyReport();
        verifyReport.setEventId(eventId);
        List<VerifyReport> verifyReportList = vrs.queryForAll(verifyReport);
        if (verifyReportList != null && verifyReportList.size() > 0) {
            return AlvesJSONResult.ok(verifyReportList.get(0));
        } else {
            return AlvesJSONResult.errorMsg("verify report is null ...");
        }
    }
    @ResponseBody
    @PostMapping(name = "附件上传", value = "fileUpload")
    public AlvesJSONResult upload(@RequestParam(value = "file") MultipartFile file) {
        try {
            //#服务器静态文件地址
            String path = PropertiesUtils.getInstance().getProperty("attachmentPath");
            String extension = PropertiesUtils.getInstance().getProperty("imageExtension");
            FileResult fileResult = FileuploadUtil.saveFile(file, path, extension);
            return AlvesJSONResult.ok(fileResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AlvesJSONResult.errorMsg("fail upload...");
    }
    @ResponseBody
    @GetMapping(name = "初判预案列表", value = "plan_version_list/{id}")
    public AlvesJSONResult listPlanVersion(@PathVariable int id) {
        PlanVersion planVersion = new PlanVersion();
        planVersion.setType(id);
        planVersion.setStatus(4);
        List<PlanVersion> planVersionList = pvs.listAllVersions(planVersion);
        return AlvesJSONResult.ok(planVersionList);
    }
    @ResponseBody
    @GetMapping(name = "初判等级列表", value = "plan_response_list/{id}")
    public AlvesJSONResult listPlanResponse(@PathVariable int id) {
        List<PlanResponse> responseList = pvs.listResponses(id,1);
        return AlvesJSONResult.ok(responseList);
    }
    //需要上传ID
    @ResponseBody
    @GetMapping(name = "查询事件领导批示集合", value = "leader_instruct_list/{eventId}")
    public AlvesJSONResult listLeaderInstruct(@PathVariable Long eventId) {
        LeaderInstructExtend leaderInstruct = new LeaderInstructExtend();
        leaderInstruct.setEventId(eventId);
        List<LeaderInstructResult> list = lis.queryForAll(leaderInstruct);
        return AlvesJSONResult.ok(list);
    }
    @ResponseBody
    @GetMapping(name = "根据事件ID查询领导批示记录", value = "leader_instruct/{eventId}")
    public AlvesJSONResult selectLeaderInstructByEventId(@PathVariable Long eventId) {
        LeaderInstructExtend leaderInstruct = new LeaderInstructExtend();
        leaderInstruct.setEventId(eventId);
        List<LeaderInstructResult> list = lis.queryForAll(leaderInstruct);
        if (list != null && list.size() > 0) {
            return AlvesJSONResult.ok(list.get(0));
        } else {
            return AlvesJSONResult.errorMsg("verify report is null ...");
        }
    }
    @ResponseBody
    @GetMapping(name = "根据事件ID查询预案", value = "reserve_plan_list/{eventId}")
    public AlvesJSONResult selectReservePlanByEventId(@PathVariable Long eventId) {
        List<ReservePlanResult> list = rps.selectByEventId(eventId);
        return AlvesJSONResult.ok(list);
    }
    @ResponseBody
    @GetMapping(name = "查询预案", value = "reserve_plan/{id}")
    public AlvesJSONResult selectReservePlan(@PathVariable Long id) {
        ReservePlanResult reservePlanResult = rps.selectById(id);
        return AlvesJSONResult.ok(reservePlanResult);
    }
    @MyLog(value = 5)
    @ResponseBody
    @PostMapping(name = "启动预案", value = "reserve_start")
    public AlvesJSONResult startReservePlan(@RequestBody ReservePlanResult reservePlanResult) {
        VerifyReport verifyReport = new VerifyReport();
        verifyReport.setId(reservePlanResult.getVrId());
        verifyReport.setPrId(reservePlanResult.getPrId());
        boolean result = vrs.update(verifyReport);
        if (result) {
            reservePlanResult.setStartTime(DateUtil.getNowStr("yyyy-MM-dd HH:mm:ss"));
                Event event = new Event();
                event.setId(reservePlanResult.getEventId());
                event.setProcess(EventProcess.RESERVE_PLAN.getNo());
                es.update(event);
            rps.update(reservePlanResult);
            return AlvesJSONResult.ok("ok start...");
        } else {
            return AlvesJSONResult.errorMsg("fail start reserve...");
        }
    }
    @MyLog(value = 8)
    @ResponseBody
    @PostMapping(name = "删除事件", value = "remove")
    public AlvesJSONResult delete(Event event) {
        boolean result = es.delete(event);
        if (result) {
            return AlvesJSONResult.ok();
        } else {
            return AlvesJSONResult.errorMsg("fail delete...");
        }
    }
    @MyLog(value = 9)
    @ResponseBody
    @PostMapping(name = "事件更新", value = "update")
    public AlvesJSONResult update(@RequestBody EventDomain eventDomain, HttpServletRequest request) {
        Event event = eventDomain.getEvent();
        List<EventParam> eventParamList = eventDomain.getEventParamList();
        String token = TokenUtil.getRequestToken(request);
        ContactsResult contactsResult = cs.findByToken(token);
        event.setContactId(contactsResult.getId());
        boolean result = es.update(event);
        if (result) {
            List<EventParamResult> paramList =  es.selectParamByEventId(event.getId());
            if (paramList != null && paramList.size() > 0) {
                for (EventParamResult param : paramList) {
                    es.deleteParam(param.getId());
                }
            }
            if (eventParamList != null && eventParamList.size() > 0) {
                for (EventParam eventParam : eventParamList) {
                    eventParam.setEventId(event.getId());
                    eventParam.setEventNumber(event.getEventNumber());
                    es.insertParam(eventParam);
                }
            }
            return AlvesJSONResult.ok();
        } else {
            return AlvesJSONResult.errorMsg("fail update...");
        }
    }
    @ResponseBody
    @GetMapping(name = "查询事件详情", value = "event/{eventId}")
    public AlvesJSONResult selectById(@PathVariable Long eventId) {
        EventResult event = es.selectById(eventId);
        List<EventParamResult> list = es.selectParamByEventId(eventId);
        JSONObject json = new JSONObject();
        json.put("event", event);
        json.put("list", list);
        return AlvesJSONResult.ok(json);
    }
    @ResponseBody
    @PostMapping(name = "查询事件列表", value = "list")
    public AlvesJSONResult list(@RequestBody EventExtend eventExtend) {
        Map<String, Object> map = new HashMap<>();
        List<EventResult> list = es.queryForPage(eventExtend);
        Long count = es.queryForCounts(eventExtend);
        map.put("list", list);
        map.put("count", count);
        return AlvesJSONResult.ok(map);
    }
    @MyLog(value = 2)
    @ResponseBody
    @PostMapping(name = "审核事件", value = "verify_event")
    public AlvesJSONResult verifyEvent(@RequestBody VerifyEventReq eventReq) {
        EventResult coverEvent = es.selectById(eventReq.getCoverEId());
        if (eventReq.getMainEId() != null) {
            EventResult mainEvent = es.selectById(eventReq.getMainEId());
            mainEvent.setVerifyMethod(eventReq.getVerifyMethod());
            mainEvent.setVerifyStatus(eventReq.getVerifyStatus());
            mainEvent.setEventType(eventReq.getEventType());
            coverEvent.setMergeReason(eventReq.getMergeReason());
            if (eventReq.getEventType() == 1) {
                mainEvent.setProcess(EventProcess.EVENT_FINISH.getNo());
                mainEvent.setStatus(EventStatus.FINISH.getNo());
            } else {
                mainEvent.setProcess(EventProcess.VERIFY_REPORT.getNo());
            }
            es.update(mainEvent);
            //被合并
            coverEvent.setVerifyStatus(EventVerifyStatus.IS_MERGE.getNo());
            es.update(coverEvent);
            //被合并的事件记录
            SysLog sysLog = new SysLog();
            sysLog.setEventId(mainEvent.getId());
            sysLog.setOperation(coverEvent.getEventDesc());
            sysLog.setUserName(mainEvent.getReporter());
            sysLog.setCreateTime(coverEvent.getReceiveTime());
            sls.insert(sysLog);
        } else {
            coverEvent.setVerifyMethod(eventReq.getVerifyMethod());
            coverEvent.setVerifyStatus(eventReq.getVerifyStatus());
            coverEvent.setEventType(eventReq.getEventType());
            coverEvent.setMergeReason(eventReq.getMergeReason());
            if (eventReq.getEventType() == 1) {
                coverEvent.setProcess(EventProcess.EVENT_FINISH.getNo());
                coverEvent.setStatus(EventStatus.FINISH.getNo());
            }
            es.update(coverEvent);
        }
        return AlvesJSONResult.ok();
    }
    @MyLog(value = 12)
    @ResponseBody
    @PostMapping(name = "事件合并", value = "merge_event")
    public AlvesJSONResult mergeEvent(@RequestBody VerifyEventReq eventReq) {
        EventResult coverEvent = es.selectById(eventReq.getCoverEId());
        EventResult mainEvent = es.selectById(eventReq.getMainEId());
        coverEvent.setMergeReason(eventReq.getMergeReason());
        //被合并
        coverEvent.setVerifyStatus(EventVerifyStatus.IS_MERGE.getNo());
        es.update(coverEvent);
        //被合并的事件记录
        SysLog sysLog = new SysLog();
        sysLog.setEventId(mainEvent.getId());
        sysLog.setOperation(coverEvent.getEventDesc());
        sysLog.setUserName(mainEvent.getReporter());
        sysLog.setCreateTime(coverEvent.getReceiveTime());
        sls.insert(sysLog);
        return AlvesJSONResult.ok();
    }
    @ResponseBody
    @GetMapping(name = "查询操作记录", value = "sys_log_list/{eventId}")
    public AlvesJSONResult sysLogList(@PathVariable Long eventId) {
        List<JSONObject> list = sls.selectByEventId(eventId);
        return AlvesJSONResult.ok(list);
    }
    @ResponseBody
    @PostMapping(name = "情况更新", value = "sys_log_add")
    public AlvesJSONResult sysLogInsert(@RequestBody SysLog sysLog, HttpServletRequest request) {
        String token = TokenUtil.getRequestToken(request);
        Contacts contacts = cs.findByToken(token);
        sysLog.setUserName(contacts.getContactName());
        sysLog.setSysLogType(SysLogType.COMMON.getNo());
        sysLog.setCreateTime(DateUtil.getNowStr("yyyy-MM-dd HH:mm:ss"));
        int result = sls.insert(sysLog);
        if (result > 0) {
            return AlvesJSONResult.ok("success insert ...");
        } else {
            return AlvesJSONResult.errorMsg("fail insert ....");
        }
    }
    @ResponseBody
    @GetMapping(name = "通讯录", value = "list_group")
    public AlvesJSONResult groupList() {
        List<Group> groups = cs.selectGroup(new Group());
        for (Group group : groups) {
            List<ContactsResult> contactsResultList = cs.selectByGid(group.getId());
            if (contactsResultList != null && contactsResultList.size() > 0) {
                group.setContactsList(contactsResultList);
            }
        }
        List<Group> groupList = TreeUtil.treeRecursionDataList(groups, 0);
        List<JSONObject> list = new ArrayList<>();
        for (Group group : groupList) {
            JSONObject json = JSONObject.fromObject(group);
            json.remove("groupList");
            list.add(json);
        }
        return AlvesJSONResult.ok(list);
    }
}
