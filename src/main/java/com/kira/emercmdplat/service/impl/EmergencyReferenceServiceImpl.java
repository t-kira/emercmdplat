package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.EmergencyReferenceMapper;
import com.kira.emercmdplat.pojo.EmergencyReference;
import com.kira.emercmdplat.service.EmergencyReferenceService;
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
public class EmergencyReferenceServiceImpl implements EmergencyReferenceService {

    @Autowired
    private EmergencyReferenceMapper emergencyReferenceMapper;

    @Override
    public int insert(EmergencyReference pojo) {
        return emergencyReferenceMapper.insert(pojo);
    }

    @Override
    public boolean delete(EmergencyReference pojo) {
        return emergencyReferenceMapper.delete(pojo);
    }

    @Override
    public boolean update(EmergencyReference pojo) {
        return emergencyReferenceMapper.update(pojo);
    }

    @Override
    public EmergencyReference selectById(Integer id) {
        return emergencyReferenceMapper.selectById(id);
    }

    @Override
    public List<EmergencyReference> queryForAll(EmergencyReference pojo) {
        return emergencyReferenceMapper.queryForAll(pojo);
    }

    @Override
    public List<EmergencyReference> queryForPage(EmergencyReference pojo, Integer page, Integer pageSize) {
        Map<String, Object> paramMap = PojoUtil.pojoToMap(pojo, page, pageSize);
        return emergencyReferenceMapper.queryForPage(paramMap);
    }

    @Override
    public Long queryForCounts(EmergencyReference pojo) {
        return emergencyReferenceMapper.queryForCounts(pojo);
    }
}
