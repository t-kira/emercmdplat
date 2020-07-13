package com.kira.emercmdplat.pojo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author: kira
 * @Date: 2020/4/7 22:44
 * @Description:核实报告
 */
@Data
public class VerifyReport extends Base{

    private Long id;
    /**
     * event_receive.id事件ID
     */
    @NotNull(message = "事件ID不能为空")
    @Min(value = 1, message = "事件ID错误")
    private Long eventId;
    /**
     * plan_version.id
     */
    @NotNull(message = "初判预案不能为空")
    @Min(value = 1, message = "初判预案错误")
    private Long pvId;
    /**
     * plan_response.id
     */
    @NotNull(message = "初判等级不能为空")
    @Min(value = 1, message = "初判等级错误")
    private Long prId;
    /**
     * 领导ID
     */
    @NotNull(message = "上报领导信息不能为空")
    @Min(value = 1, message = "上报领导信息错误")
    private Long contactId;
    /**
     * 研判意见
     */
    private String judgeOpinion;
    /**
     * 附件地址
     */
    private String attachAddress;

    @NotNull(message = "富文本内容不能为空")
    private String richText;
    /**
     * 创建时间
     */
    private String createTime;

    private String quickReportAddr;
}
