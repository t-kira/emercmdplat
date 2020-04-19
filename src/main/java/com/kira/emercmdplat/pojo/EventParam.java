package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/6 22:59
 * @Description:事件参数
 */
@Data
public class EventParam {
    /**
     * 事件录入事件参数ID
     */
    private Long id;
    /**
     * 事件ID
     */
    private Long eId;
    /**
     * 事件编号
     */
    private String eventNumber;
    /**
     * 预案ID
     */
    private Long ppId;
    /**
     * 参数值
     */
    private String ppValue;

}
