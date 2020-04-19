package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.pojo.*;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/13 18:36
 * @Description:
 */
public interface ReservePlanMapper {

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
     * @param reservePlan
     * @return
     */
    boolean update(ReservePlan reservePlan);

    /**
     * 根据id查询单个
     *
     * @param eId
     * @return
     */
    List<ReservePlanResult> selectByEId(Long eId);

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
