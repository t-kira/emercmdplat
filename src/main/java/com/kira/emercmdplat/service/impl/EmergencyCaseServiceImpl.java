package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.EmergencyCaseMapper;
import com.kira.emercmdplat.pojo.EmergencyCase;
import com.kira.emercmdplat.service.EmergencyCaseService;
import com.kira.emercmdplat.utils.PojoUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/2/5 00:06
 * @Description:
 */
@Service
public class EmergencyCaseServiceImpl implements EmergencyCaseService {

    @Autowired
    private EmergencyCaseMapper emergencyCaseMapper;

    @Override
    public int insert(EmergencyCase pojo) {
        return emergencyCaseMapper.insert(pojo);
    }

    @Override
    public boolean delete(EmergencyCase pojo) {
        return emergencyCaseMapper.delete(pojo);
    }

    @Override
    public boolean update(EmergencyCase pojo) {
        return emergencyCaseMapper.update(pojo);
    }

    @Override
    public EmergencyCase selectById(Integer id) {
        return emergencyCaseMapper.selectById(id);
    }

    @Override
    public List<EmergencyCase> queryForAll(EmergencyCase pojo) {
        return emergencyCaseMapper.queryForAll(pojo);
    }

    @Override
    public List<EmergencyCase> queryForPage(EmergencyCase pojo, Integer page, Integer pageSize) {
        Map<String, Object> paramMap = PojoUtil.pojoToMap(pojo, page, pageSize);
        return emergencyCaseMapper.queryForPage(paramMap);
    }

    @Override
    public Long queryForCounts(EmergencyCase pojo) {
        return emergencyCaseMapper.queryForCounts(pojo);
    }
}
