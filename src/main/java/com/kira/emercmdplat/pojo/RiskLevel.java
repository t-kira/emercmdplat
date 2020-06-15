package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/6/15 10:47
 * @Description:
 */
@Data
public class RiskLevel {

    private Long id;
    /**
     * 次生衍生ID
     */
    private Long sdId;
    /**
     * 次生衍生等级
     */
    private Integer level;
    /**
     * 危险等级对应的最近距离
     */
    private Double latelyDistance;
    /**
     * 危险等级对应的最远距离
     */
    private Double furthestDistance;
}
