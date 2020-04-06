package com.kira.emercmdplat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kira.emercmdplat.pojo.PlanGroup;
import com.kira.emercmdplat.pojo.PlanParam;
import com.kira.emercmdplat.pojo.PlanTag;
import com.kira.emercmdplat.pojo.PlanType;
import com.kira.emercmdplat.service.PlanTypeService;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import com.kira.emercmdplat.utils.Node;

@RestController
@RequestMapping("/planType")
public class PlanTypeController {
	
	@Autowired
	private PlanTypeService planTypeService;
	
	@RequestMapping("/listTypeTree")
	public AlvesJSONResult listTypeTree(String name) {
		List<Node> list = planTypeService.listTypeTree(name);
		return AlvesJSONResult.ok(list);
	}
	
	@RequestMapping("/insertType")
	public AlvesJSONResult insertType(PlanType planType) {
		planTypeService.insertType(planType);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/updateType")
	public AlvesJSONResult updateType(PlanType planType) {
		planTypeService.updateType(planType);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/deleteType")
	public AlvesJSONResult deleteType(Integer id) {
		planTypeService.deleteType(id);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/listTags")
	public AlvesJSONResult listTags(int ptId) {
		List<PlanTag> list = planTypeService.listTags(ptId);
		return AlvesJSONResult.ok(list);
	}
	
	@RequestMapping("/insertTag")
	public AlvesJSONResult insertTag(PlanTag planTag) {
		planTypeService.insertTag(planTag);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/deleteTag")
	public AlvesJSONResult deleteTag(Integer id) {
		planTypeService.deleteTag(id);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/listParams")
	public AlvesJSONResult listParams(int ptId,Integer page,Integer pageSize) {
		Map<String,Object> map = new HashMap<>();
		List<PlanParam> list = planTypeService.listParams(ptId, page, pageSize);
		Long count = planTypeService.countParams(ptId);
		map.put("list", list);
    	map.put("count", count);
		return AlvesJSONResult.ok(map);
	}
	
	@RequestMapping("/insertParam")
	public AlvesJSONResult insertParam(PlanParam planParam) {
		planTypeService.insertParam(planParam);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/updateParam")
	public AlvesJSONResult updateParam(PlanParam planParam) {
		planTypeService.updateParam(planParam);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/deleteParam")
	public AlvesJSONResult deleteParam(Integer id) {
		planTypeService.deleteParam(id);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/listGroups")
	public AlvesJSONResult listGroups(PlanGroup planGroup,Integer page,Integer pageSize) {
		Map<String,Object> map = new HashMap<>();
		List<PlanGroup> list = planTypeService.listGroups(planGroup, page, pageSize);
		Long count = planTypeService.countGroups(planGroup);
		map.put("list", list);
    	map.put("count", count);
		return AlvesJSONResult.ok(map);
	}
	
	@RequestMapping("/insertGroup")
	public AlvesJSONResult insertGroup(PlanGroup planGroup) {
		planTypeService.insertGroup(planGroup);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/updateGroup")
	public AlvesJSONResult updateGroup(PlanGroup planGroup) {
		planTypeService.updateGroup(planGroup);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/deleteGroup")
	public AlvesJSONResult deleteGroup(Integer id) {
		planTypeService.deleteGroup(id);
		return AlvesJSONResult.ok();
	}

}
