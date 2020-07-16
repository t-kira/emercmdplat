package com.kira.emercmdplat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.kira.emercmdplat.pojo.Monitor;
import com.kira.emercmdplat.pojo.MonitorResult;
import com.kira.emercmdplat.service.MonitorService;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;

@Api2Doc(id = "Monitor", name = "监控接口", order = 9)
@RestController
@RequestMapping("/monitor")
public class MonitorController {
	
	@Autowired
	private MonitorService monitorService;
	
	@Api2Doc(order = 1)
    @ApiComment(value="添加监控")
    @RequestMapping(name="添加监控",value="/add",method=RequestMethod.POST)
    public String insert(@ApiComment(value="添加监控",sample="根据id查询监控接口可查看字段信息") @RequestBody Monitor monitor) {
		monitorService.insert(monitor);
        return "success";
    }
	
	@Api2Doc(order = 2)
    @ApiComment(value="修改监控")
    @RequestMapping(name="修改监控",value="/update",method=RequestMethod.POST)
    public String update(@ApiComment(value="修改监控",sample="根据id查询监控接口可查看字段信息") @RequestBody Monitor monitor) {
		monitorService.update(monitor);
        return "success";
    }
	
	@Api2Doc(order = 3)
    @ApiComment(value="删除监控")
    @RequestMapping(name="删除监控",value="/delete",method=RequestMethod.GET)
    public String delete(@ApiComment(value="监控id",sample="1") String ids) {
    	if (StringUtils.isEmpty(ids)) {
    		return "fail";
    	}
    	String[] idList = ids.split(",");
    	for (String id : idList) {
    		Monitor monitor = monitorService.selectById(Integer.valueOf(id));
    		monitorService.delete(monitor);
    	}
        return "success";
    }
	
	@Api2Doc(order = 4)
    @ApiComment(value="根据id查询监控")
    @RequestMapping(name="根据id查询监控",value="/selectById",method=RequestMethod.GET)
    public Monitor selectById(@ApiComment(value="监控id",sample="1") Integer id) {
		Monitor monitor = monitorService.selectById(id);
        return monitor;
    }
	
	@Api2Doc(order = 5)
    @ApiComment(value="列出监控")
    @RequestMapping(name="列出监控",value="/list",method=RequestMethod.POST)
    public AlvesJSONResult list(@ApiComment(value="监控查询参数",sample="根据id查询监控接口可查看字段信息") @RequestBody Monitor monitor) {
    	MonitorResult result = new MonitorResult();
        List<Monitor> list = monitorService.queryForPage(monitor, monitor.getPage(), monitor.getPageSize());
        Long count = monitorService.queryForCounts(monitor);
        result.setList(list);
        result.setCount(count);
        return AlvesJSONResult.ok(result);
    }
	
}
