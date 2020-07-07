package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.LeaderInstruct;
import com.kira.emercmdplat.pojo.LeaderInstructExtend;
import com.kira.emercmdplat.pojo.LeaderInstructResult;
import com.kira.emercmdplat.service.base.BaseService;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/10 22:15
 * @Description:
 */
public interface LeaderInstructService {

    int insert(LeaderInstruct leaderInstruct);

    boolean delete(LeaderInstruct leaderInstruct);

    boolean update(LeaderInstruct leaderInstruct);

    LeaderInstructResult selectById(Integer id);

    List<LeaderInstructResult> queryForAll(LeaderInstructExtend leaderInstructExtend);

    List<LeaderInstructResult> queryForPage(LeaderInstructExtend leaderInstructExtend);

    Long queryForCounts(LeaderInstructExtend leaderInstructExtend);
}
