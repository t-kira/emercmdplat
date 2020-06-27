package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.Duty;
import com.kira.emercmdplat.pojo.DutyExtent;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/6 14:15
 * @Description:
 */
public interface DutyService {

	int insert(Duty duty);

	boolean delete(Duty duty);

	boolean update(Duty duty);

	DutyExtent selectById(Integer id);

	List<DutyExtent> queryForAll(Duty duty);

	List<DutyExtent> queryForPage(Duty duty, Integer page, Integer pageSize);

	Long queryForCounts(Duty duty);

	List<DutyExtent> queryForIds(List ids);
}
