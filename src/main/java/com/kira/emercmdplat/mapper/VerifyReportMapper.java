package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.mapper.base.BaseMapper;
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

    List<VerifyReport> queryForAll(VerifyReport verifyReport);

    List<VerifyReport> queryForPage(Map<String, Object> map);

    Long queryForCounts(VerifyReport verifyReport);

    VerifyReport selectByEventId(Long eventId);
}
