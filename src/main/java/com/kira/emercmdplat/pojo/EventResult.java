package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/12 23:19
 * @Description:事件查询返回结果集
 */
@Data
public class EventResult extends Event{
    /**
     * 事件名称
     */
    private String ptName;
    /**
     * 值班人员姓名
     */
    private String contactName;
    /**
     *事发区域名称
     */
    private String incidentAreaName;
    /**
     * 本级报送单位名称
     */
    private String mechanismName;

    /**
     * 报送单位名称
     */
    private String reportMechanismName;
}
