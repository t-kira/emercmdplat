package com.kira.emercmdplat.pojo;

import java.util.List;

import com.terran4j.commons.api2doc.annotations.ApiComment;

public class PlanResponse {

	@ApiComment(value = "id", sample = "1")
	private Integer id;

	@ApiComment(value = "0 预警响应/1 应急响应", sample = "0")
	private Integer form;
	/**
	 * 等级
	 */
	@ApiComment(value = "等级", sample = "一级响应")
	private String level;
	/**
	 * 颜色
	 */
	@ApiComment(value = "颜色1 蓝 2 黄 3 橙 4 红", sample = "1")
	private Integer color;
	/**
	 * 类型
	 */
	@ApiComment(value = "类型 1 普通 2 一般 3 较大 4重大 5 特别重大", sample = "1")
	private Integer type;
	/**
	 * 描述
	 */
	@ApiComment(value = "描述", sample = "aaa")
	private String desc;

	@ApiComment(value = "参数值集合json", sample = "[{id:1,value:'aaa'}]")
	private String params;
	/**
	 * 预案ID
	 */
	@ApiComment(value = "预案ID", sample = "1")
	private Integer pvId;

	@ApiComment(value = "参数值集合list", sample = "[{id:1,name='aaa',value='bbb'}]")
	private List<PlanParam> paramList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getForm() {
		return form;
	}

	public void setForm(Integer form) {
		this.form = form;
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

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public List<PlanParam> getParamList() {
		return paramList;
	}

	public void setParamList(List<PlanParam> paramList) {
		this.paramList = paramList;
	}

}
