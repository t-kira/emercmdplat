package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.VerifyReportMapper;
import com.kira.emercmdplat.pojo.VerifyReport;
import com.kira.emercmdplat.service.VerifyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/4/8 23:49
 * @Description:
 */
@Service
public class VerifyReportServiceImpl implements VerifyReportService {

    @Autowired
    private VerifyReportMapper vrm;

    @Override
    public int insert(VerifyReport pojo) {
        return vrm.insert(pojo);
    }

    @Override
    public boolean delete(VerifyReport pojo) {
        return vrm.delete(pojo);
    }

    @Override
    public boolean update(VerifyReport pojo) {
        return vrm.update(pojo);
    }

    @Override
    public VerifyReport selectById(Integer id) {
        return vrm.selectById(id);
    }

    @Override
    public List<VerifyReport> queryForAll(VerifyReport pojo) {
        return vrm.queryForAll(pojo);
    }

    @Override
    public List<VerifyReport> queryForPage(VerifyReport pojo, Integer page, Integer pageSize) {
        Map<String, Object> paramMap = new HashMap<>();
        return vrm.queryForPage(paramMap);
    }

    @Override
    public Long queryForCounts(VerifyReport pojo) {
        return vrm.queryForCounts(pojo);
    }
}
