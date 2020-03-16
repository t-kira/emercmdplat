package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.HazardSouceMapper;
import com.kira.emercmdplat.pojo.HazardSouce;
import com.kira.emercmdplat.service.HazardSourceService;
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
public class HazardSourceServiceImpl implements HazardSourceService {

    @Autowired
    private HazardSouceMapper hazardSouceMapper;

    @Override
    public int insert(HazardSouce pojo) {
        return hazardSouceMapper.insert(pojo);
    }

    @Override
    public boolean delete(HazardSouce pojo) {
        return hazardSouceMapper.delete(pojo);
    }

    @Override
    public boolean update(HazardSouce pojo) {
        return hazardSouceMapper.update(pojo);
    }

    @Override
    public HazardSouce selectById(Integer id) {
        return hazardSouceMapper.selectById(id);
    }

    @Override
    public List<HazardSouce> queryForAll(HazardSouce pojo) {
        return hazardSouceMapper.queryForAll(pojo);
    }

    @Override
    public List<HazardSouce> queryForPage(HazardSouce pojo, Integer page, Integer pageSize) {
        Map<String,Object> paramMap = PojoUtil.pojoToMap(pojo, page, pageSize);
        return hazardSouceMapper.queryForPage(paramMap);
    }

    @Override
    public Long queryForCounts(HazardSouce pojo) {
        return hazardSouceMapper.queryForCounts(pojo);
    }
}
