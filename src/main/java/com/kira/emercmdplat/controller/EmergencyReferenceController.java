package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.pojo.EmergencyReference;
import com.kira.emercmdplat.service.EmergencyReferenceService;
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
@RequestMapping("/emergencyReference")
public class EmergencyReferenceController {

    @Autowired
    private EmergencyReferenceService emergencyReferenceService;

    @RequestMapping("/add")
    public AlvesJSONResult insert(EmergencyReference emergencyReference) {
    	emergencyReferenceService.insert(emergencyReference);
        return AlvesJSONResult.ok();
    }
    
    @RequestMapping("/update")
    public AlvesJSONResult update(EmergencyReference emergencyReference) {
    	emergencyReferenceService.update(emergencyReference);
        return AlvesJSONResult.ok();
    }
    
    @RequestMapping("/delete")
    public AlvesJSONResult delete(EmergencyReference emergencyReference) {
    	emergencyReferenceService.delete(emergencyReference);
        return AlvesJSONResult.ok();
    }
    
    @RequestMapping("/selectById")
    public AlvesJSONResult selectById(Integer id) {
    	EmergencyReference emergencyReference = emergencyReferenceService.selectById(id);
        return AlvesJSONResult.ok(emergencyReference);
    }
    
    @RequestMapping("/list")
    public AlvesJSONResult list(EmergencyReference emergencyReference,Integer page,Integer pageSize) {
    	Map<String,Object> map = new HashMap<>();
    	List<EmergencyReference> list = emergencyReferenceService.queryForPage(emergencyReference, page, pageSize);
    	Long count = emergencyReferenceService.queryForCounts(emergencyReference);
    	map.put("list", list);
    	map.put("count", count);
        return AlvesJSONResult.ok(map);
    }
}
