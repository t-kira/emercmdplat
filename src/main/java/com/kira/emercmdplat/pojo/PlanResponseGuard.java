package com.kira.emercmdplat.pojo;

public class PlanResponseGuard {

    private Integer id;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 保障名称
     */
    private String name;
    /**
     * 保障描述
     */
    private String desc;
    /**
     * 资源项json
     */
    private String res;
    /**
     * 父级ID
     */
    private Integer parentId;
    /**
     * 预案响应ID
     */
    private Integer prId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getPrId() {
        return prId;
    }

    public void setPrId(Integer prId) {
        this.prId = prId;
    }

}
