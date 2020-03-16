package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.pojo.Shelter;
import com.kira.emercmdplat.service.ShelterService;
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
@RequestMapping("/shelter")
public class ShelterController {

    @Autowired
    private ShelterService shelterService;

    @RequestMapping("/add")
    public AlvesJSONResult insert(Shelter shelter) {
    	shelterService.insert(shelter);
        return AlvesJSONResult.ok();
    }
    
    @RequestMapping("/update")
    public AlvesJSONResult update(Shelter shelter) {
    	shelterService.update(shelter);
        return AlvesJSONResult.ok();
    }
    
    @RequestMapping("/delete")
    public AlvesJSONResult delete(Shelter shelter) {
    	shelterService.delete(shelter);
        return AlvesJSONResult.ok();
    }
    
    @RequestMapping("/selectById")
    public AlvesJSONResult selectById(Integer id) {
    	Shelter shelter = shelterService.selectById(id);
        return AlvesJSONResult.ok(shelter);
    }
    
    @RequestMapping("/list")
    public AlvesJSONResult list(Shelter shelter,Integer page,Integer pageSize) {
    	Map<String,Object> map = new HashMap<>();
    	List<Shelter> list = shelterService.queryForPage(shelter, page, pageSize);
    	Long count = shelterService.queryForCounts(shelter);
    	map.put("list", list);
    	map.put("count", count);
        return AlvesJSONResult.ok(map);
    }
}
