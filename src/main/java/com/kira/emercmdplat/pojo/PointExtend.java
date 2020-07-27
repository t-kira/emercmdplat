package com.kira.emercmdplat.pojo;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @Author: kira
 * @Date: 2020/7/26 12:30
 * @Description:
 */
@Data
public class PointExtend extends Point{
    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空")
    private Long beginTime;
    /**
     * 结束时间
     */
    @NotNull(message = "结束时间不能为空")
    private Long endTime;
}
