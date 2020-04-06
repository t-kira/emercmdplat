package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.EmergencyTeamMapper;
import com.kira.emercmdplat.pojo.EmergencyTeam;
import com.kira.emercmdplat.service.EmergencyTeamService;
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
public class EmergencyTeamServiceImpl implements EmergencyTeamService {

    @Autowired
    private EmergencyTeamMapper emergencyTeamMapper;

    @Override
    public int insert(EmergencyTeam pojo) {
        return emergencyTeamMapper.insert(pojo);
    }

    @Override
    public boolean delete(EmergencyTeam pojo) {
        return emergencyTeamMapper.delete(pojo);
    }

    @Override
    public boolean update(EmergencyTeam pojo) {
        return emergencyTeamMapper.update(pojo);
    }

    @Override
    public EmergencyTeam selectById(Integer id) {
        return emergencyTeamMapper.selectById(id);
    }

    @Override
    public List<EmergencyTeam> queryForAll(EmergencyTeam pojo) {
        return emergencyTeamMapper.queryForAll(pojo);
    }

    @Override
    public List<EmergencyTeam> queryForPage(EmergencyTeam pojo, Integer page, Integer pageSize) {
        Map<String, Object> paramMap = PojoUtil.pojoToMap(pojo, page, pageSize);
        return emergencyTeamMapper.queryForPage(paramMap);
    }

    @Override
    public Long queryForCounts(EmergencyTeam pojo) {
        return emergencyTeamMapper.queryForCounts(pojo);
    }
}
