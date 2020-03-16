package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.pojo.EmergencySupply;
import com.kira.emercmdplat.service.EmergencySupplyService;
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
@RequestMapping("/emergencySupply")
public class EmergencySupplyController {

    @Autowired
    private EmergencySupplyService emergencySupplyService;

    @RequestMapping("/add")
    public AlvesJSONResult insert(EmergencySupply emergencySupply) {
    	emergencySupplyService.insert(emergencySupply);
        return AlvesJSONResult.ok();
    }
    
    @RequestMapping("/update")
    public AlvesJSONResult update(EmergencySupply emergencySupply) {
    	emergencySupplyService.update(emergencySupply);
        return AlvesJSONResult.ok();
    }
    
    @RequestMapping("/delete")
    public AlvesJSONResult delete(EmergencySupply emergencySupply) {
    	emergencySupplyService.delete(emergencySupply);
        return AlvesJSONResult.ok();
    }
    
    @RequestMapping("/selectById")
    public AlvesJSONResult selectById(Integer id) {
    	EmergencySupply emergencySupply = emergencySupplyService.selectById(id);
        return AlvesJSONResult.ok(emergencySupply);
    }
    
    @RequestMapping("/list")
    public AlvesJSONResult list(EmergencySupply emergencySupply,Integer page,Integer pageSize) {
    	Map<String,Object> map = new HashMap<>();
    	List<EmergencySupply> list = emergencySupplyService.queryForPage(emergencySupply, page, pageSize);
    	Long count = emergencySupplyService.queryForCounts(emergencySupply);
    	map.put("list", list);
    	map.put("count", count);
        return AlvesJSONResult.ok(map);
    }
}
