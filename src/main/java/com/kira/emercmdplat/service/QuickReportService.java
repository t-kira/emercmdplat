package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.QuickReport;
import com.kira.emercmdplat.pojo.QuickReportResult;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/13 03:50
 * @Description:
 */
public interface QuickReportService {

    /**
     * 增/插入数据
     *
     * @param quickReport
     * @return
     */
    int insert(QuickReport quickReport);

    /**
     * 删（其实就是修改了状态，使其不显示，在客户端看来是删除了）
     *
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 改，
     *
     * @param quickReport
     * @return
     */
    boolean update(QuickReport quickReport);

    /**
     * 根据id查询单个
     *
     * @param id
     * @return
     */
    QuickReportResult selectById(Long id);

    /**
     * 按条件查询
     *
     * @param quickReport
     * @return
     */
    List<QuickReportResult> queryForAllOrPage(QuickReport quickReport);

    Long queryForCounts(QuickReport quickReport);
}
