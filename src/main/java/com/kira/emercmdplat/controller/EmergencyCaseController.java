package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.pojo.EmergencyCase;
import com.kira.emercmdplat.service.EmergencyCaseService;
import com.kira.emercmdplat.utils.AlvesJSONResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kira
 * @Date: 2020/2/4 23:03
 * @Description:
 */
@RestController
@RequestMapping("/emergencyCase")
public class EmergencyCaseController extends BaseController {

    @Autowired
    private EmergencyCaseService emergencyCaseService;

    @RequestMapping(name="添加案例分析",value="/add",method=RequestMethod.POST)
    public AlvesJSONResult insert(@RequestBody EmergencyCase emergencyCase) {
        int result = emergencyCaseService.insert(emergencyCase);
        if (result > 0) {
        	return AlvesJSONResult.ok();
        } else {
        	throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "案例分析保存失败");
        }
    }

    @RequestMapping(name="修改案例分析",value="/update",method=RequestMethod.POST)
    public AlvesJSONResult update(@RequestBody EmergencyCase emergencyCase) {
        boolean result = emergencyCaseService.update(emergencyCase);
        if (result) {
        	return AlvesJSONResult.ok();
        } else {
        	throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "应急专家保存失败");
        }
    }

    @RequestMapping(name="删除案例分析",value="/delete",method=RequestMethod.GET)
    public AlvesJSONResult delete(Integer id) {
    	EmergencyCase emergencyCase = emergencyCaseService.selectById(id);
        boolean result = emergencyCaseService.delete(emergencyCase);
        if (result) {
        	return AlvesJSONResult.ok();
        } else {
        	throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "案例分析删除失败");
        }
    }

    @RequestMapping(name="根据id查询案例分析",value="/selectById",method=RequestMethod.GET)
    public AlvesJSONResult selectById(Integer id) {
        EmergencyCase emergencyCase = emergencyCaseService.selectById(id);
        return AlvesJSONResult.ok(emergencyCase);
    }

    @RequestMapping(name="列出案例分析",value="/list",method=RequestMethod.POST)
    public AlvesJSONResult list(@RequestBody EmergencyCase emergencyCase) {
        Map<String, Object> map = new HashMap<>();
        List<EmergencyCase> list = emergencyCaseService.queryForPage(emergencyCase, emergencyCase.getPage(), emergencyCase.getPageSize());
        Long count = emergencyCaseService.queryForCounts(emergencyCase);
        map.put("list", list);
        map.put("count", count);
        return AlvesJSONResult.ok(map);
    }
}
