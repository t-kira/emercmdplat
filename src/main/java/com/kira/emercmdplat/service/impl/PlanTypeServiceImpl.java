package com.kira.emercmdplat.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kira.emercmdplat.mapper.PlanGroupMapper;
import com.kira.emercmdplat.mapper.PlanParamMapper;
import com.kira.emercmdplat.mapper.PlanTagMapper;
import com.kira.emercmdplat.mapper.PlanTypeMapper;
import com.kira.emercmdplat.pojo.PlanGroup;
import com.kira.emercmdplat.pojo.PlanParam;
import com.kira.emercmdplat.pojo.PlanTag;
import com.kira.emercmdplat.pojo.PlanType;
import com.kira.emercmdplat.service.PlanTypeService;
import com.kira.emercmdplat.utils.Node;
import com.kira.emercmdplat.utils.PojoUtil;

@Service
public class PlanTypeServiceImpl implements PlanTypeService {
	
	@Autowired
	private PlanTypeMapper planTypeMapper;
	
	@Autowired
	private PlanTagMapper planTagMapper;
	
	@Autowired
	private PlanParamMapper planParamMapper;
	
	@Autowired
	private PlanGroupMapper planGroupMapper;

	@Override
	public List<Node> listTypeTree(String name) {
		PlanType param = new PlanType();
		param.setName(name);
		List<PlanType> list = planTypeMapper.queryForAll(param);
		List<Node> newList = new ArrayList<>();
		for (PlanType planType : list) {
			newList.add(new Node(planType.getId(),planType.getName(),planType.getParentId(),planType.getOrder()));
		}
		return Node.buildTree(newList, 0);
	}

	@Override
	public void insertType(PlanType planType) {
		planTypeMapper.insert(planType);
	}

	@Override
	public void updateType(PlanType planType) {
		planTypeMapper.update(planType);
	}

	@Override
	public void deleteType(Integer id) {
		PlanType param = new PlanType();
		param.setId(id);
		planTypeMapper.delete(param);
	}

	@Override
	public List<PlanTag> listTags(int ptId) {
		PlanTag param = new PlanTag();
		param.setPtId(ptId);
		return planTagMapper.queryForAll(param);
	}

	@Override
	public void insertTag(PlanTag planTag) {
		planTagMapper.insert(planTag);
	}

	@Override
	public void deleteTag(Integer id) {
		PlanTag param = new PlanTag();
		param.setId(id);
		planTagMapper.delete(param);
	}

	@Override
	public List<PlanParam> listParams(int ptId, Integer page, Integer pageSize) {
		PlanParam param = new PlanParam();
		param.setPtId(ptId);
		Map<String,Object> paramMap = PojoUtil.pojoToMap(param, page, pageSize);
		return planParamMapper.queryForPage(paramMap);
	}
	
	@Override
	public Long countParams(int ptId) {
		PlanParam param = new PlanParam();
		param.setPtId(ptId);
		return planParamMapper.queryForCounts(param);
	}

	@Override
	public void insertParam(PlanParam planParam) {
		planParamMapper.insert(planParam);
	}

	@Override
	public void updateParam(PlanParam planParam) {
		planParamMapper.update(planParam);
	}

	@Override
	public void deleteParam(Integer id) {
		PlanParam param = new PlanParam();
		param.setId(id);
		planParamMapper.delete(param);
	}

	@Override
	public List<PlanGroup> listGroups(PlanGroup planGroup, Integer page, Integer pageSize) {
		Map<String,Object> paramMap = PojoUtil.pojoToMap(planGroup, page, pageSize);
		return planGroupMapper.queryForPage(paramMap);
	}

	@Override
	public Long countGroups(PlanGroup planGroup) {
		return planGroupMapper.queryForCounts(planGroup);
	}

	@Override
	public void insertGroup(PlanGroup planGroup) {
		planGroupMapper.insert(planGroup);
	}

	@Override
	public void updateGroup(PlanGroup planGroup) {
		planGroupMapper.update(planGroup);
	}

	@Override
	public void deleteGroup(Integer id) {
		PlanGroup param = new PlanGroup();
		param.setId(id);
		planGroupMapper.delete(param);
	}
	
}