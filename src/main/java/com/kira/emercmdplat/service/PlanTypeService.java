package com.kira.emercmdplat.service;

import java.util.List;

import com.kira.emercmdplat.pojo.PlanGroup;
import com.kira.emercmdplat.pojo.PlanParam;
import com.kira.emercmdplat.pojo.PlanTag;
import com.kira.emercmdplat.pojo.PlanType;
import com.kira.emercmdplat.utils.Node;

public interface PlanTypeService {

    List<Node> listTypeTree(String name);

    void insertType(PlanType planType);

    void updateType(PlanType planType);

    void deleteType(Integer id);

    List<PlanTag> listTags(int ptId);

    void insertTag(PlanTag planTag);

    void deleteTag(Integer id);

    List<PlanParam> listParams(int ptId, Integer page, Integer pageSize);

    List<PlanParam> listParamsByPtId(int ptId);

    Long countParams(int ptId);

    void insertParam(PlanParam planParam);

    void updateParam(PlanParam planParam);

    void deleteParam(Integer id);

    List<PlanGroup> listGroups(PlanGroup planGroup, Integer page, Integer pageSize);

    Long countGroups(PlanGroup planGroup);

    void insertGroup(PlanGroup planGroup);

    void updateGroup(PlanGroup planGroup);

    void deleteGroup(Integer id);

}
