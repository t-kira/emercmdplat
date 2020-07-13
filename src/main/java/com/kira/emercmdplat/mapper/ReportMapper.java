package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.pojo.Report;
import com.kira.emercmdplat.pojo.ReportResult;

import java.util.List;
/**
 * @Author: kira
 * @Date: 2020/4/14 00:24
 * @Description:
 */
public interface ReportMapper {

    /**
     * 增/插入数据
     *
     * @param report
     * @return
     */
    int insert(Report report);

    /**
     * 删（其实就是修改了状态，使其不显示，在客户端看来是删除了）
     *
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 改，
     * @param report
     * @return
     */
    boolean update(Report report);

    /**
     * 根据id查询单个
     *
     * @param id
     * @return
     */
    ReportResult selectById(Long id);

    /**
     * 按条件查询
     * @param report
     * @return
     */
    List<ReportResult> queryForAll(Report report);

    List<ReportResult> queryForPage(Report report);

    Long queryForCounts(Report report);
}
