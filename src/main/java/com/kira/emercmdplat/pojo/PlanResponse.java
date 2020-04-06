package com.kira.emercmdplat.pojo;

public class PlanResponse {

    private Integer id;
    /**
     * 等级
     */
    private String level;
    /**
     * 颜色
     */
    private Integer color;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 描述
     */
    private String desc;
    /**
     * 预案版本ID
     */
    private Integer pvId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getPvId() {
        return pvId;
    }

    public void setPvId(Integer pvId) {
        this.pvId = pvId;
    }

}
