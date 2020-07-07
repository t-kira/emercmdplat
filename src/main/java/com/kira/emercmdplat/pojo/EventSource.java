package com.kira.emercmdplat.pojo;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
    @NotNull(message = "事件ID为必传项")
    @Min(value = 1, message = "事件ID为必传项")
    private Long eventId;
    /**
     * 影响范围 单位(米)
     */
    @NotNull(message = "影响范围不能为空")
    @DecimalMin(value = "0", message = "影响范围必须大于0")
    private Double influenceScope;

    /**
     *
     */
    private Long dataTypeId;
}
