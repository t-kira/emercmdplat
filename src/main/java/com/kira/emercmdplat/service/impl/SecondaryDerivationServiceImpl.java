package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.SecondaryDerivationMapper;
import com.kira.emercmdplat.pojo.EventRisk;
import com.kira.emercmdplat.pojo.EventRiskResult;
import com.kira.emercmdplat.pojo.RiskLevel;
import com.kira.emercmdplat.service.SecondaryDerivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/6/15 11:02
 * @Description:
 */
@Service
public class SecondaryDerivationServiceImpl implements SecondaryDerivationService {

    @Autowired
    private SecondaryDerivationMapper sdm;

    @Override
    public List<RiskLevel> selectByPtId(Long ptId) {
        return sdm.selectByPtId(ptId);
    }

    @Override
    public int insert(EventRisk eventRisk) {
        return sdm.insert(eventRisk);
    }

    @Override
    public List<EventRiskResult> selectByEventId(Long eventId) {
        return sdm.selectByEventId(eventId);
    }
}
