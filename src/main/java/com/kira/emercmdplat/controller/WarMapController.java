package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.annotation.MyLog;
import com.kira.emercmdplat.enums.SourceType;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.*;
import com.kira.emercmdplat.utils.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.*;

/**
 * @Author: kira
 * @Date: 2020/5/23 17:03
 * @Description:战时一张图
 */
@CrossOrigin
@RestController
@RequestMapping("/war")
public class WarMapController {

    @Autowired
    private ContactService cs;
    @Autowired
    private TaskService ts;
    @Autowired
    private EventService es;
    @Autowired
    private DataTypeService dts;
    @Autowired
    private SecondaryDerivationService sds;
    @Autowired
    private ReservePlanService rps;
    @Autowired
    private EmergencySupplyService ess;
    @Autowired
    private HazardSourceService hss;
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
        return AlvesJSONResult.ok(groupList);
    }
    /**
     * 指派事件任务
     * @param taskExtend
     * @return
     */
    @MyLog(value = 6)
    @ResponseBody
    @PostMapping("add_task")
    public AlvesJSONResult insertTask(@RequestBody TaskExtend taskExtend) {
        int result = ts.insert(taskExtend);
        if (result > 0) {
            return AlvesJSONResult.ok("success insert...");
        } else {
            return AlvesJSONResult.errorMsg("fail insert...");
        }
    }
    /**
     * 事件详情
     * @param id 事件ID
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
    @ResponseBody
    @PostMapping(name="列出事件发源地范围内的风险隐患",value="situation_analysis")
    public AlvesJSONResult situationAnalysis(@RequestBody EventSource eventSource) {
        EventResult eventResult = es.selectById(eventSource.getEventId());
        List<DataType> dataTypeList = new ArrayList<>();
        if (eventSource.getDataTypeId() != null && eventSource.getDataTypeId() > 0) {
            DataType dataType = new DataType();
            dataType.setId(eventSource.getDataTypeId());
            dataTypeList.add(dataType);
        } else {
            DataType dataType = new DataType();
            dataType.setTaskType(1);
            dataTypeList = dts.queryForAll(dataType);
        }
        List<JSONObject> listJson = new ArrayList<>();
        for (DataType d : dataTypeList) {
            JSONObject jsonObject = new JSONObject();
            DataType dataType2 = new DataType();
            dataType2.setId(d.getId());
            List<DataType> list = dts.queryResources(dataType2);
            Iterator<DataType> iterator = list.iterator();
            while (iterator.hasNext()) {
                DataType dataType1 = iterator.next();
                dataType1.setDataTypeId(d.getId());
                if (dataType1.getIcon() != null) {
                	dataType1.setCommonIcon(BaseObject.host + "/img/" + dataType1.getIcon() + "-common.png");
                	dataType1.setActiveIcon(BaseObject.host + "/img/" + dataType1.getIcon() + "-active.png");
            	}
                boolean flag = DistanceUtil.getDistance(eventResult.getLat(), eventResult.getLng(), dataType1.getLat(), dataType1.getLng(), eventSource.getInfluenceScope());
                if (!flag) {
                    iterator.remove();
                }
            }
            jsonObject.put("name", d.getName());
            jsonObject.put("id", d.getId());
            jsonObject.put("dataTypeList", list);
            listJson.add(jsonObject);
        }

        return AlvesJSONResult.ok(listJson);
    }
    @ResponseBody
    @GetMapping("list_task")
    public AlvesJSONResult taskList(Integer dataTypeId, Long eventId) {
//        TaskExtend taskExtend = new TaskExtend();
//        taskExtend.setEventId(eventId);
//        taskExtend.setOrder("id");
//        taskExtend.setOrderType("desc");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("dataTypeId", dataTypeId);
        paramMap.put("eventId", eventId);
        List<Task> taskList = ts.selectByTaskType(paramMap);
        return AlvesJSONResult.ok(taskList);
    }

    @ResponseBody
    @PostMapping("new_update_list")
    public AlvesJSONResult newUpdateList(@RequestBody TaskExtend taskExtend) {
        List<Task> taskList = ts.queryForAll(taskExtend);
        List<JSONObject> jsonObjectList = new ArrayList<>();
        for (Task task : taskList) {
            JSONObject json = JSONObject.fromObject(task);
            Feedback feedback = ts.selectLatestFeedbackByTaskId(task.getId());
            if (feedback != null) {
                JSONObject jsonObject = JSONObject.fromObject(feedback);
                if (!StringUtil.isEmpty(feedback.getAttachPath())) {
                    String[] attachArr = feedback.getAttachPath().split(",");
                    List<String> attachList = new ArrayList<>();
                    for (String attach : attachArr) {
                        attachList.add(attach);
                    }
                    jsonObject.put("attachList", attachList);
                }
                json.put("feedback", jsonObject);
            }
            jsonObjectList.add(json);
        }
        return AlvesJSONResult.ok(jsonObjectList);
    }
    @ResponseBody
    @GetMapping("add_secondary_derivation")
    public AlvesJSONResult insertSecondaryDerivation(@RequestBody EventSource eventSource) {
        EventResult eventResult = es.selectById(eventSource.getEventId());
        DataType dataType2 = new DataType();
        dataType2.setId(1l);
        List<DataType> list = dts.queryResources(dataType2);
        Iterator<DataType> iterator = list.iterator();
        List<RiskLevel> riskLevelList = sds.selectByPtId(eventResult.getPtId());
        while (iterator.hasNext()) {
            DataType dataType1 = iterator.next();
            boolean flag = DistanceUtil.getDistance(eventResult.getLat(), eventResult.getLng(), dataType1.getLat(), dataType1.getLng(), eventSource.getInfluenceScope());
            if (flag) {
                double distance = DistanceUtil.getDistance(eventResult.getLat(), eventResult.getLng(), dataType1.getLat(), dataType1.getLng());
                for (RiskLevel riskLevel : riskLevelList) {
                    if (distance >= riskLevel.getLatelyDistance() && distance < riskLevel.getFurthestDistance()) {
                        EventRisk eventRisk = new EventRisk();
                        eventRisk.setEventId(eventResult.getId());
                        eventRisk.setEventTitle(eventResult.getEventTitle());
                        eventRisk.setRlId(riskLevel.getId());
                        eventRisk.setHid(dataType1.getId());
                        eventRisk.setHName(dataType1.getTypeName());
                        eventRisk.setDistance(distance);
                        sds.insert(eventRisk);
                    }
                }

            }
        }
        return AlvesJSONResult.ok();
    }

    @ResponseBody
    @GetMapping("list_secondary_derivation/{eventId}")
    public AlvesJSONResult listSecondaryDerivation(@PathVariable Long eventId) {
        List<EventRiskResult> list = sds.selectByEventId(eventId);
        return AlvesJSONResult.ok(list);
    }

    @ResponseBody
    @GetMapping("list_research_report/{eventId}")
    public AlvesJSONResult listResearchReport(@PathVariable Long eventId) {
        String report = StringUtil.toStr(PropertiesUtils.getInstance().getProperty("researchReport"));
    	EventResult event = es.selectById(eventId);
    	JSONObject resultJson = new JSONObject();

        //事件参数
    	List<EventParamResult> list = es.selectParamByEId(eventId);
    	StringBuffer paramBuffer = new StringBuffer();
        for (EventParamResult ep : list) {
            paramBuffer.append(ep.getName()).append(ep.getPpValue()).append(ep.getUnit()).append("，");
    	}
        resultJson.put("eventReport", MessageFormat.format(report, event.getReceiveTime(), event.getReporter(),
                event.getIncidentTime(), event.getIncidentLocation(), event.getPtName(),
                paramBuffer.deleteCharAt(paramBuffer.length()-1)));
        //危险源
        String hazardSourceReport = StringUtil.toStr(PropertiesUtils.getInstance().getProperty("hazardSourceReport"));
        StringBuffer dataBuffer = new StringBuffer();
        List<HazardSouce> hazardSourceList = hss.queryForAll(new HazardSouce());
        if (hazardSourceList == null || hazardSourceList.size() == 0) {
            dataBuffer.append("无危险源,");
        } else {
            for (HazardSouce hazardSouce : hazardSourceList) {
                dataBuffer.append(hazardSouce.getName()).append("，地址：").append(hazardSouce.getAddr()).append("，负责人：").
                        append(hazardSouce.getPIC()).append("，联系电话：").append(hazardSouce.getCellNum()).append("；");
            }
        }
        resultJson.put("hazardSourceReport", MessageFormat.format(hazardSourceReport, dataBuffer.deleteCharAt(dataBuffer.length() - 1)));
        //预案
        String reservePlanReport = StringUtil.toStr(PropertiesUtils.getInstance().getProperty("reservePlanReport"));
        List<ReservePlanResult> reservePlanResultList = rps.selectByEId(eventId);
        if (reservePlanResultList != null && reservePlanResultList.size() > 0) {
            ReservePlanResult reservePlan = reservePlanResultList.get(0);
            resultJson.put("reservePlanReport", MessageFormat.format(reservePlanReport, reservePlan.getPvName(), reservePlan.getPrLevel()));
        }
        //应急物资
        String emergencySupplyReport = StringUtil.toStr(PropertiesUtils.getInstance().getProperty("emergencySupplyReport"));
        StringBuffer emergencySupplyBuffer = new StringBuffer();
        List<EmergencySupply> emergencySupplyList = ess.queryForAll(new EmergencySupply());
        if (emergencySupplyList == null || emergencySupplyList.size() == 0) {
            emergencySupplyBuffer.append("无应急物质,");
        } else {
            for (EmergencySupply emergencySupply : emergencySupplyList) {
                emergencySupplyBuffer.append(emergencySupply.getName()).append("，数量：").append(emergencySupply.getSupplyNum()).
                        append("(").append(emergencySupply.getMeasurementUnit()).append(")，联系人：").
                        append(emergencySupply.getContactName()).append("，联系电话：").append(emergencySupply.getContactNum()).append("；");
            }
        }
        resultJson.put("emergencySupplyReport", MessageFormat.format(emergencySupplyReport, emergencySupplyBuffer.deleteCharAt(emergencySupplyBuffer.length() - 1)));
        //次生衍生
        String riskReport = StringUtil.toStr(PropertiesUtils.getInstance().getProperty("riskReport"));
        StringBuffer riskBuffer = new StringBuffer();
        List<EventRiskResult> riskList = sds.selectByEventId(eventId);
        if (riskList == null || riskList.size() == 0) {
            riskBuffer.append("无次生衍生，");
        } else {
            for (EventRiskResult riskResult : riskList) {
                riskBuffer.append(riskResult.getEventTitle()).append("，等级：").append(riskResult.getLevel()).append("，距离：").append(riskResult.getDistance()).append("米；");
            }
        }
        resultJson.put("riskReport", MessageFormat.format(riskReport, riskBuffer.deleteCharAt(riskBuffer.length() - 1)));
        return AlvesJSONResult.ok(resultJson);
    }
}
