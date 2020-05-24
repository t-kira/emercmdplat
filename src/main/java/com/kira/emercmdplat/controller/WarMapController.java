package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.enums.SourceType;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.*;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import com.kira.emercmdplat.utils.TreeUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private HazardSourceService hss;
    @Autowired
    private ProtectionTargetService pts;
    @Autowired
    private EmergencyTeamService emts;
    @Autowired
    private MedicalInstitutionService mis;
    @Autowired
    private ShelterService ss;
    @Autowired
    private ReserveLibraryService rls;
    @Autowired
    private EmergencyExpertService ees;
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
        JSONObject json = new JSONObject();
        EventResult eventResult = es.selectById(eventSource.getEventId());
        //危险源
        List<HazardSouce> hazardSourceList = hss.queryForAll(new HazardSouce());
        //防护目标
        List<ProtectionTarget> protectionTargetList = pts.queryForAll(new ProtectionTarget());
        //应急队伍
        List<EmergencyTeam> emergencyTeamList = emts.queryForAll(new EmergencyTeam());
        //医疗机构
        List<MedicalInstitution> medicalInstitutionList = mis.queryForAll(new MedicalInstitution());
        //避难场所
        List<Shelter> shelterList = ss.queryForAll(new Shelter());
        //物资库
        List<ReserveLibrary> reserveLibraryList = rls.queryForAll(new ReserveLibrary());
        //应急专家
        List<EmergencyExpert> emergencyExpertList = ees.queryForAll(new EmergencyExpert());

        json.put("hazardSourceList", hazardSourceList);
        json.put("protectionTargetList", protectionTargetList);
        json.put("emergencyTeamList", emergencyTeamList);
        json.put("medicalInstitutionList", medicalInstitutionList);
        json.put("shelterList", shelterList);
        json.put("reserveLibraryList", reserveLibraryList);
        json.put("emergencyExpertList", emergencyExpertList);

        return AlvesJSONResult.ok(json);
    }

    /**
     * 任务指派
     * @return
     */
    @ResponseBody
    @PostMapping("task_assignment")
    public AlvesJSONResult taskAssignment(@RequestBody TaskAssignment taskAssignment) {
        if (taskAssignment.getType().intValue() == SourceType.HAZARD_SOURCE.getNo().intValue()) {

        }
        return AlvesJSONResult.ok();
    }
}
