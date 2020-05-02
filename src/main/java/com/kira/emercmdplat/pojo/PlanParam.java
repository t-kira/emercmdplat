package com.kira.emercmdplat.pojo;

import com.terran4j.commons.api2doc.annotations.ApiComment;

public class PlanParam {

	@ApiComment(value = "id", sample = "1")
	private Integer id;
	/**
	 * 名称
	 */
	@ApiComment(value = "名称", sample = "aaa")
	private String name;
	/**
	 * 类型
	 */
	@ApiComment(value = "类型 1 数字 2 文本", sample = "1")
	private Integer type;
	/**
	 * 单位
	 */
	@ApiComment(value = "单位", sample = "人数")
	private String unit;
	/**
	 * 预案类型ID
	 */
	@ApiComment(value = "预案类型ID", sample = "1")
	private Integer ptId;

	@ApiComment(value = "参数值", sample = "aaa")
	private String value;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getPtId() {
		return ptId;
	}

	public void setPtId(Integer ptId) {
		this.ptId = ptId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
