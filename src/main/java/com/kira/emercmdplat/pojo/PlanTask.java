package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/5/23 20:30
 * @Description:
 */
@Data
public class PlanTask {

    private Long id;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 任务内容
     */
    private String taskContent;
    /**
     * 响应人员ids
     */
    private String responsePersonIds;
    /**
     * 最新反馈
     */
    private Long latestFeedbackId;

    /**
     * 开始时间 调度时间
     */
    private String startTime;
    /**
     * 结束时间 完成时间
     */
    private String endTime;
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
     * 任务类型
     */
    private Integer taskType;
}
