package com.kira.emercmdplat.pojo;

/**
 * @Author: kira
 * @Date: 2020/4/7 00:27
 * @Description:事件发展
 */
public class EventDevelopment {

    private Long id;
    /**
     * 事件ID
     */
    private Long eId;
    /**
     * 报告时间
     */
    private String reportTime;
    /**
     * 续报内容
     */
    private String reportContent;
    /**
     * 续报人员
     */
    private String reporter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long geteId() {
        return eId;
    }

    public void seteId(Long eId) {
        this.eId = eId;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }
}
