package com.kira.emercmdplat.pojo;

import lombok.Data;

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
    private String eventTitle;
    /**
     * 接报时间
     */
    private String receiveTime;
    /**
     * 事发时间
     */
    private String incidentTime;
    /**
     * 事发地点
     */
    private String incidentLocation;
    /**
     * 经度
     */
    private Double lng;
    /**
     * 纬度
     */
    private Double lat;
    /**
     * 影响范围
     */
    private Double influenceSphere;
    /**
     * 事发区域
     */
    private Long incidentAreaId;
    /**
     * 事件类型
     */
    private Long ptId;
    /**
     * 报送单位
     */
    private Long reportMechanismId;
    /**
     * 报送人姓名
     */
    private String reporter;
    /**
     * 报送人电话
     */
    private String reportTel;
    /**
     * 值班人员ID
     */
    private Long did;
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
