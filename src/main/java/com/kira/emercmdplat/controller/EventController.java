package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.annotation.MyLog;
import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.enums.*;
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
@RequestMapping("/event")
public class EventController extends BaseController {
    @Autowired
    private EventService es;
    @Autowired
    private PlanTypeService pts;
    @Autowired
    private DutyService ds;
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

    /**
     * 事件接报
     * @param eventDomain
     * @return
     */
    @MyLog(value = 1)
    @ResponseBody
    @PostMapping(value = "add")
    public AlvesJSONResult insert(@RequestBody EventDomain eventDomain) {
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
        event.setVerifyStatus(0);
        event.setProcess(EventProcess.EVENT_RECEIVE.getNo());
        event.setReceiveTime(DateUtil.getNowStr("yyy-MM-dd HH:mm:ss"));
        int result = es.insert(event);
        if (result > 0) {
            if (eventParamList != null && eventParamList.size() > 0) {
                for (EventParam eventParam : eventParamList) {
                    eventParam.setEId(event.getId());
                    eventParam.setEventNumber(event.getEventNumber());
                    es.insertParam(eventParam);
                }
            }
            return AlvesJSONResult.ok(event);
        } else {
            return AlvesJSONResult.errorMsg("fail insert...");
        }
    }
    /**
     * 终结事件
     * @param event
     * @return
     */
    @MyLog(value = 7)
    @ResponseBody
    @PostMapping(value = "end")
    public AlvesJSONResult end(@RequestBody Event event) {
        event.setProcess(EventProcess.EVENT_FINISH.getNo());
        boolean result = es.update(event);
        if (result) {
            return AlvesJSONResult.ok("success close");
        } else {
            return AlvesJSONResult.errorMsg("error close event...");
        }
    }
    /**
     * 获取事件类型列表
     * @return 事件类型集合
     */
    @ResponseBody
    @GetMapping(value = "type_list")
    public AlvesJSONResult listPlanType() {
        List<Node> list = pts.listTypeTree(null);
        return AlvesJSONResult.ok(list);
    }
    /**
     * 根据事件类型返回事件参数集合
     * @param id 事件类型ID
     * @return
     */
    @ResponseBody
    @GetMapping(value = "event_param/{id}")
    public AlvesJSONResult listParam(@PathVariable int id) {
        List<PlanParam> planParamList = pts.listParamsByPtId(id,true);
        return AlvesJSONResult.ok(planParamList);
    }
    /**
     * 获取值班人员集合
     * @return
     */
    @ResponseBody
    @GetMapping(value = "duty_list")
    public AlvesJSONResult listDuty(){
        List<DutyExtent> dutyList = ds.queryForAll(new DutyExtent());
        return AlvesJSONResult.ok(dutyList);
    }
    /**
     * 获取值班人员信息
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping(value = "duty/{id}")
    public AlvesJSONResult selectDuty(@PathVariable int id){
        Duty duty = ds.selectById(id);
        return AlvesJSONResult.ok(duty);
    }
    /**
     * 获取机构集合
     * @return
     */
    @ResponseBody
    @GetMapping(value = "mechanism_list")
    public AlvesJSONResult listMechanism() {
        List<Mechanism> mechanismList = ms.queryForAll(new Mechanism());
        return AlvesJSONResult.ok(mechanismList);
    }
    /**
     * 添加核实报告内容
     * @param verifyReport
     * @return
     */
    @MyLog(value = 3)
    @ResponseBody
    @PostMapping(value = "add_verify")
    public AlvesJSONResult insertVerifyReport(@RequestBody VerifyReport verifyReport) {
//        VerifyReport verifyReport = verifyQuickReport.getVerify();
        int result = vrs.insert(verifyReport);
        if (result > 0) {
            EventResult eventResult = es.selectById(verifyReport.getEid());
            QuickReport quickReport = new QuickReport();
            quickReport.setTitle(eventResult.getEventTitle());
            quickReport.setContent(eventResult.getEventDesc());
            quickReport.setEid(verifyReport.getEid());
            quickReport.setOrigin(1);
            quickReport.setEditId(eventResult.getDid());
            quickReport.setIssueTime(DateUtil.getNowStr("yyyy-MM-dd HH:mm:ss"));
            JSONObject json = new JSONObject();
            json.put("richText", verifyReport.getCreateTime());

            // 文件的实际路径
            String path = PropertiesUtils.getInstance().getProperty("attachmentPath").toString();
            String attachmentGainPath = PropertiesUtils.getInstance().getProperty("attachmentGainPath").toString();
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
            quickReport.setSubmitId(eventResult.getDid().intValue());
            qrs.insert(quickReport);
            //生成PDF
            Event event = es.selectById(verifyReport.getEid());
            event.setId(verifyReport.getEid());
            event.setProcess(EventProcess.VERIFY_REPORT.getNo());
            //新增一条消息
            Message message = new Message();
            message.setEid(event.getId());
            message.setDid(verifyReport.getDid());
            message.setVId(verifyReport.getId());
            message.setStatus(MessageStatus.MESSAGE_UNREAD.getNo());
            message.setType(0);
            mas.insert(message);

            es.update(event);
            ReservePlan reservePlan = new ReservePlan();
            reservePlan.setEid(verifyReport.getEid());
            reservePlan.setStatus(ReservePlanStatus.UNEDIT.getNo());
            reservePlan.setStartTime(DateUtil.getNowStr("yyyy-MM-dd HH:mm:ss"));
            rps.insert(reservePlan);
            return AlvesJSONResult.ok(EventProcess.VERIFY_REPORT.getNo());
        } else {
            return AlvesJSONResult.errorMsg("fail insert verifyReport...");
        }
    }
    /**
     * 根据事件ID查询核实报告
     * @param eId
     * @return
     */
    @ResponseBody
    @GetMapping(value = "verify/{eId}")
    public AlvesJSONResult selectVerifyReportByEid(@PathVariable long eId) {
        VerifyReport verifyReport = new VerifyReport();
        verifyReport.setEid(eId);
        List<VerifyReport> verifyReportList = vrs.queryForAll(verifyReport);
        if (verifyReportList != null && verifyReportList.size() > 0) {
            return AlvesJSONResult.ok(verifyReportList.get(0));
        } else {
            return AlvesJSONResult.errorMsg("verify report is null ...");
        }
    }
    /**
     * 附件上传
     * @param file
     * @return
     */
    @ResponseBody
    @PostMapping(value = "fileUpload")
    public AlvesJSONResult upload(@RequestParam(value = "file") MultipartFile file) {
        try {
            //#服务器静态文件地址
            String path = PropertiesUtils.getInstance().getProperty("attachmentPath").toString();
            String extension = PropertiesUtils.getInstance().getProperty("imageExtension").toString();
            FileResult fileResult = FileuploadUtil.saveFile(file, path, extension);
            return AlvesJSONResult.ok(fileResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AlvesJSONResult.errorMsg("fail upload...");
    }
    /**
     *初判预案列表
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("plan_version_list/{id}")
    public AlvesJSONResult listPlanVersion(@PathVariable int id) {
        PlanVersion planVersion = new PlanVersion();
        planVersion.setType(id);
        planVersion.setStatus(4);
        List<PlanVersion> planVersionList = pvs.listAllVersions(planVersion);
        return AlvesJSONResult.ok(planVersionList);
    }
    /**
     * 初判等级列表
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("plan_response_list/{id}")
    public AlvesJSONResult listPlanResponse(@PathVariable int id) {
        List<PlanResponse> responseList = pvs.listResponses(id,1);
        return AlvesJSONResult.ok(responseList);
    }
    /**
     * 领导批示
     * @param eId
     * @return
     */
    @ResponseBody
    @GetMapping(value = "leader_instruct_list/{eId}")
    public AlvesJSONResult listLeaderInstruct(@PathVariable Long eId) {
        LeaderInstruct leaderInstruct = new LeaderInstruct();
        leaderInstruct.setEid(eId);
        List<LeaderInstruct> list = lis.queryForAll(leaderInstruct);
        return AlvesJSONResult.ok(list);
    }
    /**
     * 根据事件ID查询领导批示记录
     * @param eId
     * @return
     */
    @ResponseBody
    @GetMapping(value = "leader_instruct/{eId}")
    public AlvesJSONResult selectLeaderInstructByEid(@PathVariable long eId) {
        LeaderInstruct leaderInstruct = new LeaderInstruct();
        leaderInstruct.setEid(eId);
        List<LeaderInstruct> list = lis.queryForAll(leaderInstruct);
        if (list != null && list.size() > 0) {
            return AlvesJSONResult.ok(list.get(0));
        } else {
            return AlvesJSONResult.errorMsg("verify report is null ...");
        }
    }
    /**
     * 添加事件发展信息
     * @param development
     * @return
     */
    @MyLog(value = 11)
    @ResponseBody
    @PostMapping(value = "add_development")
    public AlvesJSONResult insertDevelopment(@RequestBody EventDevelopment development) {
        int result = es.insertDevelopment(development);
        if (result > 0) {
            return AlvesJSONResult.ok();
        } else {
            return AlvesJSONResult.errorMsg("fail insert development...");
        }
    }
    /**
     * 修改事件发展
     * @param development
     * @return
     */
    @MyLog(value = 10)
    @ResponseBody
    @PostMapping(value = "updateDevelopment")
    public AlvesJSONResult updateDevelopment(@RequestBody EventDevelopment development) {
        int result = es.updateDevelopment(development);
        if (result > 0) {
            return AlvesJSONResult.ok();
        } else {
            return AlvesJSONResult.errorMsg("fail update development...");
        }
    }
    /**
     * 查询预案
     * @param eId
     * @return
     */
    @ResponseBody
    @GetMapping(value = "reserve_plan_list/{eId}")
    public AlvesJSONResult selectReservePlanByEId(@PathVariable Long eId) {
        List<ReservePlanResult> list = rps.selectByEId(eId);
        return AlvesJSONResult.ok(list);
    }
    /**
     * 查询预案
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping(value = "reserve_plan/{id}")
    public AlvesJSONResult selectReservePlan(@PathVariable Long id) {
        ReservePlanResult reservePlanResult = rps.selectById(id);
        return AlvesJSONResult.ok(reservePlanResult);
    }
    /**
     * 启动预案
     * @param reservePlanResult
     * @return
     */
    @MyLog(value = 5)
    @ResponseBody
    @PostMapping(value = "reserve_start")
    public AlvesJSONResult startReservePlan(@RequestBody ReservePlanResult reservePlanResult) {
        VerifyReport verifyReport = new VerifyReport();
        verifyReport.setId(reservePlanResult.getVrId());
        verifyReport.setPrId(reservePlanResult.getPrId());
        boolean result = vrs.update(verifyReport);
        if (result) {
            reservePlanResult.setStartTime(DateUtil.getNowStr("yyyy-MM-dd HH:mm:ss"));
                Event event = new Event();
                event.setId(reservePlanResult.getEid());
                event.setProcess(EventProcess.RESERVE_PLAN.getNo());
                es.update(event);
            rps.update(reservePlanResult);
            return AlvesJSONResult.ok("ok start...");
        } else {
            return AlvesJSONResult.errorMsg("fail start reserve...");
        }
    }
    /**
     * 删除事件
     * @param event
     * @return
     */
    @MyLog(value = 8)
    @ResponseBody
    @PostMapping(value = "remove")
    public AlvesJSONResult delete(Event event) {
        boolean result = es.delete(event);
        if (result) {
            return AlvesJSONResult.ok();
        } else {
            return AlvesJSONResult.errorMsg("fail delete...");
        }
    }
    @ResponseBody
    @PostMapping(value = "update")
    @MyLog(value = 9)
    public AlvesJSONResult update(@RequestBody EventDomain eventDomain) {
        Event event = eventDomain.getEvent();
        List<EventParam> eventParamList = eventDomain.getEventParamList();
        boolean result = es.update(event);
        if (result) {
            List<EventParamResult> paramList =  es.selectParamByEId(event.getId());
            if (paramList != null && paramList.size() > 0) {
                for (EventParamResult param : paramList) {
                    es.deleteParam(param.getId());
                }
            }
            if (eventParamList != null && eventParamList.size() > 0) {
                for (EventParam eventParam : eventParamList) {
                    eventParam.setEId(event.getId());
                    eventParam.setEventNumber(event.getEventNumber());
                    es.insertParam(eventParam);
                }
            }
            return AlvesJSONResult.ok();
        } else {
            return AlvesJSONResult.errorMsg("fail update...");
        }
    }
    /**
     * 查询事件详情
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping(value = "event/{id}")
    public AlvesJSONResult selectById(@PathVariable Long id) {
        EventResult event = es.selectById(id);
        List<EventParamResult> list = es.selectParamByEId(id);
        JSONObject json = new JSONObject();
        json.put("event", event);
        json.put("list", list);
        return AlvesJSONResult.ok(json);
    }
    /**
     * 查询事件列表
     * @param eventExtend
     * @return
     */
    @ResponseBody
    @PostMapping("list")
    public AlvesJSONResult list(@RequestBody EventExtend eventExtend) {
        Map<String, Object> map = new HashMap<>();
        List<EventResult> list = es.queryForPage(eventExtend);
        Long count = es.queryForCounts(eventExtend);
        map.put("list", list);
        map.put("count", count);
        return AlvesJSONResult.ok(map);
    }
    /**
     * 审核事件
     * @param eventReq
     * @return
     */
    @MyLog(value = 2)
    @ResponseBody
    @PostMapping("verify_event")
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

            SysLog sysLog = new SysLog();
            sysLog.setEid(mainEvent.getId());
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
    /**
     * 事件合并
     * @param eventReq
     * @return
     */
    @MyLog(value = 12)
    @ResponseBody
    @PostMapping("merge_event")
    public AlvesJSONResult mergeEvent(@RequestBody VerifyEventReq eventReq) {
        EventResult coverEvent = es.selectById(eventReq.getCoverEId());
        EventResult mainEvent = es.selectById(eventReq.getMainEId());
        coverEvent.setMergeReason(eventReq.getMergeReason());
        //被合并
        coverEvent.setVerifyStatus(EventVerifyStatus.IS_MERGE.getNo());
        es.update(coverEvent);
        SysLog sysLog = new SysLog();
        sysLog.setEid(mainEvent.getId());
        sysLog.setOperation(coverEvent.getEventDesc());
        sysLog.setUserName(mainEvent.getReporter());
        sysLog.setCreateTime(coverEvent.getReceiveTime());
        sls.insert(sysLog);
        return AlvesJSONResult.ok();
    }
    /**
     * 查询操作记录
     * @param eid
     * @return
     */
    @ResponseBody
    @GetMapping("sys_log_list/{eid}")
    public AlvesJSONResult sysLogList(@PathVariable Long eid) {
        List<JSONObject> list = sls.selectByEid(eid);

        return AlvesJSONResult.ok(list);
    }
    /**
     * 情况更新
     * @param sysLog
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("sys_log_add")
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
    /**
     * 通讯录
     * @return
     */
    @ResponseBody
    @GetMapping("list_group")
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
    @MyLog(value = 6)
    @ResponseBody
    @GetMapping("dispatch_control")
    public AlvesJSONResult dispatchControl(@PathVariable Long eventId) {

        return AlvesJSONResult.ok();
    }
}
