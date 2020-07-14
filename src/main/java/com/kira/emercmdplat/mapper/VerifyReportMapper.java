package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.pojo.VerifyReport;

import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/4/8 23:33
 * @Description:核实报告mapper
 */
public interface VerifyReportMapper{

    int insert(VerifyReport verifyReport);

    boolean delete(Long verifyReportId);

    boolean update(VerifyReport verifyReport);

    VerifyReport selectById(Long verifyReportId);

    List<VerifyReport> queryForAllOrPage(VerifyReport verifyReport);

    Long queryForCounts(VerifyReport verifyReport);

    VerifyReport selectByEventId(Long eventId);
}
