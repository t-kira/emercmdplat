package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/13 17:20
 * @Description:
 */
@Data
public class ReservePlanResult extends ReservePlan{

    /**
     * 事件类型名称
     */
    private String ptName;
    /**
     * 初判预案ID
     */
    private Long pvId;
    /**
     * 初判预案名称
     */
    private String pvName;
    /**
     * 预案等级ID
     */
    private Long prId;
    /**
     * 核实报告ID
     */
    private Long vrId;
    /**
     * 预案响应等级
     */
    private String prLevel;
}
