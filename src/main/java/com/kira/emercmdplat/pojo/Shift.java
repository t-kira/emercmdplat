package com.kira.emercmdplat.pojo;

import com.kira.emercmdplat.annotation.DateTime;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/7/9 14:31
 * @Description:班次
 */
@Data
public class Shift extends Base{

    private Long id;
    /**
     * 班次名称
     */
    @NotNull(message = "班次名称不能为空")
    private String shiftName;
    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空")
    @DateTime(format = "yyyy-MM-dd HH:mm", message = "开始时间格式不对")
    private String startTime;
    /**
     * 结束时间
     */
    @NotNull(message = "结束时间不能为空")
    @DateTime(format = "yyyy-MM-dd HH:mm", message = "结束时间格式不对")
    private String endTime;
    /**
     * 机构ID
     */
    @NotNull(message = "机构不能为空")
    @Min(value = 1, message = "机构必须大于0")
    private Long mechanismId;
    /**
     * 机构名称
     */
    private String mechanismName;
    /**
     * 班次人数
     */
    @NotNull(message = "班次人数不能为空")
    @Min(value = 1, message = "班次人数必须大于0")
    private Integer personNumber;

    private List<ShiftJob> shiftJobList;
    /**
     * 班次类型
     */
    @NotNull(message = "班次类型不能为空")
    @Min(value = 1, message = "班次类型必须大于0")
    private Integer shiftType;

    /**
     * 班次状态 生效:0 失效:1
     */
    private Integer shiftStatus;

    private String createTime;
}
