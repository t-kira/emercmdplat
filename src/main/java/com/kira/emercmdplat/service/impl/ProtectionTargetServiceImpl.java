package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.ProtectionTargetMapper;
import com.kira.emercmdplat.pojo.ProtectionTarget;
import com.kira.emercmdplat.service.ProtectionTargetService;
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
public class ProtectionTargetServiceImpl implements ProtectionTargetService {

    @Autowired
    private ProtectionTargetMapper protectionTargetMapper;

    @Override
    public int insert(ProtectionTarget pojo) {
        return protectionTargetMapper.insert(pojo);
    }

    @Override
    public boolean delete(ProtectionTarget pojo) {
        return protectionTargetMapper.delete(pojo);
    }

    @Override
    public boolean update(ProtectionTarget pojo) {
        return protectionTargetMapper.update(pojo);
    }

    @Override
    public ProtectionTarget selectById(Integer id) {
        return protectionTargetMapper.selectById(id);
    }

    @Override
    public List<ProtectionTarget> queryForAll(ProtectionTarget pojo) {
        return protectionTargetMapper.queryForAll(pojo);
    }

    @Override
    public List<ProtectionTarget> queryForPage(ProtectionTarget pojo, Integer page, Integer pageSize) {
        Map<String,Object> paramMap = PojoUtil.pojoToMap(pojo, page, pageSize);
        return protectionTargetMapper.queryForPage(paramMap);
    }

    @Override
    public Long queryForCounts(ProtectionTarget pojo) {
        return protectionTargetMapper.queryForCounts(pojo);
    }
}
