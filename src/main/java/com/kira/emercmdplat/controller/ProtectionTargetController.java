package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.controller.base.BaseController;
import com.kira.emercmdplat.pojo.ProtectionTarget;
import com.kira.emercmdplat.pojo.ProtectionTargetResult;
import com.kira.emercmdplat.service.ProtectionTargetService;
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
@Api2Doc(id = "protectionTarget", name = "防护目标接口", order = 12)
@RestController
@RequestMapping("/protectionTarget")
public class ProtectionTargetController extends BaseController {

    @Autowired
    private ProtectionTargetService protectionTargetService;

    @RequestMapping("/add")
    public AlvesJSONResult insert(ProtectionTarget protectionTarget) {
        protectionTargetService.insert(protectionTarget);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/update")
    public AlvesJSONResult update(ProtectionTarget protectionTarget) {
        protectionTargetService.update(protectionTarget);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/delete")
    public AlvesJSONResult delete(ProtectionTarget protectionTarget) {
        protectionTargetService.delete(protectionTarget);
        return AlvesJSONResult.ok();
    }

    @RequestMapping("/selectById")
    public AlvesJSONResult selectById(Integer id) {
        ProtectionTarget protectionTarget = protectionTargetService.selectById(id);
        return AlvesJSONResult.ok(protectionTarget);
    }
    
    @Api2Doc(order = 5)
    @ApiComment(value="列出防护目标")
    @RequestMapping(name="列出防护目标",value="/list",method=RequestMethod.POST)
    public ProtectionTargetResult list(@ApiComment(value="防护目标参数",sample="根据id查询防护目标接口可查看字段信息") @RequestBody ProtectionTarget protectionTarget) {
    	ProtectionTargetResult result = new ProtectionTargetResult();
        List<ProtectionTarget> list = protectionTargetService.queryForPage(protectionTarget, protectionTarget.getPage(), protectionTarget.getPageSize());
        Long count = protectionTargetService.queryForCounts(protectionTarget);
        result.setList(list);
        result.setCount(count);
        return result;
    }
}
