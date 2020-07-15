package com.kira.emercmdplat.controller;

import com.alibaba.druid.util.StringUtils;
import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.pojo.EmergencySupply;
import com.kira.emercmdplat.pojo.EmergencySupplyResult;
import com.kira.emercmdplat.service.EmergencySupplyService;
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
@Api2Doc(id = "emergencySupply", name = "应急物资接口", order = 5)
@RestController
@RequestMapping("/emergencySupply")
public class EmergencySupplyController extends BaseController {

    @Autowired
    private EmergencySupplyService emergencySupplyService;
    
    @Api2Doc(order = 1)
    @ApiComment(value="添加应急物资")
    @RequestMapping(name="添加应急物资",value="/add",method=RequestMethod.POST)
    public AlvesJSONResult insert(@Validated @ApiComment(value="添加应急物资",sample="根据id查询物资接口可查看字段信息") @RequestBody EmergencySupply emergencySupply) {
        int result = emergencySupplyService.insert(emergencySupply);
        if (result > 0) {
        	return AlvesJSONResult.ok();
        } else {
        	throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "应急物资保存失败");
        }
    }
    
    @Api2Doc(order = 2)
    @ApiComment(value="修改应急物资")
    @RequestMapping(name="修改应急物资",value="/update",method=RequestMethod.POST)
    public AlvesJSONResult update(@Validated @ApiComment(value="修改应急物资",sample="根据id查询物资接口可查看字段信息") @RequestBody EmergencySupply emergencySupply) {
        boolean result = emergencySupplyService.update(emergencySupply);
        if (result) {
        	return AlvesJSONResult.ok();
        } else {
        	throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "应急物资保存失败");
        }
    }
    
    @Api2Doc(order = 3)
    @ApiComment(value="删除应急物资")
    @RequestMapping(name="删除应急物资",value="/delete",method=RequestMethod.GET)
    public AlvesJSONResult delete(@ApiComment(value="物资id",sample="1") String ids) {
    	if (StringUtils.isEmpty(ids)) {
    		throw new CustomException(ResultEnum.ERROR_PARAMETER.getNo(), "ids为空");
    	}
    	String[] idList = ids.split(",");
    	for (String id : idList) {
	    	EmergencySupply emergencySupply = emergencySupplyService.selectById(Integer.valueOf(id));
	        emergencySupplyService.delete(emergencySupply);
    	}
        return AlvesJSONResult.ok();
    }
    
    @Api2Doc(order = 4)
    @ApiComment(value="根据id查询物资")
    @RequestMapping(name="根据id查询物资",value="/selectById",method=RequestMethod.GET)
    public AlvesJSONResult selectById(@ApiComment(value="物资id",sample="1") Integer id) {
        EmergencySupply emergencySupply = emergencySupplyService.selectById(id);
        return AlvesJSONResult.ok(emergencySupply);
    }
    
    @Api2Doc(order = 5)
    @ApiComment(value="列出物资")
    @RequestMapping(name="列出物资",value="/list",method=RequestMethod.POST)
    public AlvesJSONResult list(@ApiComment(value="列出应急物资",sample="根据id查询物资接口可查看字段信息") @RequestBody EmergencySupply emergencySupply) {
    	EmergencySupplyResult result = new EmergencySupplyResult();
        List<EmergencySupply> list = emergencySupplyService.queryForPage(emergencySupply, emergencySupply.getPage(), emergencySupply.getPageSize());
        Long count = emergencySupplyService.queryForCounts(emergencySupply);
        result.setList(list);
        result.setCount(count);
        return AlvesJSONResult.ok(result);
    }
}
