package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.enums.*;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.mapper.*;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.SysLogService;
import com.kira.emercmdplat.utils.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/5/4 23:03
 * @Description:
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper slm;
    @Autowired
    private ContactMapper cm;
    @Autowired
    private PlanParamMapper ppm;
    @Autowired
    private PlanTypeMapper ptm;

    @Override
    public int insert(SysLog sysLog) {
        return slm.insert(sysLog);
    }

    @Override
    public boolean delete(Long id) {
        return slm.delete(id);
    }

    @Override
    public boolean update(SysLog sysLog) {
        return slm.update(sysLog);
    }

    @Override
    public SysLog selectById(Long id) {
        return slm.selectById(id);
    }

    @Override
    public List<SysLog> queryForAll(SysLog sysLog) {
        return slm.queryForAll(sysLog);
    }

    @Override
    public Long queryForCounts(SysLog sysLog) {
        return slm.queryForCounts(sysLog);
    }

    @Override
    public List<SysLog> queryForPage(SysLog sysLog) {
        sysLog.setPage((sysLog.getPage() - 1) * sysLog.getPageSize());
        return slm.queryForPage(sysLog);
    }

    @Override
    public List<JSONObject> selectByEventId(Long eventId) {
        List<SysLog> list = slm.selectByEventId(eventId);
        List<JSONObject> resultList = new ArrayList<>();
        for (SysLog sysLog : list) {
            JSONObject jsonObject = JSONObject.fromObject(sysLog);
            SysLogType sysLogType = SysLogType.getByValue(sysLog.getSysLogType());
            JSONObject rJson = new JSONObject();
            String params = sysLog.getParams();
            switch (sysLogType) {
                case EVENT_INSERT:
                    JSONObject eventJson = JSONObject.fromObject(params);
                    Map classMap = new HashMap();
                    classMap.put("eventParamList",EventParam.class);
                    EventDomain eventDomain = (EventDomain)JSONObject.toBean(eventJson, EventDomain.class, classMap);
                    rJson.put("eventTitle", eventDomain.getEvent().getEventTitle());
                    List<JSONObject> paramList = new ArrayList<>();
                    List<EventParam> eventParamList = eventDomain.getEventParamList();
                    if (eventParamList != null && eventParamList.size() > 0) {
                        for (EventParam eventParam : eventParamList) {
                            if (eventParam != null) {
                                JSONObject paramJson = new JSONObject();
                                paramJson.put("value", eventParam.getPpValue());
                                PlanParam param = ppm.selectById(eventParam.getPpId().intValue());
                                paramJson.put("name", param.getName());
                                paramJson.put("unit", param.getUnit());
                                paramList.add(paramJson);
                            }
                        }
                        rJson.put("paramList", paramList);
                    }
                    break;
                case EVENT_VERIFY:
                    JSONObject eventVerifyJson = JSONObject.fromObject(params);
                    VerifyEventReq verifyEventReq = (VerifyEventReq)JSONObject.toBean(eventVerifyJson, VerifyEventReq.class);
                    rJson.put("reason", verifyEventReq.getMergeReason());
                    rJson.put("verifyMethod", VerifyMethod.getByValue(verifyEventReq.getVerifyMethod()).getName());
                    rJson.put("eventType", EventType.getByValue(verifyEventReq.getEventType()).getName());
                    rJson.put("verifyStatus", EventVerifyStatus.getByValue(verifyEventReq.getVerifyStatus()).getName());
                    break;
                case VERIFY_REPORT_INSERT:
                    JSONObject verifyReportJson = JSONObject.fromObject(params);
                    VerifyReport verifyReport = (VerifyReport)JSONObject.toBean(verifyReportJson, VerifyReport.class);
                    rJson.put("attachAddr", verifyReport.getQuickReportAddr());
                    break;
                case INSERT_LEADER_INSTRUCT_INSERT:
                    JSONObject leaderInstructJson = JSONObject.fromObject(params);
                    LeaderInstructExtend leaderInstructExtend = (LeaderInstructExtend)JSONObject.toBean(leaderInstructJson, LeaderInstructExtend.class);
                    rJson.put("instructContent", leaderInstructExtend.getInstructContent());
                    break;
                case START_RESERVE_PLAN:
                    JSONObject reservePlanJson = JSONObject.fromObject(params);
                    ReservePlanResult reservePlan = (ReservePlanResult)JSONObject.toBean(reservePlanJson, ReservePlanResult.class);
                    rJson.put("prLevel", reservePlan.getPrLevel());
                    break;
                case DISPATCH_CONTROL:
                    JSONObject taskJson = JSONObject.fromObject(params);
                    Map taskClassMap = new HashMap();
                    taskClassMap.put("contactList", JSONObject.class);
                    taskClassMap.put("contactIdList", Long.class);
                    TaskExtend taskExtend = (TaskExtend)JSONObject.toBean(taskJson, TaskExtend.class, taskClassMap);
                    rJson.put("taskTitle", taskExtend.getTaskTitle());
                    rJson.put("taskContent", taskExtend.getTaskContent());
                    List<Long> contactIdList = taskExtend.getContactIdList();
                    List<JSONObject> contactList = taskExtend.getContactList();
                    List<String> contactNameList = new ArrayList<>();
                    if (contactIdList != null && contactIdList.size() > 0) {
                        for (Long contactId : contactIdList) {
                            ContactsResult contactsResult = cm.selectById(contactId);
                            contactNameList.add(contactsResult.getContactName());
                        }
                    }
                    if (contactList != null && contactList.size() > 0) {
                        for (JSONObject json : contactList) {
                            contactNameList.add(StringUtil.toStr(json.get("contactName")));
                        }
                    }
                    rJson.put("contactNameList", contactNameList);
                    break;
                case EVENT_MERGE:
                    JSONObject eventMergeJson = JSONObject.fromObject(params);
                    VerifyEventReq eventMerge = (VerifyEventReq)JSONObject.toBean(eventMergeJson.getJSONObject("verifyEventReq"), VerifyEventReq.class);
                    rJson.put("reason", eventMerge.getMergeReason());
                    rJson.put("eventDesc", eventMergeJson.get("eventDesc"));
                    break;
                case EVENT_UPDATE:
                    JSONObject eventUpdateJson = JSONObject.fromObject(params);
                    Map eventClassMap = new HashMap();
                    eventClassMap.put("eventParamList",EventParam.class);
                    EventDomain eventDomain1 = (EventDomain)JSONObject.toBean(eventUpdateJson, EventDomain.class, eventClassMap);
                    rJson.put("eventTitle", eventDomain1.getEvent().getEventTitle());
                    rJson.put("eventLevel", EventLevel.getByValue(eventDomain1.getEvent().getEventLevel()).getName());
                    rJson.put("incidentLocation", eventDomain1.getEvent().getIncidentLocation());
                    PlanType planType = ptm.selectById(eventDomain1.getEvent().getPtId().intValue());
                    rJson.put("ptName", planType.getName());
                    List<JSONObject> paramsList = new ArrayList<>();
                    List<EventParam> eventParams = eventDomain1.getEventParamList();
                    if (eventParams != null && eventParams.size() > 0) {
                        for (EventParam eventParam : eventParams) {
                            if (eventParam != null) {
                                JSONObject paramJson = new JSONObject();
                                paramJson.put("value", eventParam.getPpValue());
                                PlanParam param = ppm.selectById(eventParam.getPpId().intValue());
                                paramJson.put("name", param.getName());
                                paramJson.put("unit", param.getUnit());
                                paramsList.add(paramJson);
                            }
                        }
                        rJson.put("paramList", paramsList);
                    }
                    break;
                default:
                    break;
            }
            jsonObject.put("detail", rJson);
            resultList.add(jsonObject);
        }
        return resultList;
    }
}
