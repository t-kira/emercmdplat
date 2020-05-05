package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/5/2 18:20
 * @Description:事件核实请求
 */
@Data
public class VerifyEventReq {

    /**
     * 核实方式：1：视频监控 2：电话 3：app
     */
    private Integer verifyMethod;
    /**
     * 事件类型：1：一般事件 2：应急事件
     */
    private Integer eventType;
    /**
     * 核实状态：1：属实 2：不属实
     */
    private Integer verifyStatus;
    /**
     * 合并原因
     */
    private String mergeReason;
    /**
     * 主事件ID
     */
    private  Long mainEId;
    /**
     * 从事件ID
     */
    private Long coverEId;
}