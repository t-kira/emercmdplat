package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/13 16:41
 * @Description:
 */
@Data
public class QuickReportResult extends QuickReport{
    /**
     * 事件编号
     */
    private String eventNumber;
    /**
     * 事件标题
     */
    private String eventTitle;
    /**
     * 事件类型名称
     */
    private String ptName;
}
