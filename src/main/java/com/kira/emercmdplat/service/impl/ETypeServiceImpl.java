package com.kira.emercmdplat.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kira.emercmdplat.mapper.ETypeMapper;
import com.kira.emercmdplat.pojo.EType;
import com.kira.emercmdplat.service.ETypeService;
import com.kira.emercmdplat.utils.PojoUtil;

@Service
public class ETypeServiceImpl implements ETypeService {
	
	@Autowired
	private ETypeMapper etm;

	@Override
	public int insert(EType pojo) {
		return etm.insert(pojo);
	}

	@Override
	public boolean delete(EType pojo) {
		return etm.delete(pojo);
	}

	@Override
	public boolean update(EType pojo) {
		return etm.update(pojo);
	}

	@Override
	public EType selectById(Integer id) {
		return etm.selectById(id);
	}

	@Override
	public List<EType> queryForAll(EType pojo) {
		return etm.queryForAll(pojo);
	}

	@Override
	public List<EType> queryForPage(EType pojo, Integer page, Integer pageSize) {
		Map<String, Object> paramMap = PojoUtil.pojoToMap(pojo, page, pageSize);
        return etm.queryForPage(paramMap);
	}

	@Override
	public Long queryForCounts(EType pojo) {
		return etm.queryForCounts(pojo);
	}

}
