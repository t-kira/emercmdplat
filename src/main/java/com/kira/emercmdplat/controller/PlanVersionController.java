package com.kira.emercmdplat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kira.emercmdplat.pojo.PlanCatalog;
import com.kira.emercmdplat.pojo.PlanOrg;
import com.kira.emercmdplat.pojo.PlanResponse;
import com.kira.emercmdplat.pojo.PlanResponseFlow;
import com.kira.emercmdplat.pojo.PlanResponseFlowTask;
import com.kira.emercmdplat.pojo.PlanResponseGuard;
import com.kira.emercmdplat.pojo.PlanVersion;
import com.kira.emercmdplat.service.PlanVersionService;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import com.kira.emercmdplat.utils.Node;

@RestController
@RequestMapping("/planVersion")
public class PlanVersionController {
	
	@Autowired
	private PlanVersionService planVersionService;
	
	@RequestMapping("/listVersions")
	public AlvesJSONResult listVersions(PlanVersion planVersion,Integer page,Integer pageSize) {
		Map<String,Object> map = new HashMap<>();
		List<PlanVersion> list = planVersionService.listVersions(planVersion, page, pageSize);
		Long count = planVersionService.countVersions(planVersion);
		map.put("list", list);
    	map.put("count", count);
		return AlvesJSONResult.ok(map);
	}
	
	@RequestMapping("/insertVersion")
	public AlvesJSONResult insertVersion(PlanVersion planVersion) {
		planVersionService.insertVersion(planVersion);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/updateVersion")
	public AlvesJSONResult updateVersion(PlanVersion planVersion) {
		planVersionService.updateVersion(planVersion);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/deleteVersion")
	public AlvesJSONResult deleteVersion(Integer id) {
		planVersionService.deleteVersion(id);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/listOrgTree")
	public AlvesJSONResult listOrgTree(PlanOrg planOrg) {
		List<Node> list = planVersionService.listOrgTree(planOrg);
		return AlvesJSONResult.ok(list);
	}
	
	@RequestMapping("/insertOrg")
	public AlvesJSONResult insertOrg(PlanOrg planOrg) {
		planVersionService.insertOrg(planOrg);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/updateOrg")
	public AlvesJSONResult updateOrg(PlanOrg planOrg) {
		planVersionService.updateOrg(planOrg);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/deleteOrg")
	public AlvesJSONResult deleteOrg(Integer id) {
		planVersionService.deleteOrg(id);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/listResponses")
	public AlvesJSONResult listResponses(int pvId) {
		List<PlanResponse> list = planVersionService.listResponses(pvId);
		return AlvesJSONResult.ok(list);
	}
	
	@RequestMapping("/insertResponse")
	public AlvesJSONResult insertResponse(PlanResponse planResponse) {
		planVersionService.insertResponse(planResponse);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/deleteResponse")
	public AlvesJSONResult deleteResponse(Integer id) {
		planVersionService.deleteResponse(id);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/listResponseFlows")
	public AlvesJSONResult listResponseFlows(int prId) {
		List<PlanResponseFlow> list = planVersionService.listResponseFlows(prId);
		return AlvesJSONResult.ok(list);
	}
	
	@RequestMapping("/insertResponseFlow")
	public AlvesJSONResult insertResponseFlow(PlanResponseFlow planResponseFlow) {
		planVersionService.insertResponseFlow(planResponseFlow);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/deleteResponseFlow")
	public AlvesJSONResult deleteResponseFlow(Integer id) {
		planVersionService.deleteResponseFlow(id);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/listResponseFlowTasks")
	public AlvesJSONResult listResponseFlowTasks(int prfId) {
		List<PlanResponseFlowTask> list = planVersionService.listResponseFlowTasks(prfId);
		return AlvesJSONResult.ok(list);
	}
	
	@RequestMapping("/insertResponseFlowTask")
	public AlvesJSONResult insertResponseFlowTask(PlanResponseFlowTask planResponseFlowTask) {
		planVersionService.insertResponseFlowTask(planResponseFlowTask);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/deleteResponseFlowTask")
	public AlvesJSONResult deleteResponseFlowTask(Integer id) {
		planVersionService.deleteResponseFlowTask(id);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/listResponseGuardTree")
	public AlvesJSONResult listResponseGuardTree(PlanResponseGuard planResponseGuard) {
		List<Node> list = planVersionService.listResponseGuardTree(planResponseGuard);
		return AlvesJSONResult.ok(list);
	}
	
	@RequestMapping("/insertResponseGuard")
	public AlvesJSONResult insertResponseGuard(PlanResponseGuard planResponseGuard) {
		planVersionService.insertResponseGuard(planResponseGuard);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/updateResponseGuard")
	public AlvesJSONResult updateResponseGuard(PlanResponseGuard planResponseGuard) {
		planVersionService.updateResponseGuard(planResponseGuard);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/deleteResponseGuard")
	public AlvesJSONResult deleteResponseGuard(Integer id) {
		planVersionService.deleteResponseGuard(id);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/listCatalogTree")
	public AlvesJSONResult listCatalogTree(PlanCatalog planCatalog) {
		List<Node> list = planVersionService.listCatalogTree(planCatalog);
		return AlvesJSONResult.ok(list);
	}
	
	@RequestMapping("/insertCatalog")
	public AlvesJSONResult insertCatalog(PlanCatalog planCatalog) {
		planVersionService.insertCatalog(planCatalog);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/updateCatalog")
	public AlvesJSONResult updateCatalog(PlanCatalog planCatalog) {
		planVersionService.updateCatalog(planCatalog);
		return AlvesJSONResult.ok();
	}
	
	@RequestMapping("/deleteCatalog")
	public AlvesJSONResult deleteCatalog(Integer id) {
		planVersionService.deleteCatalog(id);
		return AlvesJSONResult.ok();
	}

}
