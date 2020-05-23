package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/5/22 17:09
 * @Description:事件任务
 */
@Data
public class EventTask {

    private Long id;
    /**
     * 任务标题
     */
    private String taskTitle;
    /**
     * 流程
     */
    private Long prfId;
    /**
     * 开始时间 调度时间
     */
    private String startTime;
    /**
     * 结束时间 完成时间
     */
    private String endTime;
    /**
     * 联系人信息
     */
    private Long contactId;
    /**
     * 经度
     */
    private Double lng;
    /**
     * 纬度
     */
    private Double lat;
    /**
     * 地址
     */
    private String address;
    /**
     * 任务内容
     */
    private String taskContent;
    /**
     * 响应时间
     */
    private String responseTime;
    /**
     * 到达时间
     */
    private String arriveTime;
    /**
     * 任务状态 1：待处理 2：处理中 3：已处理
     */
    private Integer status;
    /**
     * 是否到场 0：到场 非0：未到场
     */
    private Integer isArrive;
}
