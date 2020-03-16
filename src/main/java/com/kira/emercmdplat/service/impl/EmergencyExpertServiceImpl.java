package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.EmergencyExpertMapper;
import com.kira.emercmdplat.pojo.EmergencyExpert;
import com.kira.emercmdplat.service.EmergencyExpertService;
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
public class EmergencyExpertServiceImpl implements EmergencyExpertService {

    @Autowired
    private EmergencyExpertMapper emergencyExpertMapper;

    @Override
    public int insert(EmergencyExpert pojo) {
        return emergencyExpertMapper.insert(pojo);
    }

    @Override
    public boolean delete(EmergencyExpert pojo) {
        return emergencyExpertMapper.delete(pojo);
    }

    @Override
    public boolean update(EmergencyExpert pojo) {
        return emergencyExpertMapper.update(pojo);
    }

    @Override
    public EmergencyExpert selectById(Integer id) {
        return emergencyExpertMapper.selectById(id);
    }

    @Override
    public List<EmergencyExpert> queryForAll(EmergencyExpert pojo) {
        return emergencyExpertMapper.queryForAll(pojo);
    }

    @Override
    public List<EmergencyExpert> queryForPage(EmergencyExpert pojo, Integer page, Integer pageSize) {
        Map<String,Object> paramMap = PojoUtil.pojoToMap(pojo, page, pageSize);
        return emergencyExpertMapper.queryForPage(paramMap);
    }

    @Override
    public Long queryForCounts(EmergencyExpert pojo) {
        return emergencyExpertMapper.queryForCounts(pojo);
    }
}
