package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.enums.ShiftStatus;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.mapper.ShiftMapper;
import com.kira.emercmdplat.pojo.Shift;
import com.kira.emercmdplat.pojo.ShiftDetail;
import com.kira.emercmdplat.pojo.ShiftDetailResult;
import com.kira.emercmdplat.pojo.ShiftExtend;
import com.kira.emercmdplat.service.ShiftService;
import com.kira.emercmdplat.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/7/9 15:26
 * @Description:
 */
@Service
public class ShiftServiceImpl implements ShiftService {

    @Autowired
    private ShiftMapper sm;

    @Override
    public int insertShift(Shift shift) {
        long count = sm.countStartUpShiftByType(shift.getShiftType());
        if (count > 0)
            throw new CustomException(ResultEnum.EXIST_DATA.getNo());
        shift.setShiftStatus(ShiftStatus.START_UP.getNo());
        shift.setCreateTime(DateUtil.getNowStr("yyyy-MM-dd HH:mm:ss"));
        return sm.insertShift(shift);
    }

    @Override
    public boolean deleteShift(Long shiftId) {
        return sm.deleteShift(shiftId);
    }

    @Override
    public boolean updateShift(Shift shift) {
        if (shift.getId() == null)
            throw new CustomException(ResultEnum.MISSING_PARAMETER.getNo(), "班次ID不能为空");
        Shift _shift = sm.selectByShiftId(shift.getId());
        if (_shift == null)
            throw new CustomException(ResultEnum.NON_DATA.getNo(), "要修改的班次不存在");
        long count = sm.countStartUpShiftByType(shift.getShiftType());
        if (count > 0)
            throw new CustomException(ResultEnum.EXIST_DATA.getNo(), "该班次已存在");
        return sm.updateShift(shift);
    }

    @Override
    public Shift selectByShiftId(Long shiftId) {
        return sm.selectByShiftId(shiftId);
    }

    @Override
    public List<Shift> queryShiftForAll(ShiftExtend shiftExtend) {
        return sm.queryShiftForAll(shiftExtend);
    }

    @Override
    public List<Shift> queryShiftForPage(ShiftExtend shiftExtend) {
        shiftExtend.setPage((shiftExtend.getPage() - 1) * shiftExtend.getPageSize());
        return sm.queryShiftForPage(shiftExtend);
    }

    @Override
    public Long queryShiftForCounts(ShiftExtend shiftExtend) {
        return sm.queryShiftForCounts(shiftExtend);
    }

    @Override
    public List<ShiftDetailResult> queryDetail(Map<String, String> paramMap) {
        return sm.queryDetail(paramMap);
    }

    @Override
    public int insertShiftDetail(ShiftDetail shiftDetail) {
        long personCount = sm.countShiftDetail(shiftDetail);
        Shift shift = sm.selectByShiftId(shiftDetail.getShiftId());
        if (personCount >= shift.getPersonNumber())
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "该班次当天人数已到上限");
        shiftDetail.setCreateTime(DateUtil.getNowStr("yyyy-MM-dd HH:mm:ss"));
        return sm.insertShiftDetail(shiftDetail);
    }

    @Override
    public boolean deleteShiftDetail(Long shiftDetailId) {
        return sm.deleteShiftDetail(shiftDetailId);
    }
}
