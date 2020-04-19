package com.kira.emercmdplat.mapper;

import java.util.List;
import java.util.Map;

import com.kira.emercmdplat.mapper.base.BaseMapper;
import com.kira.emercmdplat.pojo.PlanGroup;

/**
 * @Author: kira
 * @Date: 2020/2/4 23:07
 * @Description:危险源mapper
 */
public interface PlanGroupMapper extends BaseMapper<PlanGroup> {

	List<PlanGroup> queryForIds(Map<String, Object> paramMap);

}
