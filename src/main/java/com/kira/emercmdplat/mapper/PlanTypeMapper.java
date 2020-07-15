package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.mapper.base.BaseMapper;
import com.kira.emercmdplat.pojo.PlanType;

/**
 * @Author: kira
 * @Date: 2020/2/4 23:07
 * @Description:危险源mapper
 */
public interface PlanTypeMapper extends BaseMapper<PlanType> {
	
	Long countsForParentId(Integer id);

}
