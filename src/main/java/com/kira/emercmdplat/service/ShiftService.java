package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/7/9 15:26
 * @Description:
 */
public interface ShiftService {

    int insertShift(Shift shift);

    boolean deleteShift(Long shiftId);

    boolean updateShift(Shift shift);

    Shift selectByShiftId(Long shiftId);

    List<Shift> queryShiftForAll(Shift shift);

    List<Shift> queryShiftForPage(Shift shift);

    Long queryShiftForCounts(Shift shift);

    List<ShiftDetailResult> queryDetail(Map<String, String> paramMap);

    int insertShiftDetail(ShiftDetail shiftDetail);

    boolean deleteShiftDetail(Long shiftDetailId);
}
