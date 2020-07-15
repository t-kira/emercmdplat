package com.kira.emercmdplat.controller;

import com.alibaba.druid.util.StringUtils;
import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.pojo.EmergencyExpert;
import com.kira.emercmdplat.pojo.EmergencyExpertResult;
import com.kira.emercmdplat.service.EmergencyExpertService;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kira
 * @Date: 2020/2/4 23:03
 * @Description:
 */
@Api2Doc(id = "emergencyExpert", name = "应急专家接口", order = 4)
@RestController
@RequestMapping("/emergencyExpert")
public class EmergencyExpertController extends BaseController {

    @Autowired
    private EmergencyExpertService emergencyExpertService;
    
    @Api2Doc(order = 1)
    @ApiComment(value="添加应急专家")
    @RequestMapping(name="添加应急专家",value="/add",method=RequestMethod.POST)
    public AlvesJSONResult insert(@Validated @ApiComment(value="添加应急专家",sample="根据id查询专家接口可查看字段信息") @RequestBody EmergencyExpert emergencyExpert) {
        int result = emergencyExpertService.insert(emergencyExpert);
        if (result > 0) {
        	return AlvesJSONResult.ok();
        } else {
        	throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "应急专家保存失败");
        }
    }

    @Api2Doc(order = 2)
    @ApiComment(value="修改应急专家")
    @RequestMapping(name="修改应急专家",value="/update",method=RequestMethod.POST)
    public AlvesJSONResult update(@Validated @ApiComment(value="修改应急专家",sample="根据id查询专家接口可查看字段信息") @RequestBody EmergencyExpert emergencyExpert) {
        boolean result = emergencyExpertService.update(emergencyExpert);
        if (result) {
        	return AlvesJSONResult.ok();
        } else {
        	throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "应急专家保存失败");
        }
    }

    @Api2Doc(order = 3)
    @ApiComment(value="删除应急专家")
    @RequestMapping(name="删除应急专家",value="/delete",method=RequestMethod.GET)
    public AlvesJSONResult delete(@ApiComment(value="专家id",sample="1") String ids) {
    	if (StringUtils.isEmpty(ids)) {
    		throw new CustomException(ResultEnum.ERROR_PARAMETER.getNo(), "ids为空");
    	}
    	String[] idList = ids.split(",");
    	for (String id : idList) {
	    	EmergencyExpert emergencyExpert = emergencyExpertService.selectById(Integer.valueOf(id));
	        emergencyExpertService.delete(emergencyExpert);
    	}
        return AlvesJSONResult.ok();
    }
    
    @Api2Doc(order = 4)
    @ApiComment(value="根据id查询专家")
    @RequestMapping(name="根据id查询专家",value="/selectById",method=RequestMethod.GET)
    public AlvesJSONResult selectById(@ApiComment(value="专家id",sample="1") Integer id) {
        EmergencyExpert emergencyExpert = emergencyExpertService.selectById(id);
        return AlvesJSONResult.ok(emergencyExpert);
    }

    @Api2Doc(order = 5)
    @ApiComment(value="列出专家")
    @RequestMapping(name="列出专家",value="/list",method=RequestMethod.POST)
    public AlvesJSONResult list(@ApiComment(value="专家查询参数",sample="根据id查询专家接口可查看字段信息") @RequestBody EmergencyExpert emergencyExpert) {
    	EmergencyExpertResult result = new EmergencyExpertResult();
        List<EmergencyExpert> list = emergencyExpertService.queryForPage(emergencyExpert, emergencyExpert.getPage(), emergencyExpert.getPageSize());
        Long count = emergencyExpertService.queryForCounts(emergencyExpert);
        result.setList(list);
        result.setCount(count);
        return AlvesJSONResult.ok(result);
    }
}
