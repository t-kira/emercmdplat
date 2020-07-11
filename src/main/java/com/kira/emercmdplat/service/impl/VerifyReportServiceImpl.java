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
        VerifyReport verifyReport = vrm.selectByEventId(pojo.getEventId());
        if (verifyReport != null){
            pojo.setId(verifyReport.getId());
            boolean result = vrm.update(pojo);
            if (result)
                return 1;
            else
                return 0;
        }else {
            return vrm.insert(pojo);
        }
    }

    @Override
    public boolean delete(Long verifyReportId) {
        return vrm.delete(verifyReportId);
    }

    @Override
    public boolean update(VerifyReport pojo) {
        return vrm.update(pojo);
    }

    @Override
    public VerifyReport selectById(Long verifyReportId) {
        return vrm.selectById(verifyReportId);
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
