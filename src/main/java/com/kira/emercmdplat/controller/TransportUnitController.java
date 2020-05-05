package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.TransportUnit;
import com.kira.emercmdplat.pojo.TransportUnitResult;
import com.kira.emercmdplat.service.TransportUnitService;
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
@Api2Doc(id = "transportUnit", name = "运输单位接口", order = 7)
@RestController
@RequestMapping("/transportUnit")
public class TransportUnitController extends BaseController{

    @Autowired
    private TransportUnitService transportUnitService;
    
    @Api2Doc(order = 1)
    @ApiComment(value="添加运输单位")
    @RequestMapping(name="添加运输单位",value="/add",method=RequestMethod.POST)
    public String insert(@ApiComment(value="添加运输单位",sample="根据id查询运输单位接口可查看字段信息") @RequestBody TransportUnit transportUnit) {
        transportUnitService.insert(transportUnit);
        return "success";
    }
    
    @Api2Doc(order = 2)
    @ApiComment(value="修改运输单位")
    @RequestMapping(name="修改运输单位",value="/update",method=RequestMethod.POST)
    public String update(@ApiComment(value="修改运输单位",sample="根据id查询运输单位接口可查看字段信息") @RequestBody TransportUnit transportUnit) {
        transportUnitService.update(transportUnit);
        return "success";
    }
    
    @Api2Doc(order = 3)
    @ApiComment(value="删除运输单位")
    @RequestMapping(name="删除运输单位",value="/delete",method=RequestMethod.GET)
    public String delete(@ApiComment(value="运输单位id",sample="1") Integer id) {
    	TransportUnit transportUnit = transportUnitService.selectById(id);
        transportUnitService.delete(transportUnit);
        return "success";
    }
    
    @Api2Doc(order = 4)
    @ApiComment(value="根据id查询运输单位")
    @RequestMapping(name="根据id查询运输单位",value="/selectById",method=RequestMethod.GET)
    public TransportUnit selectById(Integer id) {
        TransportUnit transportUnit = transportUnitService.selectById(id);
        return transportUnit;
    }
    
    @Api2Doc(order = 5)
    @ApiComment(value="列出运输单位")
    @RequestMapping(name="列出运输单位",value="/list",method=RequestMethod.POST)
    public TransportUnitResult list(@ApiComment(value="列出运输单位",sample="根据id查询运输单位接口可查看字段信息") @RequestBody TransportUnit transportUnit) {
    	TransportUnitResult result = new TransportUnitResult();
        List<TransportUnit> list = transportUnitService.queryForPage(transportUnit, transportUnit.getPage(), transportUnit.getPageSize());
        Long count = transportUnitService.queryForCounts(transportUnit);
        result.setList(list);
        result.setCount(count);
        return result;
    }
}
