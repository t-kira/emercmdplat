package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/5/4 22:04
 * @Description:
 */
@Data
public class SysLog {

    private Long id;
    /**
     * 方法类型
     */
    private Integer sysLogType;
    /**
     * 事件ID
     */
    private Long eid;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 操作
     */
    private String operation;
    /**
     * 方法名
     */
    private String method;
    /**
     * 参数
     */
    private String params;
    /**
     * 操作时间
     */
    private String createTime;
}
