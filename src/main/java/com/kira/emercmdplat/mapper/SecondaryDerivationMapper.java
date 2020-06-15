package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.pojo.*;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/6/15 10:53
 * @Description:
 */
public interface SecondaryDerivationMapper {

    int insert(EventRisk eventRisk);

    List<RiskLevel> selectByPtId(Long ptId);

    List<EventRiskResult> selectByEventId(Long eventId);
}
