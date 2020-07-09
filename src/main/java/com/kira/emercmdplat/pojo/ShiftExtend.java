package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/7/9 14:51
 * @Description:
 */
@Data
public class ShiftExtend extends Shift{

    private Integer page;

    private Integer pageSize;

    private String order;

    private String orderType;
}
