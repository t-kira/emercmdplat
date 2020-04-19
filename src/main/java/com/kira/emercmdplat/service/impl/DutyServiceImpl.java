package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.DutyMapper;
import com.kira.emercmdplat.pojo.Duty;
import com.kira.emercmdplat.pojo.DutyExtent;
import com.kira.emercmdplat.service.DutyService;
import com.kira.emercmdplat.utils.PojoUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: kira
 * @Date: 2020/4/6 14:16
 * @Description:
 */
@Service
public class DutyServiceImpl implements DutyService {

    @Autowired
    private DutyMapper dm;

    @Override
    public int insert(Duty pojo) {
        return dm.insert(pojo);
    }

    @Override
    public boolean delete(Duty pojo) {
        return dm.delete(pojo);
    }

    @Override
    public boolean update(Duty pojo) {
        return dm.update(pojo);
    }

    @Override
    public DutyExtent selectById(Integer id) {
        return dm.selectById(id);
    }

    @Override
    public List<DutyExtent> queryForAll(Duty pojo) {
        return dm.queryForAll(pojo);
    }

    @Override
    public List<DutyExtent> queryForPage(Duty pojo, Integer page, Integer pageSize) {
        Map<String, Object> paramMap = PojoUtil.pojoToMap(pojo, page, pageSize);
        return dm.queryForPage(paramMap);
    }

    @Override
    public Long queryForCounts(Duty pojo) {
        return dm.queryForCounts(pojo);
    }

	@Override
	public List<DutyExtent> queryForIds(List ids) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ids", ids);
		return dm.queryForIds(paramMap);
	}

}
