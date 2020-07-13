package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/10 01:07
 * @Description:领导批示
 */
@Data
public class LeaderInstruct extends Base{

    private Long id;
    /**
     * 事件ID
     */
    private Long eventId;
    /**
     *领导ID
     */
    private Long contactId;
    /**
     * 批示内容
     */
    private String instructContent;
    /**
     * 批示状态 0：已批示 1：未批示
     */
    private Integer instructStatus;
    /**
     * 批示时间
     */
    private String instructTime;

}
