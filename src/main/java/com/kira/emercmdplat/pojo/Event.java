package com.kira.emercmdplat.pojo;

import com.kira.emercmdplat.annotation.DateTime;
import com.kira.emercmdplat.annotation.Phone;
import com.kira.emercmdplat.controller.EventController;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * @Author: kira
 * @Date: 2020/4/6 21:10
 * @Description:事件信息表
 */
@Data
public class Event {
    /**
     * 事件ID
     */
    private Long id;
    /**
     * 事件编号
     */
    private String eventNumber;
    /**
     * 事件描述
     */
    private String eventDesc;
    /**
     * 事件标题
     */
    @NotNull(message = "事件标题不能为空")
    private String eventTitle;
    /**
     * 接报时间
     */
    private String receiveTime;
    /**
     * 事发时间
     */
    @NotNull(message = "事件时间不能为空")
    @DateTime(format = "yyyy-MM-dd HH:mm:ss", message = "时间格式错误,正确格式为:年-月-日 时:分:秒")
    private String incidentTime;
    /**
     * 事发地点
     */
    @NotNull(message = "事发地点不能为空")
    private String incidentLocation;
    /**
     * 经度
     */
    @NotNull(message = "经度不能为空")
    @Digits(integer = 4, fraction = 6,message = "经度只能为数值型")
    @DecimalMin(value = "0", message = "经度必须大于0")
    private Double lng;
    /**
     * 纬度
     */
    @NotNull(message = "纬度不能为空")
    @Digits(integer = 4, fraction = 6,message = "纬度只能为数值型")
    @DecimalMin(value = "0", message = "纬度必须大于0")
    private Double lat;
    /**
     * 影响范围
     */
    @NotNull(message = "影响范围不能为空")
    @DecimalMin(value = "0", message = "影响范围必须大于0")
    private Double influenceSphere;
    /**
     * 事发区域
     */
    private Long incidentAreaId;
    /**
     * 事件类型
     */
    @NotNull(message = "事件类型不能为空")
    @Min(value = 1, message = "事件类型只能为数值型")
    private Long ptId;
    /**
     * 报送单位
     */
    @NotNull(message = "报送单位不能为空")
    @Min(value = 1, message = "报送单位为必填")
    private Long reportMechanismId;
    /**
     * 报送人姓名
     */
    @NotNull(message = "报送人不能为空")
    @Size(min = 1, max = 10, message = "报送人姓名不能过短或过长")
    private String reporter;
    /**
     * 报送人电话
     */
    @NotNull(message = "报送人电话不能为空")
    @Phone
    private String reportTel;
    /**
     * 值班人员ID
     */
    private Long contactId;
    /**
     * 事件状态
     */
    private Integer status;
    /**
     * 事件进程
     */
    private Integer process;
    /**
     * 总结
     */
    private String summary;
    /**
     * 合并原因
     */
    private String mergeReason;
    /**
     * 事件等级
     */
    @NotNull(message = "事件等级不能为空")
    @Min(value = 1, message = "事件等级为必填")
    private Integer eventLevel;
    /**
     * 核实方式：1：视频监控 2：电话 3：app
     */
    private Integer verifyMethod;
    /**
     * 事件类型：1：一般事件 2：应急事件
     */
    private Integer eventType;
    /**
     * 核实状态：1：属实 2：不属实 3:被合并
     */
    private Integer verifyStatus;
    /**
     * 1:系统报送、2:电话、3:传真、4:其他；
     */
    private Integer reportType;
    /**
     * 本级单位
     */
    private Long mechanismId;
    /**
     * 附件地址
     */
    private String attachAddr;
}
