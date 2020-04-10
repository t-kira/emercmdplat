package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.HazardSouce;
import com.kira.emercmdplat.service.HazardSourceService;
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
@RequestMapping("/hazardSource")
public class HazardSourceController extends BaseController {

    @Autowired
    private HazardSourceService hazardSourceService;

    @RequestMapping("/add")
    public AlvesJSONResult insert(HazardSouce hazardSouce) {
        hazardSourceService.insert(hazardSouce);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/update")
    public AlvesJSONResult update(HazardSouce hazardSouce) {
        hazardSourceService.update(hazardSouce);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/delete")
    public AlvesJSONResult delete(HazardSouce hazardSouce) {
        hazardSourceService.delete(hazardSouce);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/selectById")
    public AlvesJSONResult selectById(Integer id) {
        HazardSouce hazardSource = hazardSourceService.selectById(id);
        return AlvesJSONResult.ok(hazardSource);
    }

    @RequestMapping("/list")
    public AlvesJSONResult list(HazardSouce hazardSouce, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        List<HazardSouce> list = hazardSourceService.queryForPage(hazardSouce, page, pageSize);
        Long count = hazardSourceService.queryForCounts(hazardSouce);
        map.put("list", list);
        map.put("count", count);
        return AlvesJSONResult.ok(map);
    }
}
