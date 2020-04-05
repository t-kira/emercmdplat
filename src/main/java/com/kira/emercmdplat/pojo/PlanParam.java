package com.kira.emercmdplat.pojo;

public class PlanParam {
	
	private Integer id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 类型
	 */
	private Integer type;
	/**
	 * 单位
	 */
	private String unit;
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

}
