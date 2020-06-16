package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.enums.SourceType;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.*;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import com.kira.emercmdplat.utils.DistanceUtil;
import com.kira.emercmdplat.utils.StringUtil;
import com.kira.emercmdplat.utils.TreeUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String listResearchReport(@PathVariable Long eventId) {
    	StringBuilder sb = new StringBuilder();
    	EventResult event = es.selectById(eventId);
    	List<EventParamResult> list = es.selectParamByEId(eventId);
    	sb.append(event.getReceiveTime());
    	sb.append(",");
    	sb.append("接" + event.getReporter() + "报");
    	sb.append(event.getIncidentTime());
    	sb.append(",");
    	sb.append(event.getIncidentLocation());
    	sb.append("发生");
    	sb.append(event.getPtName());
    	sb.append("。");
    	sb.append("经核实，截至发文时，发生");
    	for (EventParamResult ep : list) {
    		sb.append(ep.getName() + ep.getPpValue() + ep.getUnit());
    		sb.append(",");
    	}
    	sb.append("。");
    	return sb.toString();
    }
}
