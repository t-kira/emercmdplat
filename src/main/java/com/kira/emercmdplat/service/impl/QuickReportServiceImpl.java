package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.QuickReportMapper;
import com.kira.emercmdplat.pojo.QuickReport;
import com.kira.emercmdplat.pojo.QuickReportResult;
import com.kira.emercmdplat.service.QuickReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/13 03:51
 * @Description:
 */
@Service
public class QuickReportServiceImpl implements QuickReportService {

    @Autowired
    private QuickReportMapper qrm;

    @Override
    public int insert(QuickReport pojo) {
        return qrm.insert(pojo);
    }

    @Override
    public boolean delete(Long id) {
        return qrm.delete(id);
    }

    @Override
    public boolean update(QuickReport pojo) {
        return qrm.update(pojo);
    }

    @Override
    public QuickReportResult selectById(Long id) {
        return qrm.selectById(id);
    }

    @Override
    public List<QuickReportResult> queryForAll(QuickReport pojo) {
        return qrm.queryForAll(pojo);
    }

    @Override
    public List<QuickReportResult> queryForPage(QuickReport quickReport) {
        quickReport.setPage((quickReport.getPage() - 1) * quickReport.getPageSize());
        return qrm.queryForPage(quickReport);
    }

    @Override
    public Long queryForCounts(QuickReport quickReport) {
        return qrm.queryForCounts(quickReport);
    }
}
