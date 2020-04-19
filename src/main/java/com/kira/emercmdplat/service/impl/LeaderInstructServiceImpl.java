package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.LeaderInstructMapper;
import com.kira.emercmdplat.pojo.LeaderInstruct;
import com.kira.emercmdplat.service.LeaderInstructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/4/10 22:15
 * @Description:
 */
@Service
public class LeaderInstructServiceImpl implements LeaderInstructService {
    @Autowired
    private LeaderInstructMapper lim;

    @Override
    public int insert(LeaderInstruct pojo) {
        return lim.insert(pojo);
    }

    @Override
    public boolean delete(LeaderInstruct pojo) {
        return lim.delete(pojo);
    }

    @Override
    public boolean update(LeaderInstruct pojo) {
        return lim.update(pojo);
    }

    @Override
    public LeaderInstruct selectById(Integer id) {
        return lim.selectById(id);
    }

    @Override
    public List queryForAll(LeaderInstruct pojo) {
        return lim.queryForAll(pojo);
    }

    @Override
    public List queryForPage(LeaderInstruct pojo, Integer page, Integer pageSize) {
        Map<String, Object> paramMap = new HashMap<>();
        return lim.queryForPage(paramMap);
    }

    @Override
    public Long queryForCounts(LeaderInstruct pojo) {
        return lim.queryForCounts(pojo);
    }
}
