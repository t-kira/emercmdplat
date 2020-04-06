package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.pojo.EmergencyFund;
import com.kira.emercmdplat.service.EmergencyFundService;
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
@RequestMapping("/emergencyFund")
public class EmergencyFundController {

    @Autowired
    private EmergencyFundService emergencyFundService;

    @RequestMapping("/add")
    public AlvesJSONResult insert(EmergencyFund emergencyFund) {
        emergencyFundService.insert(emergencyFund);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/update")
    public AlvesJSONResult update(EmergencyFund emergencyFund) {
        emergencyFundService.update(emergencyFund);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/delete")
    public AlvesJSONResult delete(EmergencyFund emergencyFund) {
        emergencyFundService.delete(emergencyFund);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/selectById")
    public AlvesJSONResult selectById(Integer id) {
        EmergencyFund emergencyFund = emergencyFundService.selectById(id);
        return AlvesJSONResult.ok(emergencyFund);
    }

    @RequestMapping("/list")
    public AlvesJSONResult list(EmergencyFund emergencyFund, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        List<EmergencyFund> list = emergencyFundService.queryForPage(emergencyFund, page, pageSize);
        Long count = emergencyFundService.queryForCounts(emergencyFund);
        map.put("list", list);
        map.put("count", count);
        return AlvesJSONResult.ok(map);
    }
}
