package com.kira.emercmdplat.controller;

import com.alibaba.druid.util.StringUtils;
import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.EmergencySupply;
import com.kira.emercmdplat.pojo.EmergencySupplyResult;
import com.kira.emercmdplat.service.EmergencySupplyService;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String insert(@Validated @ApiComment(value="添加应急物资",sample="根据id查询物资接口可查看字段信息") @RequestBody EmergencySupply emergencySupply) {
        emergencySupplyService.insert(emergencySupply);
        return "success";
    }
    
    @Api2Doc(order = 2)
    @ApiComment(value="修改应急物资")
    @RequestMapping(name="修改应急物资",value="/update",method=RequestMethod.POST)
    public String update(@Validated @ApiComment(value="修改应急物资",sample="根据id查询物资接口可查看字段信息") @RequestBody EmergencySupply emergencySupply) {
        emergencySupplyService.update(emergencySupply);
        return "success";
    }
    
    @Api2Doc(order = 3)
    @ApiComment(value="删除应急物资")
    @RequestMapping(name="删除应急物资",value="/delete",method=RequestMethod.GET)
    public String delete(@ApiComment(value="物资id",sample="1") String ids) {
    	if (StringUtils.isEmpty(ids)) {
    		return "fail";
    	}
    	String[] idList = ids.split(",");
    	for (String id : idList) {
	    	EmergencySupply emergencySupply = emergencySupplyService.selectById(Integer.valueOf(id));
	        emergencySupplyService.delete(emergencySupply);
    	}
        return "success";
    }
    
    @Api2Doc(order = 4)
    @ApiComment(value="根据id查询物资")
    @RequestMapping(name="根据id查询物资",value="/selectById",method=RequestMethod.GET)
    public EmergencySupply selectById(@ApiComment(value="物资id",sample="1") Integer id) {
        EmergencySupply emergencySupply = emergencySupplyService.selectById(id);
        return emergencySupply;
    }
    
    @Api2Doc(order = 5)
    @ApiComment(value="列出物资")
    @RequestMapping(name="列出物资",value="/list",method=RequestMethod.POST)
    public EmergencySupplyResult list(@ApiComment(value="列出应急物资",sample="根据id查询物资接口可查看字段信息") @RequestBody EmergencySupply emergencySupply) {
    	EmergencySupplyResult result = new EmergencySupplyResult();
        List<EmergencySupply> list = emergencySupplyService.queryForPage(emergencySupply, emergencySupply.getPage(), emergencySupply.getPageSize());
        Long count = emergencySupplyService.queryForCounts(emergencySupply);
        result.setList(list);
        result.setCount(count);
        return result;
    }
}
