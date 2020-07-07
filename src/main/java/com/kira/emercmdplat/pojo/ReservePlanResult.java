package com.kira.emercmdplat.pojo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
    @NotNull(message = "预案等级ID不能为空")
    @Min(value = 1, message = "预案等级ID传值错误")
    private Long prId;
    /**
     * 核实报告ID
     */
    @NotNull(message = "核实报告ID不能为空")
    @Min(value = 1, message = "核实报告ID传值错误")
    private Long vrId;
    /**
     * 预案响应等级
     */
    private String prLevel;
}
