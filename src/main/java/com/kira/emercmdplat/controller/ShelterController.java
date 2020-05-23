package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.Shelter;
import com.kira.emercmdplat.pojo.ShelterResult;
import com.kira.emercmdplat.service.ShelterService;
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
@Api2Doc(id = "shelter", name = "避难场所接口", order = 13)
@RestController
@RequestMapping("/shelter")
public class ShelterController extends BaseController {

    @Autowired
    private ShelterService shelterService;

    @RequestMapping("/add")
    public AlvesJSONResult insert(Shelter shelter) {
        shelterService.insert(shelter);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/update")
    public AlvesJSONResult update(Shelter shelter) {
        shelterService.update(shelter);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/delete")
    public AlvesJSONResult delete(Shelter shelter) {
        shelterService.delete(shelter);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/selectById")
    public AlvesJSONResult selectById(Integer id) {
        Shelter shelter = shelterService.selectById(id);
        return AlvesJSONResult.ok(shelter);
    }
    
    @Api2Doc(order = 5)
    @ApiComment(value="列出避难场所")
    @RequestMapping(name="列出避难场所",value="/list",method=RequestMethod.POST)
    public ShelterResult list(@ApiComment(value="避难场所参数",sample="根据id查询避难场所接口可查看字段信息") @RequestBody Shelter shelter) {
    	ShelterResult result = new ShelterResult();
        List<Shelter> list = shelterService.queryForPage(shelter, shelter.getPage(), shelter.getPageSize());
        Long count = shelterService.queryForCounts(shelter);
        result.setList(list);
        result.setCount(count);
        return result;
    }
}
