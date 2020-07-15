package com.kira.emercmdplat.pojo;

import com.kira.emercmdplat.annotation.DateTime;
import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author: kira
 * @Date: 2020/4/13 23:52
 * @Description:快报
 */
@Data
public class Report extends Base{

    private Long id;
    /**
     * 事件ID
     */
    @NotNull(message = "事件ID不能为空")
    @Min(value = 1, message = "事件ID错误")
    private Long eventId;
    /**
     * 联系人ID
     */
    private Long contactId;
    /**
     * 报告时间
     */
    @NotNull(message = "事件时间不能为空")
    @DateTime(format = "yyyy-MM-dd", message = "时间格式错误,正确格式为:年-月-日")
    private String reportTime;
    /**
     * 关键字
      */
    @NotNull(message = "关键字不能为空")
    private String keyWord;
    /**
     * 报告内容
     */
    @NotNull(message = "报告内容不能为空")
    private String reportContent;
    /**
     * 报告类型1：日报 2：周报 3：月报
     */
    @NotNull(message = "报告类型不能为空")
    @Min(value = 1, message = "报告类型为必填")
    private Integer type;

    private Integer period;

    private String createTime;

    private String updateTime;
}
