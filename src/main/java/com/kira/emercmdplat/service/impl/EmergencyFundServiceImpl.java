package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.EmergencyFundMapper;
import com.kira.emercmdplat.pojo.EmergencyFund;
import com.kira.emercmdplat.service.EmergencyFundService;
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
public class EmergencyFundServiceImpl implements EmergencyFundService {

    @Autowired
    private EmergencyFundMapper emergencyFundMapper;

    @Override
    public int insert(EmergencyFund pojo) {
        return emergencyFundMapper.insert(pojo);
    }

    @Override
    public boolean delete(EmergencyFund pojo) {
        return emergencyFundMapper.delete(pojo);
    }

    @Override
    public boolean update(EmergencyFund pojo) {
        return emergencyFundMapper.update(pojo);
    }

    @Override
    public EmergencyFund selectById(Integer id) {
        return emergencyFundMapper.selectById(id);
    }

    @Override
    public List<EmergencyFund> queryForAll(EmergencyFund pojo) {
        return emergencyFundMapper.queryForAll(pojo);
    }

    @Override
    public List<EmergencyFund> queryForPage(EmergencyFund pojo, Integer page, Integer pageSize) {
        Map<String, Object> paramMap = PojoUtil.pojoToMap(pojo, page, pageSize);
        return emergencyFundMapper.queryForPage(paramMap);
    }

    @Override
    public Long queryForCounts(EmergencyFund pojo) {
        return emergencyFundMapper.queryForCounts(pojo);
    }
}
