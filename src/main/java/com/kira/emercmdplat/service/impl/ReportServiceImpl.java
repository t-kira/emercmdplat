package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.ReportMapper;
import com.kira.emercmdplat.pojo.Report;
import com.kira.emercmdplat.pojo.ReportResult;
import com.kira.emercmdplat.service.ReportService;
import com.kira.emercmdplat.utils.DateUtil;
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
        report.setCreateTime(DateUtil.getNowStr());
        Report r = new Report();
        r.setType(report.getType());
        r.setOrder("id");
        r.setOrderType("desc");
        int period = 1;
        List<ReportResult> list = rm.queryForAllOrPage(r);
        if (list != null && list.size() >0) {
            ReportResult _report = list.get(0);
            period = _report.getPeriod() + 1;
        }
        report.setPeriod(period);
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
     * @param report
     * @return
     */
    @Override
    public List<ReportResult> queryForAllOrPage(Report report) {
        if (report.getPage() != null) {
            report.setPage((report.getPage() - 1) * report.getPageSize());
        }
        return rm.queryForAllOrPage(report);
    }

    @Override
    public Long queryForCounts(Report report) {
        return rm.queryForCounts(report);
    }
}
