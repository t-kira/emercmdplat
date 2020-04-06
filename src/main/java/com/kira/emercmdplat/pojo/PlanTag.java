package com.kira.emercmdplat.pojo;

public class PlanTag {

    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 预案类型ID
     */
    private Integer ptId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPtId() {
        return ptId;
    }

    public void setPtId(Integer ptId) {
        this.ptId = ptId;
    }

}
