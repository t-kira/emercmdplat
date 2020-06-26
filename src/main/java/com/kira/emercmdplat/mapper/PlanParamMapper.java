package com.kira.emercmdplat.mapper;

import java.util.List;
import java.util.Map;

import com.kira.emercmdplat.mapper.base.BaseMapper;
import com.kira.emercmdplat.pojo.PlanParam;

/**
 * @Author: kira
 * @Date: 2020/2/4 23:07
 * @Description:危险源mapper
 */
public interface PlanParamMapper extends BaseMapper<PlanParam> {

	List<PlanParam> queryForIds(Map<String, Object> paramMap);
}
