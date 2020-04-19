package com.kira.emercmdplat.mapper;

import java.util.List;
import java.util.Map;

import com.kira.emercmdplat.mapper.base.BaseMapper;
import com.kira.emercmdplat.pojo.PlanTag;

/**
 * @Author: kira
 * @Date: 2020/2/4 23:07
 * @Description:危险源mapper
 */
public interface PlanTagMapper extends BaseMapper<PlanTag> {

	List<PlanTag> queryForIds(Map<String, Object> paramMap);

}
