package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.QuickReportResult;
import com.kira.emercmdplat.pojo.ReservePlan;
import com.kira.emercmdplat.pojo.ReservePlanExtend;
import com.kira.emercmdplat.pojo.ReservePlanResult;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/13 18:52
 * @Description:
 */
public interface ReservePlanService {

    /**
     * 增/插入数据
     *
     * @param reservePlan
     * @return
     */
    int insert(ReservePlan reservePlan);

    /**
     * 删（其实就是修改了状态，使其不显示，在客户端看来是删除了）
     *
     * @param reservePlan
     * @return
     */
    boolean delete(ReservePlan reservePlan);

    /**
     * 改，
     *
     * @param reservePlanResult
     * @return
     */
    boolean update(ReservePlanResult reservePlanResult);

    /**
     * 根据id查询单个
     *
     * @param eventId
     * @return
     */
    List<ReservePlanResult> selectByEventId(Long eventId);

    /**
     * 根据id查询单个
     *
     * @param id
     * @return
     */
    ReservePlanResult selectById(Long id);

    /**
     * 按条件查询
     *
     * @param reservePlan
     * @return
     */
    List<QuickReportResult> queryForAll(ReservePlan reservePlan);

    List<QuickReportResult> queryForPage(ReservePlanExtend reservePlanExtend);

    Long queryForCounts(ReservePlanExtend reservePlanExtend);
}
