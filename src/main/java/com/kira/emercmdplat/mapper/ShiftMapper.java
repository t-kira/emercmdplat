package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/4/21 22:50
 * @Description:
 */
public interface ShiftMapper {

    int insertShift(Shift shift);

    boolean deleteShift(Long shiftId);

    boolean updateShift(Shift shift);

    Shift selectByShiftId(Long shiftId);

    List<Shift> queryShiftForAllOrPage(Shift shift);

    Long queryShiftForCounts(Shift shift);

    int insertShiftDetail(ShiftDetail shiftDetail);

    boolean deleteShiftDetail(Long shiftDetailId);

    List<ShiftDetailResult> queryDetail(Map<String,String> paramMap);

    Long countStartUpShiftByType(Integer shiftType);

    Long countShiftDetail(ShiftDetail shiftDetail);
}
