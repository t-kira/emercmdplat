package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/6/15 11:10
 * @Description:事件次生衍生
 */
@Data
public class EventRisk {

    private Long id;
    /**
     * 事件标题
     */
    private String eventTitle;
    /**
     * 事件ID
     */
    private Long eventId;
    /**
     * 次生衍生等级ID
     */
    private Long rlId;
    /**
     * 风险隐患ID
     */
    private Long hid;
    /**
     * 风险隐患名称
     */
    private String hName;
    /**
     * 风险隐患距离中心的的距离
     */
    private Double distance;
}
