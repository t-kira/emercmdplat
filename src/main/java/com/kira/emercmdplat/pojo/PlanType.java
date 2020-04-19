package com.kira.emercmdplat.pojo;

import com.terran4j.commons.api2doc.annotations.ApiComment;

public class PlanType {
	
	@ApiComment(value = "id", sample = "1")
    private Integer id;
    /**
     * 名称
     */
	@ApiComment(value = "名称", sample = "aaa")
    private String name;
    /**
     * 排序
     */
	@ApiComment(value = "排序", sample = "1")
    private Integer order;
    /**
     * 父级ID
     */
	@ApiComment(value = "父级ID", sample = "0")
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
