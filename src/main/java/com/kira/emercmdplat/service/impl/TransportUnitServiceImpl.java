package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.TransportUnitMapper;
import com.kira.emercmdplat.pojo.TransportUnit;
import com.kira.emercmdplat.service.TransportUnitService;
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
public class TransportUnitServiceImpl implements TransportUnitService {

    @Autowired
    private TransportUnitMapper transportUnitMapper;

    @Override
    public int insert(TransportUnit pojo) {
        return transportUnitMapper.insert(pojo);
    }

    @Override
    public boolean delete(TransportUnit pojo) {
        return transportUnitMapper.delete(pojo);
    }

    @Override
    public boolean update(TransportUnit pojo) {
        return transportUnitMapper.update(pojo);
    }

    @Override
    public TransportUnit selectById(Integer id) {
        return transportUnitMapper.selectById(id);
    }

    @Override
    public List<TransportUnit> queryForAll(TransportUnit pojo) {
        return transportUnitMapper.queryForAll(pojo);
    }

    @Override
    public List<TransportUnit> queryForPage(TransportUnit pojo, Integer page, Integer pageSize) {
        Map<String, Object> paramMap = PojoUtil.pojoToMap(pojo, page, pageSize);
        return transportUnitMapper.queryForPage(paramMap);
    }

    @Override
    public Long queryForCounts(TransportUnit pojo) {
        return transportUnitMapper.queryForCounts(pojo);
    }
}
