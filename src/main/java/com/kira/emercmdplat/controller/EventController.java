package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.annotation.MechanismPermission;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
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
    private SysLogService sls;
    @Autowired
    private ContactService cs;

    @MyLog(value = 1)
    @ResponseBody
    @PostMapping(name = "事件接报", value = "add")
    public AlvesJSONResult insert(@Validated @RequestBody EventDomain eventDomain, HttpServletRequest request) {
        int result = es.insert(eventDomain, request);
        if (result > 0) {
            return AlvesJSONResult.ok();
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(),"事件保存失败");
        }
    }
    @MyLog(value = 7)
    @ResponseBody
    @PostMapping(name = "终结事件", value = "end")
    public AlvesJSONResult end(@RequestBody Event event) {
        if (event != null && event.getId() > 0) {
            event.setProcess(EventProcess.EVENT_FINISH.getNo());
            boolean result = es.update(event);
            if (result) {
                return AlvesJSONResult.ok("success close");
            } else {
                throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "终结事件失败");
            }
        } else {
            throw new CustomException(ResultEnum.ERROR_PARAMETER.getNo(), "事件ID必传");
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
    public AlvesJSONResult listParam(@PathVariable Integer id) {
        List<PlanParam> planParamList = pts.listParamsByPtId(id,true);
        return AlvesJSONResult.ok(planParamList);
    }
    @ResponseBody
    @GetMapping(name = "获取值班人员集合", value = "duty_list")
    public AlvesJSONResult listDuty(){
        Contacts contacts = new Contacts();
        contacts.setPersonAttribute(1);
        List<ContactsResult> contactsList = cs.queryForAllOrPage(contacts);
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
        List<Mechanism> mechanismList = ms.queryForAllOrPage(null);
        return AlvesJSONResult.ok(mechanismList);
    }
    @MyLog(value = 3)
    @ResponseBody
    @PostMapping(name = "添加核实报告内容", value = "add_verify")
    public AlvesJSONResult insertVerifyReport(@Validated @RequestBody VerifyReport verifyReport) {
        verifyReport.setCreateTime(DateUtil.getNowStr());
        int result = vrs.insert(verifyReport);
        if (result > 0) {
            return AlvesJSONResult.ok(EventProcess.VERIFY_REPORT.getNo());
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo());
        }
    }
    @ResponseBody
    @GetMapping(name = "根据事件ID查询核实报告", value = "verify/{eventId}")
    public AlvesJSONResult selectVerifyReportByEventId(@PathVariable Long eventId) {
        VerifyReport verifyReport = new VerifyReport();
        verifyReport.setEventId(eventId);
        List<VerifyReport> verifyReportList = vrs.queryForAllOrPage(verifyReport);
        if (verifyReportList != null && verifyReportList.size() > 0) {
            return AlvesJSONResult.ok(verifyReportList.get(0));
        } else {
            throw new CustomException(ResultEnum.NON_DATA.getNo());
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
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "附件上传失败");
        }
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
    @ResponseBody
    @GetMapping(name = "查询事件领导批示集合", value = "leader_instruct_list/{eventId}")
    public AlvesJSONResult listLeaderInstruct(@PathVariable Long eventId) {
        LeaderInstructExtend leaderInstructExtend = new LeaderInstructExtend();
        leaderInstructExtend.setEventId(eventId);
        List<LeaderInstructResult> list = lis.queryForAll(leaderInstructExtend);
        return AlvesJSONResult.ok(list);
    }
    @ResponseBody
    @GetMapping(name = "根据事件ID查询领导批示记录", value = "leader_instruct/{eventId}")
    public AlvesJSONResult selectLeaderInstructByEventId(@PathVariable Long eventId) {
        LeaderInstructExtend leaderInstructExtend = new LeaderInstructExtend();
        leaderInstructExtend.setEventId(eventId);
        List<LeaderInstructResult> list = lis.queryForAll(leaderInstructExtend);
        if (list != null && list.size() > 0) {
            return AlvesJSONResult.ok(list.get(0));
        } else {
            throw new CustomException(ResultEnum.NON_DATA.getNo());
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
    public AlvesJSONResult startReservePlan(@Validated @RequestBody ReservePlanResult reservePlanResult) {
        EventResult eventResult = es.selectById(reservePlanResult.getEventId());
        if (eventResult == null) {
            throw new CustomException(ResultEnum.NON_DATA.getNo());
        }
        if (eventResult.getStatus() == EventStatus.FINISH.getNo() && eventResult.getProcess() == EventProcess.EVENT_FINISH.getNo()) {
            throw new CustomException(ResultEnum.EVENT_FINISH.getNo());
        }
        VerifyReport verifyReport = new VerifyReport();
        verifyReport.setId(reservePlanResult.getVrId());
        verifyReport.setPrId(reservePlanResult.getPrId());
        boolean result = vrs.update(verifyReport);
        if (result) {
            reservePlanResult.setStartTime(DateUtil.getNowStr());
            Event event = new Event();
            event.setId(reservePlanResult.getEventId());
            event.setProcess(EventProcess.RESERVE_PLAN.getNo());
            es.update(event);
            rps.update(reservePlanResult);
            return AlvesJSONResult.ok("预案启动成功");
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "预案启动失败");
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
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "事件删除失败");
        }
    }
    @MyLog(value = 9)
    @Transactional
    @ResponseBody
    @PostMapping(name = "事件更新", value = "update")
    public AlvesJSONResult update(@RequestBody EventDomain eventDomain, HttpServletRequest request) {
        boolean result = es.update(eventDomain, request);
        if (result) {
            return AlvesJSONResult.ok();
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "事件更新失败");
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
    public AlvesJSONResult list(@RequestBody(required = false) Event event) {
        List<EventResult> list = es.queryForAllOrPage(event);
        Long count = es.queryForCounts(event);
        System.out.println("list");
        return AlvesJSONResult.pageOk(list, count);
    }
    @MyLog(value = 2)
    @ResponseBody
    @PostMapping(name = "审核事件", value = "verify_event")
    public AlvesJSONResult verifyEvent(@Validated @RequestBody VerifyEventReq eventReq, HttpServletRequest request) {
        boolean result = es.verifyEvent(eventReq, request);
        if (result) {
            return AlvesJSONResult.ok("事件审核成功");
        } else {
            return AlvesJSONResult.errorMsg("事件审核失败");
        }
    }
    @ResponseBody
    @Transactional
    @PostMapping(name = "事件合并", value = "merge_event")
    public AlvesJSONResult mergeEvent(@RequestBody VerifyEventReq eventReq, HttpServletRequest request) {
        if (eventReq.getMainEId() > 0 && eventReq.getCoverEId() > 0) {
            boolean result = es.mergeEvent(eventReq, request);
            if (result)
                return AlvesJSONResult.ok("事件合并成功");
            else
                return AlvesJSONResult.errorMsg("事件合并失败");
        } else {
            throw new CustomException(ResultEnum.MISSING_PARAMETER.getNo(), "主事件和被合并事件ID不能为空");
        }
    }
    @ResponseBody
    @GetMapping(name = "查询操作记录", value = "sys_log_list/{eventId}")
    public AlvesJSONResult sysLogList(@PathVariable Long eventId) {
        List<JSONObject> list = sls.selectByEventId(eventId);
        return AlvesJSONResult.ok(list);
    }
    @ResponseBody
    @PostMapping(name = "情况更新", value = "sys_log_add")
    public AlvesJSONResult sysLogInsert(@Validated @RequestBody SysLog sysLog, HttpServletRequest request) {
        String token = TokenUtil.getRequestToken(request);
        Contacts contacts = cs.findByToken(token);
        sysLog.setUserName(contacts.getContactName());
        sysLog.setSysLogType(SysLogType.COMMON.getNo());
        sysLog.setCreateTime(DateUtil.getNowStr());
        int result = sls.insert(sysLog);
        if (result > 0) {
            return AlvesJSONResult.ok("success insert ...");
        } else {
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "情况更新失败");
        }
    }
    @ResponseBody
    @GetMapping(name = "通讯录", value = "list_group")
    public AlvesJSONResult groupList() {
        List<Group> groupList = cs.selectContactList();
        List<JSONObject> list = new ArrayList<>();
        for (Group group : groupList) {
            JSONObject json = JSONObject.fromObject(group);
            json.remove("groupList");
            list.add(json);
        }
        return AlvesJSONResult.ok(list);
    }
    @ResponseBody
    @GetMapping(name = "事件数据统计", value = "event_statistics")
    public AlvesJSONResult statistics() {
        Map<String, Object> result = es.statistics();
        return AlvesJSONResult.ok(result);
    }
}
