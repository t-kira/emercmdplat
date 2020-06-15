package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.EventRisk;
import com.kira.emercmdplat.pojo.EventRiskResult;
import com.kira.emercmdplat.pojo.RiskLevel;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/6/15 11:01
 * @Description:
 */
public interface SecondaryDerivationService {

    List<RiskLevel> selectByPtId(Long ptId);

    int insert(EventRisk eventRisk);

    List<EventRiskResult> selectByEventId(Long eventId);
}
