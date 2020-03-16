package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.pojo.TransportUnit;
import com.kira.emercmdplat.service.TransportUnitService;
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
@RequestMapping("/transportUnit")
public class TransportUnitController {

    @Autowired
    private TransportUnitService transportUnitService;

    @RequestMapping("/add")
    public AlvesJSONResult insert(TransportUnit transportUnit) {
    	transportUnitService.insert(transportUnit);
        return AlvesJSONResult.ok();
    }
    
    @RequestMapping("/update")
    public AlvesJSONResult update(TransportUnit transportUnit) {
    	transportUnitService.update(transportUnit);
        return AlvesJSONResult.ok();
    }
    
    @RequestMapping("/delete")
    public AlvesJSONResult delete(TransportUnit transportUnit) {
    	transportUnitService.delete(transportUnit);
        return AlvesJSONResult.ok();
    }
    
    @RequestMapping("/selectById")
    public AlvesJSONResult selectById(Integer id) {
    	TransportUnit transportUnit = transportUnitService.selectById(id);
        return AlvesJSONResult.ok(transportUnit);
    }
    
    @RequestMapping("/list")
    public AlvesJSONResult list(TransportUnit transportUnit,Integer page,Integer pageSize) {
    	Map<String,Object> map = new HashMap<>();
    	List<TransportUnit> list = transportUnitService.queryForPage(transportUnit, page, pageSize);
    	Long count = transportUnitService.queryForCounts(transportUnit);
    	map.put("list", list);
    	map.put("count", count);
        return AlvesJSONResult.ok(map);
    }
}
