package com.kira.emercmdplat.pojo;

/**
 * @Author: kira
 * @Date: 2020/4/6 21:10
 * @Description:事件信息表
 */
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
    private Long ppId;
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
    private Long dId;
    /**
     * 事件状态
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventNumber() {
        return eventNumber;
    }

    public void setEventNumber(String eventNumber) {
        this.eventNumber = eventNumber;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getIncidentTime() {
        return incidentTime;
    }

    public void setIncidentTime(String incidentTime) {
        this.incidentTime = incidentTime;
    }

    public String getIncidentLocation() {
        return incidentLocation;
    }

    public void setIncidentLocation(String incidentLocation) {
        this.incidentLocation = incidentLocation;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getInfluenceSphere() {
        return influenceSphere;
    }

    public void setInfluenceSphere(Double influenceSphere) {
        this.influenceSphere = influenceSphere;
    }

    public Long getIncidentAreaId() {
        return incidentAreaId;
    }

    public void setIncidentAreaId(Long incidentAreaId) {
        this.incidentAreaId = incidentAreaId;
    }

    public Long getPpId() {
        return ppId;
    }

    public void setPpId(Long ppId) {
        this.ppId = ppId;
    }

    public Long getReportMechanismId() {
        return reportMechanismId;
    }

    public void setReportMechanismId(Long reportMechanismId) {
        this.reportMechanismId = reportMechanismId;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getReportTel() {
        return reportTel;
    }

    public void setReportTel(String reportTel) {
        this.reportTel = reportTel;
    }

    public Long getdId() {
        return dId;
    }

    public void setdId(Long dId) {
        this.dId = dId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
