package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.pojo.EmergencyReference;
import com.kira.emercmdplat.service.EmergencyReferenceService;
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
@RequestMapping("/emergencyReference")
public class EmergencyReferenceController extends BaseController {

    @Autowired
    private EmergencyReferenceService emergencyReferenceService;

    @RequestMapping(name="添加知识库",value="/add",method=RequestMethod.POST)
    public AlvesJSONResult insert(@RequestBody EmergencyReference emergencyReference) {
    	int result = emergencyReferenceService.insert(emergencyReference);
    	if (result > 0) {
    		return AlvesJSONResult.ok();
    	} else {
    		throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "知识库保存失败");
    	}
    }

    @RequestMapping(name="修改知识库",value="/update",method=RequestMethod.POST)
    public AlvesJSONResult update(@RequestBody EmergencyReference emergencyReference) {
        boolean result = emergencyReferenceService.update(emergencyReference);
        if (result) {
        	return AlvesJSONResult.ok();
        } else {
        	throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "知识库保存失败");
        }
    }

    @RequestMapping(name="删除知识库",value="/delete",method=RequestMethod.GET)
    public AlvesJSONResult delete(Integer id) {
        EmergencyReference emergencyReference = emergencyReferenceService.selectById(id);
        emergencyReferenceService.delete(emergencyReference);
        return AlvesJSONResult.ok();
    }

    @RequestMapping(name="根据id查询知识库",value="/selectById",method=RequestMethod.GET)
    public AlvesJSONResult selectById(Integer id) {
    	EmergencyReference emergencyReference = emergencyReferenceService.selectById(id);
        return AlvesJSONResult.ok(emergencyReference);
    }

    @RequestMapping(name="列出知识库",value="/list",method=RequestMethod.POST)
    public AlvesJSONResult list(@RequestBody EmergencyReference emergencyReference) {
        Map<String, Object> map = new HashMap<>();
        List<EmergencyReference> list = emergencyReferenceService.queryForPage(emergencyReference, emergencyReference.getPage(), emergencyReference.getPageSize());
        Long count = emergencyReferenceService.queryForCounts(emergencyReference);
        map.put("list", list);
        map.put("count", count);
        return AlvesJSONResult.ok(map);
    }
}
