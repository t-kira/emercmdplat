package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.VerifyReport;
import com.kira.emercmdplat.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/4/8 23:49
 * @Description:核实报告Service
 */
public interface VerifyReportService {

    int insert(VerifyReport verifyReport);

    boolean delete(Long verifyReportId);

    boolean update(VerifyReport verifyReport);

    VerifyReport selectById(Long verifyReportId);

    List<VerifyReport> queryForAllOrPage(VerifyReport verifyReport);

    Long queryForCounts(VerifyReport verifyReport);

}
