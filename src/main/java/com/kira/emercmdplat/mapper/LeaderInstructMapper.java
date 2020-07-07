package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.pojo.LeaderInstruct;
import com.kira.emercmdplat.pojo.LeaderInstructExtend;
import com.kira.emercmdplat.pojo.LeaderInstructResult;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/10 22:03
 * @Description:领导批示mapper
 */
public interface LeaderInstructMapper{

    int insert(LeaderInstruct leaderInstruct);

    boolean delete(LeaderInstruct leaderInstruct);

    boolean update(LeaderInstruct leaderInstruct);

    LeaderInstructResult selectById(Integer id);

    List<LeaderInstructResult> queryForAll(LeaderInstructExtend leaderInstructExtend);

    List<LeaderInstructResult> queryForPage(LeaderInstructExtend leaderInstructExtend);

    Long queryForCounts(LeaderInstructExtend leaderInstructExtend);
}
