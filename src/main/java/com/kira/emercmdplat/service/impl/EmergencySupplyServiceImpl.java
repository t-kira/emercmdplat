package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.EmergencySupplyMapper;
import com.kira.emercmdplat.pojo.EmergencySupply;
import com.kira.emercmdplat.service.EmergencySupplyService;
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
public class EmergencySupplyServiceImpl implements EmergencySupplyService {

    @Autowired
    private EmergencySupplyMapper emergencySupplyMapper;

    @Override
    public int insert(EmergencySupply pojo) {
        return emergencySupplyMapper.insert(pojo);
    }

    @Override
    public boolean delete(EmergencySupply pojo) {
        return emergencySupplyMapper.delete(pojo);
    }

    @Override
    public boolean update(EmergencySupply pojo) {
        return emergencySupplyMapper.update(pojo);
    }

    @Override
    public EmergencySupply selectById(Integer id) {
        return emergencySupplyMapper.selectById(id);
    }

    @Override
    public List<EmergencySupply> queryForAll(EmergencySupply pojo) {
        return emergencySupplyMapper.queryForAll(pojo);
    }

    @Override
    public List<EmergencySupply> queryForPage(EmergencySupply pojo, Integer page, Integer pageSize) {
        Map<String,Object> paramMap = PojoUtil.pojoToMap(pojo, page, pageSize);
        return emergencySupplyMapper.queryForPage(paramMap);
    }

    @Override
    public Long queryForCounts(EmergencySupply pojo) {
        return emergencySupplyMapper.queryForCounts(pojo);
    }
}
