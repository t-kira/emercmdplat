package com.kira.emercmdplat.pojo;

import com.kira.emercmdplat.annotation.DateTime;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author: kira
 * @Date: 2020/7/9 15:32
 * @Description:排班详情
 */
@Data
public class ShiftDetail {

    private Long id;

    @NotNull(message = "排班日期不能为空")
    @DateTime(format = "yyyy-MM-dd", message = "排班日期格式不对")
    private String shiftDate;

    @NotNull(message = "班次ID不能为空")
    @Min(value = 1, message = "班次ID必须大于0")
    private Long shiftId;
    /**
     * 班次名称
     */
    private String shiftName;

    @NotNull(message = "值班人员ID不能为空")
    @Min(value = 1, message = "值班人员ID必须大于0")
    private Long contactId;
    /**
     * 值班人员姓名
     */
    private String contactName;
    /**
     * 值班职位ID
     */
    private Long shiftJobId;
    /**
     * 值班职位名称
     */
    private String shiftJobName;
    /**
     * 新增时间
     */
    private String createTime;
}
