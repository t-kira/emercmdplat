package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.pojo.Duty;
import com.kira.emercmdplat.pojo.DutyExtent;

import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/4/6 13:59
 * @Description:
 */
public interface DutyMapper {

	/**
	 * 增/插入数据
	 * @param duty
	 * @return
	 */
	int insert(Duty duty);

	/**
	 * 删（其实就是修改了状态，使其不显示，在客户端看来是删除了）
	 *
	 * @param duty
	 * @return
	 */
	boolean delete(Duty duty);

	/**
	 * 改，
	 *
	 * @param duty
	 * @return
	 */
	boolean update(Duty duty);

	/**
	 * 根据id查询单个
	 *
	 * @param id
	 * @return
	 */
	DutyExtent selectById(Integer id);

	/**
	 * 按条件查询
	 *
	 * @param duty
	 * @return
	 */
	List<DutyExtent> queryForAll(Duty duty);

	List<DutyExtent> queryForPage(Map<String, Object> map);

	Long queryForCounts(Duty duty);

	List<DutyExtent> queryForIds(Map<String, Object> map);
}
