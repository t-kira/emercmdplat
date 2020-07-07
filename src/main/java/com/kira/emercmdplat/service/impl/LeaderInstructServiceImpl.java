package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.LeaderInstructMapper;
import com.kira.emercmdplat.pojo.LeaderInstruct;
import com.kira.emercmdplat.pojo.LeaderInstructExtend;
import com.kira.emercmdplat.pojo.LeaderInstructResult;
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
    public LeaderInstructResult selectById(Integer id) {
        return lim.selectById(id);
    }

    @Override
    public List<LeaderInstructResult> queryForAll(LeaderInstructExtend leaderInstructExtend) {
        return lim.queryForAll(leaderInstructExtend);
    }

    @Override
    public List<LeaderInstructResult> queryForPage(LeaderInstructExtend leaderInstructExtend) {
        leaderInstructExtend.setPage((leaderInstructExtend.getPage() - 1) * leaderInstructExtend.getPageSize());
        return lim.queryForPage(leaderInstructExtend);
    }

    @Override
    public Long queryForCounts(LeaderInstructExtend leaderInstructExtend) {
        return lim.queryForCounts(leaderInstructExtend);
    }
}
