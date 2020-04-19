package com.kira.emercmdplat.mapper;

import java.util.List;
import java.util.Map;

import com.kira.emercmdplat.mapper.base.BaseMapper;
import com.kira.emercmdplat.pojo.Duty;

/**
 * @Author: kira
 * @Date: 2020/4/6 13:59
 * @Description:
 */
public interface DutyMapper extends BaseMapper<Duty> {

	List<Duty> queryForIds(Map<String, Object> map);
}
