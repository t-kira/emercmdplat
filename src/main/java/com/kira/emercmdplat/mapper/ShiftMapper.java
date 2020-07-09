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

    List<Shift> queryShiftForAll(ShiftExtend shiftExtend);

    List<Shift> queryShiftForPage(ShiftExtend shiftExtend);

    Long queryShiftForCounts(ShiftExtend shiftExtend);

    int insertShiftDetail(ShiftDetail shiftDetail);

    boolean deleteShiftDetail(Long shiftDetailId);

    ShiftDetail selectByShiftDetailId(Long shiftId);

    List<ShiftDetail> queryShiftDetailForAll(ShiftDetail shiftDetail);

    List<ShiftDetailResult> queryDetail(Map<String,String> paramMap);

    Long countStartUpShiftByType(Integer shiftType);

    Long countShiftDetail(ShiftDetail shiftDetail);
}
