package com.kira.emercmdplat.pojo;

public class PlanType {

    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer order;
    /**
     * 父级ID
     */
    private Integer parentId;

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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

}
