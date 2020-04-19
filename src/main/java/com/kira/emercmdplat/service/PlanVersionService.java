package com.kira.emercmdplat.service;

import java.util.List;

import com.kira.emercmdplat.pojo.PlanCatalog;
import com.kira.emercmdplat.pojo.PlanOrg;
import com.kira.emercmdplat.pojo.PlanResponse;
import com.kira.emercmdplat.pojo.PlanResponseFlow;
import com.kira.emercmdplat.pojo.PlanResponseFlowTask;
import com.kira.emercmdplat.pojo.PlanResponseGuard;
import com.kira.emercmdplat.pojo.PlanVersion;
import com.kira.emercmdplat.utils.Node;

public interface PlanVersionService {

	List<PlanVersion> listVersions(PlanVersion planVersion, Integer page, Integer pageSize);

	List<PlanVersion> listVersions(PlanVersion planVersion);

	Long countVersions(PlanVersion planVersion);

	void insertVersion(PlanVersion planVersion);

	void updateVersion(PlanVersion planVersion);

	void deleteVersion(Integer id);

	List<Node> listOrgTree(PlanOrg planOrg);

	void insertOrg(PlanOrg planOrg);

	void updateOrg(PlanOrg planOrg);

	void deleteOrg(Integer id);

	List<PlanResponse> listResponses(int pvId);

	void insertResponse(PlanResponse planResponse);

	void deleteResponse(Integer id);

	List<PlanResponseFlow> listResponseFlows(int prId);

	void insertResponseFlow(PlanResponseFlow planResponseFlow);

	void deleteResponseFlow(Integer id);

	List<PlanResponseFlowTask> listResponseFlowTasks(int prfId);

	void insertResponseFlowTask(PlanResponseFlowTask planResponseFlowTask);

	void deleteResponseFlowTask(Integer id);

	List<Node> listResponseGuardTree(PlanResponseGuard planResponseGuard);

	void insertResponseGuard(PlanResponseGuard planResponseGuard);

	void updateResponseGuard(PlanResponseGuard planResponseGuard);

	void deleteResponseGuard(Integer id);

	List<Node> listCatalogTree(PlanCatalog planCatalog);

	void insertCatalog(PlanCatalog planCatalog);

	void updateCatalog(PlanCatalog planCatalog);

	void deleteCatalog(Integer id);

}
