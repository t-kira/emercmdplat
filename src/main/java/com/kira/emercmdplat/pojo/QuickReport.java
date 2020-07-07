package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/11 01:44
 * @Description:要情速报
 */
@Data
public class QuickReport {

    private Long id;
    /**
     * 事件ID
     */
    private Long eventId;
    /**
     * 期数
     */
    private Integer periodNumber;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 主送
     */
    private Integer mainDeliveryId;
    /**
     * 抄送
     */
    private Integer ccId;
    /**
     * 报送
     */
    private Integer submitId;
    /**
     * 编辑
     */
    private Long editId;
    /**
     * 联系电话
     */
    private String contactNum;
    /**
     * 签发人
     */
    private Integer issuerId;
    /**
     * 签发单位
     */
    private Integer issueCompanyId;
    /**
     * 签发时间
     */
    private String issueTime;
    /**
     * 速报类型
     */
    private Integer type;
    /**
     * 来源0：要情速报 1:核实报告
     */
    private Integer origin;
    /**
     * pdf地址
     */
    private String pdfAddr;
}
