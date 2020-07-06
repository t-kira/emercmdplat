package com.kira.emercmdplat.pojo;

import com.terran4j.commons.api2doc.annotations.ApiComment;

/**
 * 预案标签
 * @author richard.yuq
 *
 */
public class PlanTag {

	@ApiComment(value = "id", sample = "1")
	private Integer id;
    /**
     * 名称
     */
	@ApiComment(value = "名称", sample = "aaa")
    private String name;
    /**
     * 预案类型ID
     */
	@ApiComment(value = "预案类型ID", sample = "1")
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
