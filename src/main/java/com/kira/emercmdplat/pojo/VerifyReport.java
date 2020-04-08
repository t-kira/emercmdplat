package com.kira.emercmdplat.pojo;

/**
 * @Author: kira
 * @Date: 2020/4/7 22:44
 * @Description:核实报告
 */
public class VerifyReport {

    private Long id;
    /**
     * event_receive.id事件ID
     */
    private Long eId;
    /**
     * plan_version.id
     */
    private Long pvId;
    /**
     * plan_response.id
     */
    private Long prId;
    /**
     * 研判意见
     */
    private String judgeOpinion;
    /**
     * 附件地址
     */
    private String attachAddress;
    /**
     * 创建时间
     */
    private String createTime;

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

    public Long getPvId() {
        return pvId;
    }

    public void setPvId(Long pvId) {
        this.pvId = pvId;
    }

    public Long getPrId() {
        return prId;
    }

    public void setPrId(Long prId) {
        this.prId = prId;
    }

    public String getJudgeOpinion() {
        return judgeOpinion;
    }

    public void setJudgeOpinion(String judgeOpinion) {
        this.judgeOpinion = judgeOpinion;
    }

    public String getAttachAddress() {
        return attachAddress;
    }

    public void setAttachAddress(String attachAddress) {
        this.attachAddress = attachAddress;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
