package com.kira.emercmdplat.service;

import java.util.List;

import com.kira.emercmdplat.pojo.Duty;
import com.kira.emercmdplat.service.base.BaseService;

/**
 * @Author: kira
 * @Date: 2020/4/6 14:15
 * @Description:
 */
public interface DutyService extends BaseService<Duty> {

	List<Duty> queryForIds(List ids);
}
