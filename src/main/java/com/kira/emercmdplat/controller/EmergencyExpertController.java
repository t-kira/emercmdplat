package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.pojo.EmergencyExpert;
import com.kira.emercmdplat.service.EmergencyExpertService;
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
@RequestMapping("/emergencyExpert")
public class EmergencyExpertController {

    @Autowired
    private EmergencyExpertService emergencyExpertService;

    @RequestMapping("/add")
    public AlvesJSONResult insert(EmergencyExpert emergencyExpert) {
    	emergencyExpertService.insert(emergencyExpert);
        return AlvesJSONResult.ok();
    }
    
    @RequestMapping("/update")
    public AlvesJSONResult update(EmergencyExpert emergencyExpert) {
    	emergencyExpertService.update(emergencyExpert);
        return AlvesJSONResult.ok();
    }
    
    @RequestMapping("/delete")
    public AlvesJSONResult delete(EmergencyExpert emergencyExpert) {
    	emergencyExpertService.delete(emergencyExpert);
        return AlvesJSONResult.ok();
    }
    
    @RequestMapping("/selectById")
    public AlvesJSONResult selectById(Integer id) {
    	EmergencyExpert emergencyExpert = emergencyExpertService.selectById(id);
        return AlvesJSONResult.ok(emergencyExpert);
    }
    
    @RequestMapping("/list")
    public AlvesJSONResult list(EmergencyExpert emergencyExpert,Integer page,Integer pageSize) {
    	Map<String,Object> map = new HashMap<>();
    	List<EmergencyExpert> list = emergencyExpertService.queryForPage(emergencyExpert, page, pageSize);
    	Long count = emergencyExpertService.queryForCounts(emergencyExpert);
    	map.put("list", list);
    	map.put("count", count);
        return AlvesJSONResult.ok(map);
    }
}
