package com.kira.emercmdplat.service;

import java.util.List;

import com.kira.emercmdplat.pojo.PlanGroup;
import com.kira.emercmdplat.pojo.PlanParam;
import com.kira.emercmdplat.pojo.PlanTag;
import com.kira.emercmdplat.pojo.PlanType;
import com.kira.emercmdplat.utils.Node;

public interface PlanTypeService {

    List<Node> listTypeTree(Node node);

    int insertType(PlanType planType);

    void updateType(PlanType planType);

    boolean deleteType(Integer id);

    List<PlanTag> listTags(int ptId,Boolean includeCommon);

    void insertTag(PlanTag planTag);

    void deleteTag(Integer id);

    List<PlanParam> listParams(int ptId, Boolean includeCommon, Integer page, Integer pageSize);

    List<PlanParam> listParamsByPtId(int ptId, Boolean includeCommon);

    PlanParam selectByParamId(Long id);

    Long countParams(int ptId, Boolean includeCommon);

    void insertParam(PlanParam planParam);

    void updateParam(PlanParam planParam);

    void deleteParam(Integer id);

    List<PlanGroup> listGroups(PlanGroup planGroup);

    Long countGroups(PlanGroup planGroup);

    void insertGroup(PlanGroup planGroup);

    void updateGroup(PlanGroup planGroup);

    void deleteGroup(Integer id);

	List<PlanParam> queryForParamIds(List ids);

	List<PlanTag> queryForTagIds(List ids);

	List<PlanGroup> queryForGroupIds(List ids);

	PlanGroup getGroupById(Integer id);

	PlanType getPlanTypeById(Integer id);

	List<PlanParam> getParamByJson(String params);
	
	Long countsForParentId(Integer id);

}
