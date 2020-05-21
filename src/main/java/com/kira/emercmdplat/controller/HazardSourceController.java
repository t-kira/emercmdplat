package com.kira.emercmdplat.controller;

import com.alibaba.druid.util.StringUtils;
import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.HazardSouce;
import com.kira.emercmdplat.pojo.HazardSouceResult;
import com.kira.emercmdplat.service.HazardSourceService;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;

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
@Api2Doc(id = "hazardSource", name = "风险隐患接口", order = 8)
@RestController
@RequestMapping("/hazardSource")
public class HazardSourceController extends BaseController {

    @Autowired
    private HazardSourceService hazardSourceService;
    
    @Api2Doc(order = 1)
    @ApiComment(value="添加风险隐患")
    @RequestMapping(name="添加风险隐患",value="/add",method=RequestMethod.POST)
    public String insert(@ApiComment(value="添加风险隐患",sample="根据id查询风险隐患接口可查看字段信息") @RequestBody HazardSouce hazardSouce) {
        hazardSourceService.insert(hazardSouce);
        return "success";
    }
    
    @Api2Doc(order = 2)
    @ApiComment(value="修改风险隐患")
    @RequestMapping(name="修改风险隐患",value="/update",method=RequestMethod.POST)
    public String update(@ApiComment(value="修改风险隐患",sample="根据id查询风险隐患接口可查看字段信息") @RequestBody HazardSouce hazardSouce) {
        hazardSourceService.update(hazardSouce);
        return "success";
    }
    
    @Api2Doc(order = 3)
    @ApiComment(value="删除风险隐患")
    @RequestMapping(name="删除风险隐患",value="/delete",method=RequestMethod.GET)
    public String delete(@ApiComment(value="风险隐患id",sample="1") String ids) {
    	if (StringUtils.isEmpty(ids)) {
    		return "fail";
    	}
    	String[] idList = ids.split(",");
    	for (String id : idList) {
    		HazardSouce hazardSource = hazardSourceService.selectById(Integer.valueOf(id));
    		hazardSourceService.delete(hazardSource);
    	}
        return "success";
    }
    
    @Api2Doc(order = 4)
    @ApiComment(value="根据id查询风险隐患")
    @RequestMapping(name="根据id查询风险隐患",value="/selectById",method=RequestMethod.GET)
    public HazardSouce selectById(@ApiComment(value="风险隐患id",sample="1") Integer id) {
        HazardSouce hazardSource = hazardSourceService.selectById(id);
        return hazardSource;
    }
    
    @Api2Doc(order = 5)
    @ApiComment(value="列出风险隐患")
    @RequestMapping(name="列出风险隐患",value="/list",method=RequestMethod.POST)
    public HazardSouceResult list(@ApiComment(value="风险隐患参数",sample="根据id查询风险隐患接口可查看字段信息") @RequestBody HazardSouce hazardSouce) {
    	HazardSouceResult result = new HazardSouceResult();
        List<HazardSouce> list = hazardSourceService.queryForPage(hazardSouce, hazardSouce.getPage(), hazardSouce.getPageSize());
        Long count = hazardSourceService.queryForCounts(hazardSouce);
        result.setList(list);
        result.setCount(count);
        return result;
    }
}
