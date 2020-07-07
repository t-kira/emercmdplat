package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/17 23:21
 * @Description:
 */
@Data
public class MessageResult extends Message{
    /**
     * 事件编号
     */
    private String eventNumber;
    /**
     * 事件标题
     */
    private String eventTitle;
    /**
     * 事件描述
     */
    private String eventDesc;
    /**
     * 接报事件
     */
    private String receiveTime;
    /**
     * 事发事件
     */
    private String incidentTime;
    /**
     * 事件类型名称
     */
    private String ptName;
    /**
     * 值班人员
     */
    private String contactName;
    /**
     * 报告人姓名
     */
    private String reporter;
    /**
     * 报告人电话
     */
    private String reportTel;
    /**
     * 要情速报pdf地址
     */
    private String quickReportAddr;
    /**
     * 附件地址
     */
    private String enclosureAddr;
    /**
     * 事件等级
     */
    private Integer eventLevel;
    /**
     * 研判意见
     */
    private String judgeOpinion;
    /**
     * 初判预案名称
     */
    private String pvName;
    /**
     * 初判等级名称
     */
    private String prLevel;
    /**
     * 事发地点
     */
    private String incidentLocation;
    /**
     * 经度
     */
    private Double lng;
    /**
     * 纬度
     */
    private Double lat;
}
