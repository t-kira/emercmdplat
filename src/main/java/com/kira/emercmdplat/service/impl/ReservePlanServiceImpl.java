package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.ReservePlanMapper;
import com.kira.emercmdplat.pojo.QuickReportResult;
import com.kira.emercmdplat.pojo.ReservePlan;
import com.kira.emercmdplat.pojo.ReservePlanExtend;
import com.kira.emercmdplat.pojo.ReservePlanResult;
import com.kira.emercmdplat.service.ReservePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/13 21:10
 * @Description:
 */
@Service
public class ReservePlanServiceImpl implements ReservePlanService {

    @Autowired
    private ReservePlanMapper rpm;
    /**
     * 增/插入数据
     *
     * @param reservePlan
     * @return
     */
    @Override
    public int insert(ReservePlan reservePlan) {
        return rpm.insert(reservePlan);
    }

    /**
     * 删（其实就是修改了状态，使其不显示，在客户端看来是删除了）
     *
     * @param reservePlan
     * @return
     */
    @Override
    public boolean delete(ReservePlan reservePlan) {
        return rpm.delete(reservePlan);
    }

    /**
     * 改，
     *
     * @param reservePlanResult
     * @return
     */
    @Override
    public boolean update(ReservePlanResult reservePlanResult) {
        return rpm.update(reservePlanResult);
    }

    /**
     * 根据id查询单个
     *
     * @param eventId
     * @return
     */
    @Override
    public List<ReservePlanResult> selectByEventId(Long eventId) {
        return rpm.selectByEventId(eventId);
    }

    /**
     * 根据id查询单个
     *
     * @param id
     * @return
     */
    @Override
    public ReservePlanResult selectById(Long id) {
        return rpm.selectById(id);
    }

    /**
     * 按条件查询
     *
     * @param reservePlan
     * @return
     */
    @Override
    public List<QuickReportResult> queryForAll(ReservePlan reservePlan) {
        return rpm.queryForAll(reservePlan);
    }

    @Override
    public List<QuickReportResult> queryForPage(ReservePlanExtend reservePlanExtend) {
        reservePlanExtend.setPage((reservePlanExtend.getPage() - 1) * reservePlanExtend.getPageSize());
        return rpm.queryForPage(reservePlanExtend);
    }

    @Override
    public Long queryForCounts(ReservePlanExtend reservePlanExtend) {
        return rpm.queryForCounts(reservePlanExtend);
    }
}
