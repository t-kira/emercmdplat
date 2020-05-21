package com.kira.emercmdplat.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kira.emercmdplat.mapper.MonitorMapper;
import com.kira.emercmdplat.pojo.Monitor;
import com.kira.emercmdplat.service.MonitorService;
import com.kira.emercmdplat.utils.PojoUtil;

@Service
public class MonitorServiceImpl implements MonitorService {
	
	@Autowired
	private MonitorMapper monitorMapper;

	@Override
	public int insert(Monitor pojo) {
		return monitorMapper.insert(pojo);
	}

	@Override
	public boolean delete(Monitor pojo) {
		return monitorMapper.delete(pojo);
	}

	@Override
	public boolean update(Monitor pojo) {
		return monitorMapper.update(pojo);
	}

	@Override
	public Monitor selectById(Integer id) {
		return monitorMapper.selectById(id);
	}

	@Override
	public List<Monitor> queryForAll(Monitor pojo) {
		return monitorMapper.queryForAll(pojo);
	}

	@Override
	public List<Monitor> queryForPage(Monitor pojo, Integer page, Integer pageSize) {
		Map<String, Object> paramMap = PojoUtil.pojoToMap(pojo, page, pageSize);
		return monitorMapper.queryForPage(paramMap);
	}

	@Override
	public Long queryForCounts(Monitor pojo) {
		return monitorMapper.queryForCounts(pojo);
	}

}
