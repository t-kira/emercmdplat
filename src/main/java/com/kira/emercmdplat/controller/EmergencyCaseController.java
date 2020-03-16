package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.pojo.EmergencyCase;
import com.kira.emercmdplat.service.EmergencyCaseService;
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
@RequestMapping("/emergencyCase")
public class EmergencyCaseController {

    @Autowired
    private EmergencyCaseService emergencyCaseService;

    @RequestMapping("/add")
    public AlvesJSONResult insert(EmergencyCase emergencyCase) {
    	emergencyCaseService.insert(emergencyCase);
        return AlvesJSONResult.ok();
    }
    
    @RequestMapping("/update")
    public AlvesJSONResult update(EmergencyCase emergencyCase) {
    	emergencyCaseService.update(emergencyCase);
        return AlvesJSONResult.ok();
    }
    
    @RequestMapping("/delete")
    public AlvesJSONResult delete(EmergencyCase emergencyCase) {
    	emergencyCaseService.delete(emergencyCase);
        return AlvesJSONResult.ok();
    }
    
    @RequestMapping("/selectById")
    public AlvesJSONResult selectById(Integer id) {
    	EmergencyCase emergencyCase = emergencyCaseService.selectById(id);
        return AlvesJSONResult.ok(emergencyCase);
    }
    
    @RequestMapping("/list")
    public AlvesJSONResult list(EmergencyCase emergencyCase,Integer page,Integer pageSize) {
    	Map<String,Object> map = new HashMap<>();
    	List<EmergencyCase> list = emergencyCaseService.queryForPage(emergencyCase, page, pageSize);
    	Long count = emergencyCaseService.queryForCounts(emergencyCase);
    	map.put("list", list);
    	map.put("count", count);
        return AlvesJSONResult.ok(map);
    }
}
