package com.kira.emercmdplat.pojo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author: kira
 * @Date: 2020/4/13 17:18
 * @Description:
 */
@Data
public class ReservePlan {
    @NotNull(message = "预案ID不能为空")
    @Min(value = 1, message = "预案ID必传")
    private Long id;
    @NotNull(message = "事件ID不能为空")
    @Min(value = 1, message = "事件ID必传")
    private Long eventId;

    private String startTime;
    @NotNull(message = "预案ID不能为空")
    @Min(value = 0, message = "预案状态数据错误")
    private Integer status;
}
