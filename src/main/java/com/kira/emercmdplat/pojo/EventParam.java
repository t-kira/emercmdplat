package com.kira.emercmdplat.pojo;

/**
 * @Author: kira
 * @Date: 2020/4/6 22:59
 * @Description:事件参数
 */
public class EventParam {
    /**
     * 事件录入事件参数ID
     */
    private Long id;
    /**
     * 事件ID
     */
    private Long eId;
    /**
     * 事件编号
     */
    private String eventNumber;
    /**
     * 预案ID
     */
    private Long ppId;
    /**
     * 参数值
     */
    private String ppValue;

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

    public String getEventNumber() {
        return eventNumber;
    }

    public void setEventNumber(String eventNumber) {
        this.eventNumber = eventNumber;
    }

    public Long getPpId() {
        return ppId;
    }

    public void setPpId(Long ppId) {
        this.ppId = ppId;
    }

    public String getPpValue() {
        return ppValue;
    }

    public void setPpValue(String ppValue) {
        this.ppValue = ppValue;
    }
}
