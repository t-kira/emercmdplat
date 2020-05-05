package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.annotation.MyLog;
import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.enums.EventProcess;
import com.kira.emercmdplat.enums.MessageStatus;
import com.kira.emercmdplat.enums.ReservePlanStatus;
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

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    private QuickReportService qrs;
    @Autowired
    private MessageService mas;

//    @MyLog("事件接报")
    @ResponseBody
    @PostMapping(value = "add")
    public AlvesJSONResult insert(@RequestBody EventDomain eventDomain) {
        Event event = eventDomain.getEvent();
        List<EventParam> eventParamList = eventDomain.getEventParamList();
        event.setEventNumber(UUID.randomUUID().toString());
        event.setProcess(EventProcess.EVENT_RECEIVE.getNo());
        int result = es.insert(event);
        if (result > 0) {
            if (eventParamList.size() > 0) {
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
    @MyLog("终结事件")
    @ResponseBody
    @PostMapping(value = "end")
    public AlvesJSONResult end(@RequestBody Event event) {
//        event.setProcess(EventProcess.EVENT_FINISH.getNo());
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
//    @MyLog("获取事件类型列表")
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
//    @MyLog("查询事件参数")
    @ResponseBody
    @GetMapping(value = "event_param/{id}")
    public AlvesJSONResult listParam(@PathVariable int id) {
        List<PlanParam> planParamList = pts.listParamsByPtId(id);
        return AlvesJSONResult.ok(planParamList);
    }
    /**
     * 获取值班人员集合
     * @return
     */
//    @MyLog("查询值班人员集合")
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
//    @MyLog("查询值班人员信息")
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
//    @MyLog("查询机构列表")
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
    @MyLog("添加核实报告")
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
            JSONObject json = JSONObject.fromObject(quickReport);

            // 文件的实际路径
            String destPath = PropertiesUtils.getInstance().getProperty("ftlPath").toString() + UUID.randomUUID().toString() + ".pdf";
            String attachmentGainPath = PropertiesUtils.getInstance().getProperty("attachmentGainPath").toString();
            String toPath = FilenameUtils.separatorsToSystem(attachmentGainPath + destPath);
            String content = PDFTemplateUtil.freeMarkerRender(json, "/ftlFile/pdf.ftl");
            try {
                PDFTemplateUtil.createPdf(content, toPath);
                verifyReport.setQuickReportAddr(destPath);
                vrs.update(verifyReport);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            qrs.insert(quickReport);
            //生成PDF
            Event event = es.selectById(verifyReport.getEid());
            event.setId(verifyReport.getEid());
            event.setProcess(EventProcess.LEADER_INSTRUCT.getNo());
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
            rps.insert(reservePlan);
            return AlvesJSONResult.ok(EventProcess.VERIFY_REPORT.getNo());
        } else {
            return AlvesJSONResult.errorMsg("fail insert verifyReport...");
        }
    }
//    @MyLog("查询核实报告")
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
//    @MyLog("附件上传")
    @ResponseBody
    @PostMapping(value = "fileUpload")
    public AlvesJSONResult upload(@RequestParam(value = "file") MultipartFile file) {
        try {
            String path = PropertiesUtils.getInstance().getProperty("attachmentPath").toString();
            String extension = PropertiesUtils.getInstance().getProperty("imageExtension").toString();
            FileResult fileResult = FileuploadUtil.saveFile(file, path, extension);
            return AlvesJSONResult.ok(fileResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return AlvesJSONResult.errorMsg("fail upload...");
    }
//    @MyLog("查询预案列表")
    @ResponseBody
    @GetMapping("plan_version_list/{id}")
    public AlvesJSONResult listPlanVersion(@PathVariable int id) {
        PlanVersion planVersion = new PlanVersion();
        planVersion.setType(id);
        List<PlanVersion> planVersionList = pvs.listAllVersions(planVersion);
        return AlvesJSONResult.ok(planVersionList);
    }
//    @MyLog("查询预案等级")
    @ResponseBody
    @GetMapping("plan_response_list/{id}")
    public AlvesJSONResult listPlanResponse(@PathVariable int id) {
        List<PlanResponse> responseList = pvs.listResponses(id,1);
        return AlvesJSONResult.ok(responseList);
    }
    /**
     * 添加领导批示
     * @param leaderInstruct
     * @return
     */
    @MyLog("添加领导批示")
    @ResponseBody
    @PostMapping(value = "add_leader_instruct")
    public AlvesJSONResult insertLeaderInstruct(@RequestBody LeaderInstruct leaderInstruct) {
        leaderInstruct.setInstructTime(DateUtil.getNowStr("yyyy-MM-dd HH:mm:ss"));
        int result = lis.insert(leaderInstruct);
        if (result > 0) {
            Event event = new Event();
            event.setId(leaderInstruct.getEid());
            event.setProcess(EventProcess.RESERVE_PLAN.getNo());
            es.update(event);
            return AlvesJSONResult.ok(EventProcess.LEADER_INSTRUCT.getNo());
        } else {
            return AlvesJSONResult.errorMsg("fail insert leader instruct...");
        }
    }
    /**
     * 领导批示
     * @param eId
     * @return
     */
//    @MyLog("查询领导批示")
    @ResponseBody
    @GetMapping(value = "leader_instruct_list/{eId}")
    public AlvesJSONResult listLeaderInstruct(@PathVariable Long eId) {
        LeaderInstruct leaderInstruct = new LeaderInstruct();
        leaderInstruct.setEid(eId);
        List<LeaderInstruct> list = lis.queryForAll(leaderInstruct);
        return AlvesJSONResult.ok(list);
    }
//    @MyLog("查询领导批示")
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
    @MyLog("添加事件发展")
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
    @MyLog("修改事件发展")
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
//    @MyLog("查询预案")
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
//    @MyLog("查询预案")
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
    @MyLog("启动预案")
    @ResponseBody
    @PostMapping(value = "reserve_start")
    public AlvesJSONResult startReservePlan(@RequestBody ReservePlanResult reservePlanResult) {
        VerifyReport verifyReport = new VerifyReport();
        verifyReport.setId(reservePlanResult.getVrId());
        verifyReport.setPrId(reservePlanResult.getPrId());
        boolean result = vrs.update(verifyReport);
        if (result) {
            if (reservePlanResult.getStatus() < 0) {
                reservePlanResult.setStatus(ReservePlanStatus.STOP.getNo());
                Event event = new Event();
                event.setId(reservePlanResult.getEid());
                event.setProcess(EventProcess.EVENT_FINISH.getNo());
                es.update(event);
            } else {
                reservePlanResult.setStatus(ReservePlanStatus.START.getNo());
            }
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
    @MyLog("删除事件")
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
    @MyLog("更新事件")
    public AlvesJSONResult update(Event event) {
        boolean result = es.update(event);
        if (result) {
            return AlvesJSONResult.ok();
        } else {
            return AlvesJSONResult.errorMsg("fail update...");
        }
    }
//    @MyLog("查询事件")
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
//    @MyLog("查询事件列表")
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
//    @ResponseBody
//    @GetMapping("test")
//    public AlvesJSONResult test() {
//        System.out.println(new File(PathUtil.getCurrentPath()));
//        JSONObject json = new JSONObject();
//        json.put("userName", "测试内容");
//        json.put("content", "测试内容");
//        json.put("issueTime", "2020-05-02 17:34:33");
//        String pdfPath = "/Users/kira/Desktop/pdf/" + UUID.randomUUID().toString() + ".pdf";
//        try {
//            String content = PDFTemplateUtil.freeMarkerRender(json, "/templates/pdf.ftl");
//            PDFTemplateUtil.createPdf(content, pdfPath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return AlvesJSONResult.ok();
//    }
    @MyLog("审核事件")
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
                mainEvent.setProcess(EventProcess.RESERVE_PLAN.getNo());
            } else {
                mainEvent.setProcess(EventProcess.LEADER_INSTRUCT.getNo());
            }
            es.update(mainEvent);
            //被合并
            coverEvent.setVerifyStatus(3);
            es.update(coverEvent);
            EventDevelopment development = new EventDevelopment();
            development.setEid(mainEvent.getId());
            development.setReportContent(coverEvent.getEventDesc());
            development.setReporter(mainEvent.getReporter());
            development.setReportTime(coverEvent.getReceiveTime());
            es.insertDevelopment(development);
        } else {
            coverEvent.setVerifyMethod(eventReq.getVerifyMethod());
            coverEvent.setVerifyStatus(eventReq.getVerifyStatus());
            coverEvent.setEventType(eventReq.getEventType());
            coverEvent.setMergeReason(eventReq.getMergeReason());
            coverEvent.setProcess(EventProcess.VERIFY_REPORT.getNo());
            if (eventReq.getEventType() == 1) {
                coverEvent.setProcess(EventProcess.RESERVE_PLAN.getNo());
            }
            es.update(coverEvent);
        }
        return AlvesJSONResult.ok();
    }
    @MyLog("事件合并")
    @ResponseBody
    @PostMapping("merge_event")
    public AlvesJSONResult mergeEvent(@RequestBody VerifyEventReq eventReq) {
        EventResult coverEvent = es.selectById(eventReq.getCoverEId());
        EventResult mainEvent = es.selectById(eventReq.getMainEId());
        coverEvent.setMergeReason(eventReq.getMergeReason());
            //被合并
        coverEvent.setVerifyStatus(3);
        es.update(coverEvent);
        EventDevelopment development = new EventDevelopment();
        development.setEid(mainEvent.getId());
        development.setReportContent(coverEvent.getEventDesc());
        development.setReporter(mainEvent.getReporter());
        development.setReportTime(coverEvent.getReceiveTime());
        es.insertDevelopment(development);
        return AlvesJSONResult.ok();
    }
}
