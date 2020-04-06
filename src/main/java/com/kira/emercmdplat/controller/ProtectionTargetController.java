package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.pojo.ProtectionTarget;
import com.kira.emercmdplat.service.ProtectionTargetService;
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
@RequestMapping("/protectionTarget")
public class ProtectionTargetController {

    @Autowired
    private ProtectionTargetService protectionTargetService;

    @RequestMapping("/add")
    public AlvesJSONResult insert(ProtectionTarget protectionTarget) {
        protectionTargetService.insert(protectionTarget);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/update")
    public AlvesJSONResult update(ProtectionTarget protectionTarget) {
        protectionTargetService.update(protectionTarget);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/delete")
    public AlvesJSONResult delete(ProtectionTarget protectionTarget) {
        protectionTargetService.delete(protectionTarget);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/selectById")
    public AlvesJSONResult selectById(Integer id) {
        ProtectionTarget protectionTarget = protectionTargetService.selectById(id);
        return AlvesJSONResult.ok(protectionTarget);
    }

    @RequestMapping("/list")
    public AlvesJSONResult list(ProtectionTarget protectionTarget, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        List<ProtectionTarget> list = protectionTargetService.queryForPage(protectionTarget, page, pageSize);
        Long count = protectionTargetService.queryForCounts(protectionTarget);
        map.put("list", list);
        map.put("count", count);
        return AlvesJSONResult.ok(map);
    }
}
