package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.mapper.base.BaseMapper;
import com.kira.emercmdplat.pojo.PlanOrg;

/**
 * @Author: kira
 * @Date: 2020/2/4 23:07
 * @Description:危险源mapper
 */
public interface PlanOrgMapper extends BaseMapper<PlanOrg> {
	
	void copyOrg(int pvId);
	
	void deleteParent(PlanOrg org);
	
}
