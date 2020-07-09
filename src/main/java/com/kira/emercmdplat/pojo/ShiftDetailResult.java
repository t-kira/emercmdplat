package com.kira.emercmdplat.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/7/9 16:03
 * @Description:
 */
@Data
public class ShiftDetailResult {

    private String shiftDate;

    private List<Shift> shiftList;


}
