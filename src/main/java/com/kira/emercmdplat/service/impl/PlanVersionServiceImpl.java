package com.kira.emercmdplat.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kira.emercmdplat.mapper.PlanCatalogMapper;
import com.kira.emercmdplat.mapper.PlanOrgMapper;
import com.kira.emercmdplat.mapper.PlanResponseFlowMapper;
import com.kira.emercmdplat.mapper.PlanResponseFlowTaskMapper;
import com.kira.emercmdplat.mapper.PlanResponseGuardMapper;
import com.kira.emercmdplat.mapper.PlanResponseMapper;
import com.kira.emercmdplat.mapper.PlanVersionMapper;
import com.kira.emercmdplat.pojo.DataType;
import com.kira.emercmdplat.pojo.PlanCatalog;
import com.kira.emercmdplat.pojo.PlanOrg;
import com.kira.emercmdplat.pojo.PlanResponse;
import com.kira.emercmdplat.pojo.PlanResponseFlow;
import com.kira.emercmdplat.pojo.PlanResponseFlowTask;
import com.kira.emercmdplat.pojo.PlanResponseGuard;
import com.kira.emercmdplat.pojo.PlanVersion;
import com.kira.emercmdplat.service.DataTypeService;
import com.kira.emercmdplat.service.PlanVersionService;
import com.kira.emercmdplat.utils.Node;
import com.kira.emercmdplat.utils.PojoUtil;

@Service
public class PlanVersionServiceImpl implements PlanVersionService {

	@Autowired
	private PlanVersionMapper planVersionMapper;

	@Autowired
	private PlanOrgMapper planOrgMapper;

	@Autowired
	private PlanResponseMapper planResponseMapper;

	@Autowired
	private PlanResponseFlowMapper planResponseFlowMapper;

	@Autowired
	private PlanResponseFlowTaskMapper planResponseFlowTaskMapper;

	@Autowired
	private PlanResponseGuardMapper planResponseGuardMapper;

	@Autowired
	private PlanCatalogMapper planCatalogMapper;

	@Autowired
	private DataTypeService dataTypeService;

	@Override
	public List<PlanVersion> listVersions(PlanVersion planVersion, Integer page, Integer pageSize) {
		Map<String,Object> paramMap = PojoUtil.pojoToMap(planVersion, page, pageSize);
		return planVersionMapper.queryForPage(paramMap);
	}

	@Override
	public List<PlanVersion> listAllVersions(PlanVersion planVersion) {
		return planVersionMapper.queryForAll(planVersion);
	}

	@Override
	public Long countVersions(PlanVersion planVersion) {
		return planVersionMapper.queryForCounts(planVersion);
	}

	@Override
	public int insertVersion(PlanVersion planVersion) {
		planVersionMapper.insert(planVersion);
		return planVersion.getId();
	}

	@Override
	public void updateVersion(PlanVersion planVersion) {
		planVersionMapper.update(planVersion);
	}

	@Override
	public void deleteVersion(Integer id) {
		PlanVersion param = new PlanVersion();
		param.setId(id);
		planVersionMapper.delete(param);
	}

	@Override
	public List<Node> listOrgTree(PlanOrg planOrg) {
		List<PlanOrg> list = planOrgMapper.queryForAll(planOrg);
		List<Node> newList = new ArrayList<>();
		for (PlanOrg item : list) {
			newList.add(new Node(item.getId(),item.getName(),item.getParentId(),1));
		}
		return Node.buildTree(newList, 0);
	}

	@Override
	public void insertOrg(PlanOrg planOrg) {
		planOrgMapper.insert(planOrg);
	}

	@Override
	public void updateOrg(PlanOrg planOrg) {
		planOrgMapper.update(planOrg);
	}

	@Override
	public void deleteOrg(Integer id) {
		PlanOrg param = new PlanOrg();
		param.setId(id);
		planOrgMapper.delete(param);
	}

	@Override
	public List<PlanResponse> listResponses(int pvId) {
		PlanResponse param = new PlanResponse();
		param.setPvId(pvId);
		return planResponseMapper.queryForAll(param);
	}

	@Override
	public void insertResponse(PlanResponse planResponse) {
		planResponseMapper.insert(planResponse);
	}

	@Override
	public void deleteResponse(Integer id) {
		PlanResponse param = new PlanResponse();
		param.setId(id);
		planResponseMapper.delete(param);
	}

	@Override
	public List<PlanResponseFlow> listResponseFlows(int prId) {
		PlanResponseFlow param = new PlanResponseFlow();
		param.setPrId(prId);
		return planResponseFlowMapper.queryForAll(param);
	}

	@Override
	public void insertResponseFlow(PlanResponseFlow planResponseFlow) {
		planResponseFlowMapper.insert(planResponseFlow);
	}

	@Override
	public void deleteResponseFlow(Integer id) {
		PlanResponseFlow param = new PlanResponseFlow();
		param.setId(id);
		planResponseFlowMapper.delete(param);
	}

	@Override
	public List<PlanResponseFlowTask> listResponseFlowTasks(int prfId) {
		PlanResponseFlowTask param = new PlanResponseFlowTask();
		param.setPrfId(prfId);
		return planResponseFlowTaskMapper.queryForAll(param);
	}

	@Override
	public void insertResponseFlowTask(PlanResponseFlowTask planResponseFlowTask) {
		planResponseFlowTaskMapper.insert(planResponseFlowTask);
	}

	@Override
	public void deleteResponseFlowTask(Integer id) {
		PlanResponseFlowTask param = new PlanResponseFlowTask();
		param.setId(id);
		planResponseFlowTaskMapper.delete(param);
	}

	@Override
	public List<Node> listResponseGuardTree(PlanResponseGuard planResponseGuard) {
		List<PlanResponseGuard> list = planResponseGuardMapper.queryForAll(planResponseGuard);
		List<Node> newList = new ArrayList<>();
		for (PlanResponseGuard item : list) {
			if (item.getType() == 2) {
				if (!StringUtils.isEmpty(item.getRes())) {
					List<DataType> resList = dataTypeService.getResourcesByJson(item.getRes());
					if (resList.size() > 0) {
						DataType d = resList.get(0);
						item.setName(d.getTypeName() + "-" + d.getName() + "等");
					}
				}
			}
			newList.add(new Node(item.getId(),item.getName(),item.getParentId(),1));
		}
		return Node.buildTree(newList, 0);
	}

	@Override
	public void insertResponseGuard(PlanResponseGuard planResponseGuard) {
		planResponseGuardMapper.insert(planResponseGuard);
	}

	@Override
	public void updateResponseGuard(PlanResponseGuard planResponseGuard) {
		planResponseGuardMapper.update(planResponseGuard);
	}

	@Override
	public void deleteResponseGuard(Integer id) {
		PlanResponseGuard param = new PlanResponseGuard();
		param.setId(id);
		planResponseGuardMapper.delete(param);
	}

	@Override
	public List<Node> listCatalogTree(PlanCatalog planCatalog) {
		List<PlanCatalog> list = planCatalogMapper.queryForAll(planCatalog);
		List<Node> newList = new ArrayList<>();
		for (PlanCatalog item : list) {
			newList.add(new Node(item.getId(),item.getNode(),item.getParentId(),1));
		}
		return Node.buildTree(newList, 0);
	}

	@Override
	public void insertCatalog(PlanCatalog planCatalog) {
		planCatalogMapper.insert(planCatalog);
	}

	@Override
	public void updateCatalog(PlanCatalog planCatalog) {
		planCatalogMapper.update(planCatalog);
	}

	@Override
	public void deleteCatalog(Integer id) {
		PlanCatalog param = new PlanCatalog();
		param.setId(id);
		planCatalogMapper.delete(param);
	}

	@Override
	public PlanOrg getOrgById(Integer id) {
		return planOrgMapper.selectById(id);
	}

	@Override
	public PlanResponseGuard getResponseGuardById(Integer id) {
		return planResponseGuardMapper.selectById(id);
	}

	@Override
	public PlanCatalog getCatalogById(Integer id) {
		return planCatalogMapper.selectById(id);
	}

	@Override
	public PlanVersion getPlanVerionById(Integer id) {
		return planVersionMapper.selectById(id);
	}

}
