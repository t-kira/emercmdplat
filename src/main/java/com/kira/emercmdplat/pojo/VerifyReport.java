package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/7 22:44
 * @Description:核实报告
 */
@Data
public class VerifyReport {

    private Long id;
    /**
     * event_receive.id事件ID
     */
    private Long eid;
    /**
     * plan_version.id
     */
    private Long pvId;
    /**
     * plan_response.id
     */
    private Long prId;
    /**
     * 领导ID
     */
    private Long did;
    /**
     * 研判意见
     */
    private String judgeOpinion;
    /**
     * 附件地址
     */
    private String attachAddress;
    /**
     * 创建时间
     */
    private String createTime;

    private String quickReportAddr;
}
