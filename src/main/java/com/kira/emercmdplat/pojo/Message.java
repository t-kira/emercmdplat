package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/17 23:04
 * @Description:信息管理
 */
@Data
public class Message extends Base{

    private Long id;
    /**
     * 事件id
     */
    private Long eventId;
    /**
     * 批示人员ID
     */
    private Long contactId;
    /**
     * 事件被转人员
     */
    private Long toContactId;
    /**
     * 核实报告ID
     */
    private Long vId;
    /**
     * 信息类型 1:转办督办 2：呈报上报 3：退回
     */
    private Integer type;
    /**
     * 信息状态 0：未读 1：已读 2:删除
     */
    private Integer status;
    /**
     * 原因
     */
    private String reason;
}
