package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/7 00:27
 * @Description:事件发展
 */
@Data
public class EventDevelopment {

    private Long id;
    /**
     * 事件ID
     */
    private Long eId;
    /**
     * 报告时间
     */
    private String reportTime;
    /**
     * 续报内容
     */
    private String reportContent;
    /**
     * 续报人员
     */
    private String reporter;

}
