package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.DutyMapper;
import com.kira.emercmdplat.pojo.Duty;
import com.kira.emercmdplat.pojo.DutyExtent;
import com.kira.emercmdplat.service.DutyService;
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
    public int insert(DutyExtent pojo) {
        return dm.insert(pojo);
    }

    @Override
    public boolean delete(DutyExtent pojo) {
        return dm.delete(pojo);
    }

    @Override
    public boolean update(DutyExtent pojo) {
        return dm.update(pojo);
    }

    @Override
    public DutyExtent selectById(Integer id) {
        return dm.selectById(id);
    }

    @Override
    public List<DutyExtent> queryForAll(DutyExtent pojo) {
        return dm.queryForAll(pojo);
    }

    @Override
    public List<DutyExtent> queryForPage(DutyExtent pojo, Integer page, Integer pageSize) {
        Map<String, Object> paramMap = new HashMap<>();
        return dm.queryForPage(paramMap);
    }

    @Override
    public Long queryForCounts(DutyExtent pojo) {
        return dm.queryForCounts(pojo);
    }
}
