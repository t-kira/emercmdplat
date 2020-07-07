package com.kira.emercmdplat.pojo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author: kira
 * @Date: 2020/5/22 17:09
 * @Description:事件任务
 */
@Data
public class Task {

    private Long id;
    /**
     * 事件ID
     */
    @NotNull(message = "事件ID为必传项")
    @Min(value = 1, message = "事件ID为必传项")
    private Long eventId;
    /**
     * 任务标题
     */
    private String taskTitle;
    /**
     * 流程
     */
    private Long prfId;
    /**
     * 流程名称
     */
    private String prfName;
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
     * 联系人电话
     */
    private String telephone;
    /**
     * 联系人姓名
     */
    private String contactName;
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
    /**
     * 任务类型 1:事件任务 2：预案任务
     */
    private Integer taskType;
    /**
     * 预案任务中任务具体所属的分组
     */
    private Long dataTypeId;
}
