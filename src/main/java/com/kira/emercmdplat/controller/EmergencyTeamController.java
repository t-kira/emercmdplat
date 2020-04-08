package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.EmergencyTeam;
import com.kira.emercmdplat.service.EmergencyTeamService;
import com.kira.emercmdplat.utils.AlvesJSONResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kira
 * @Date: 2020/2/4 23:03
 * @Description:
 */
@RestController
@RequestMapping("/emergencyTeam")
public class EmergencyTeamController extends BaseController {

    @Autowired
    private EmergencyTeamService emergencyTeamService;

    @RequestMapping("/add")
    public AlvesJSONResult insert(EmergencyTeam emergencyTeam) {
        emergencyTeamService.insert(emergencyTeam);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/update")
    public AlvesJSONResult update(EmergencyTeam emergencyTeam) {
        emergencyTeamService.update(emergencyTeam);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/delete")
    public AlvesJSONResult delete(EmergencyTeam emergencyTeam) {
        emergencyTeamService.delete(emergencyTeam);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/selectById")
    public AlvesJSONResult selectById(Integer id) {
        EmergencyTeam emergencyTeam = emergencyTeamService.selectById(id);
        return AlvesJSONResult.ok(emergencyTeam);
    }

    @RequestMapping("/list")
    public AlvesJSONResult list(EmergencyTeam emergencyTeam, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        List<EmergencyTeam> list = emergencyTeamService.queryForPage(emergencyTeam, page, pageSize);
        Long count = emergencyTeamService.queryForCounts(emergencyTeam);
        map.put("list", list);
        map.put("count", count);
        return AlvesJSONResult.ok(map);
    }
}
