package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.pojo.HazardSouce;
import com.kira.emercmdplat.service.HazardSourceService;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kira
 * @Date: 2020/2/4 23:03
 * @Description:
 */
@RestController
@RequestMapping("/hazardSource")
public class HazardSourceController {

    @Autowired
    private HazardSourceService hss;

    @ResponseBody
    @RequestMapping("add")
    public AlvesJSONResult insert(HazardSouce hazardSouce) {
        hss.insert(hazardSouce);
        return AlvesJSONResult.ok();
    }
}
