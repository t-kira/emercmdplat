package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.ShelterMapper;
import com.kira.emercmdplat.pojo.Shelter;
import com.kira.emercmdplat.service.ShelterService;
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
public class ShelterServiceImpl implements ShelterService {

    @Autowired
    private ShelterMapper shelterMapper;

    @Override
    public int insert(Shelter pojo) {
        return shelterMapper.insert(pojo);
    }

    @Override
    public boolean delete(Shelter pojo) {
        return shelterMapper.delete(pojo);
    }

    @Override
    public boolean update(Shelter pojo) {
        return shelterMapper.update(pojo);
    }

    @Override
    public Shelter selectById(Integer id) {
        return shelterMapper.selectById(id);
    }

    @Override
    public List<Shelter> queryForAll(Shelter pojo) {
        return shelterMapper.queryForAll(pojo);
    }

    @Override
    public List<Shelter> queryForPage(Shelter pojo, Integer page, Integer pageSize) {
        Map<String, Object> paramMap = PojoUtil.pojoToMap(pojo, page, pageSize);
        return shelterMapper.queryForPage(paramMap);
    }

    @Override
    public Long queryForCounts(Shelter pojo) {
        return shelterMapper.queryForCounts(pojo);
    }
}
