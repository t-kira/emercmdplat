package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/6/14 17:02
 * @Description:
 */
@Data
public class SecondaryDerivation {

    private Long id;
    /**
     * 事件标题
     */
    private String eventTitle;
    /**
     * 次生衍生等级
     */
    private Integer riskLevel;
    /**
     * 次生衍生距离
     */
    private Double distance;
}
