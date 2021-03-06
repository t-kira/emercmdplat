package com.kira.emercmdplat.service;

import java.util.List;

import com.kira.emercmdplat.pojo.PlanCatalog;
import com.kira.emercmdplat.pojo.PlanOrg;
import com.kira.emercmdplat.pojo.PlanResponse;
import com.kira.emercmdplat.pojo.PlanResponseFlow;
import com.kira.emercmdplat.pojo.PlanResponseFlowTask;
import com.kira.emercmdplat.pojo.PlanResponseGuard;
import com.kira.emercmdplat.pojo.PlanVersion;
import com.kira.emercmdplat.pojo.PlanVersionApproval;
import com.kira.emercmdplat.utils.Node;

public interface PlanVersionService {

	List<PlanVersion> listVersions(PlanVersion planVersion);

	List<PlanVersion> listAllVersions(PlanVersion planVersion);

	Long countVersions(PlanVersion planVersion);

	int insertVersion(PlanVersion planVersion);

	void updateVersion(PlanVersion planVersion);

	boolean deleteVersion(Integer id);

	List<Node> listOrgTree(PlanOrg planOrg);

	int insertOrg(PlanOrg planOrg);

	void updateOrg(PlanOrg planOrg);

	void deleteOrg(Integer id);
	
	boolean deleteOrgByPvId(Integer pvId);

	List<PlanResponse> listResponses(int pvId,int form);

	int insertResponse(PlanResponse planResponse);

	boolean deleteResponse(Integer id);

	List<PlanResponseFlow> listResponseFlows(int prId);

	void insertResponseFlow(PlanResponseFlow planResponseFlow);
	
	void updateResponseFlow(PlanResponseFlow planResponseFlow);

	void deleteResponseFlow(Integer id);

	List<PlanResponseFlowTask> listResponseFlowTasks(int prfId);

	void insertResponseFlowTask(PlanResponseFlowTask planResponseFlowTask);
	
	void updateResponseFlowTask(PlanResponseFlowTask planResponseFlowTask);

	void deleteResponseFlowTask(Integer id);

	List<Node> listResponseGuardTree(PlanResponseGuard planResponseGuard);

	int insertResponseGuard(PlanResponseGuard planResponseGuard);

	void updateResponseGuard(PlanResponseGuard planResponseGuard);

	void deleteResponseGuard(Integer id);

	List<Node> listCatalogTree(PlanCatalog planCatalog);

	int insertCatalog(PlanCatalog planCatalog);

	void updateCatalog(PlanCatalog planCatalog);

	void deleteCatalog(Integer id);

	PlanOrg getOrgById(Integer id);

	PlanResponseGuard getResponseGuardById(Integer id);

	PlanCatalog getCatalogById(Integer id);

	PlanVersion getPlanVerionById(Integer id);

	PlanResponse getResponseById(Integer id);

	void updateReponse(PlanResponse planResponse);
	
	void insertPlanVersionApproval(PlanVersionApproval planVersionApproval);
	
	void updatePlanVersionApproval(PlanVersionApproval planVersionApproval);
	
	List<PlanVersionApproval> listPlanVersionApprovals(int pvId);
	
	void copyOrg(int pvId);

}
