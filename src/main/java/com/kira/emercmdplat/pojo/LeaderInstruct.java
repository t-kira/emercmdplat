package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/10 01:07
 * @Description:领导批示
 */
@Data
public class LeaderInstruct{

    private Long id;
    /**
     * 事件ID
     */
    private Long eId;
    /**
     *领导ID
     */
    private Long did;
    /**
     * 批示内容
     */
    private String instructContent;
    /**
     * 批示状态
     */
    private Integer instructStatus;
    /**
     * 批示时间
     */
    private String instructTime;

}
