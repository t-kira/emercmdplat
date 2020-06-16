package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/5/23 17:39
 * @Description:战时一张图事件发生影响范围
 */
@Data
public class EventSource {

    /**
     * 事件ID
     */
    private Long eventId;
    /**
     * 影响范围 单位(米)
     */
    private Double influenceScope;

    /**
     *
     */
    private Long dataTypeId;
}
