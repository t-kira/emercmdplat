package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.ReportMapper;
import com.kira.emercmdplat.pojo.Report;
import com.kira.emercmdplat.pojo.ReportExtend;
import com.kira.emercmdplat.pojo.ReportResult;
import com.kira.emercmdplat.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/14 00:27
 * @Description:
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportMapper rm;
    /**
     * 增/插入数据
     *
     * @param report
     * @return
     */
    @Override
    public int insert(Report report) {
        return rm.insert(report);
    }

    /**
     * 删（其实就是修改了状态，使其不显示，在客户端看来是删除了）
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Long id) {
        return rm.delete(id);
    }

    /**
     * 改，
     *
     * @param report
     * @return
     */
    @Override
    public boolean update(Report report) {
        return rm.update(report);
    }

    /**
     * 根据id查询单个
     *
     * @param id
     * @return
     */
    @Override
    public ReportResult selectById(Long id) {
        return rm.selectById(id);
    }

    /**
     * 按条件查询
     *
     * @param reportExtend
     * @return
     */
    @Override
    public List<ReportResult> queryForAll(ReportExtend reportExtend) {
        return rm.queryForAll(reportExtend);
    }

    @Override
    public List<ReportResult> queryForPage(ReportExtend reportExtend) {
        reportExtend.setPage((reportExtend.getPage() - 1) * reportExtend.getPageSize());
        return rm.queryForPage(reportExtend);
    }

    @Override
    public Long queryForCounts(ReportExtend reportExtend) {
        return rm.queryForCounts(reportExtend);
    }
}
