package com.kira.emercmdplat.pojo;

import com.terran4j.commons.api2doc.annotations.ApiComment;

public class PlanResponseFlowTask {

	@ApiComment(value = "id", sample = "1")
	private Integer id;
	/**
	 * 名称
	 */
	@ApiComment(value = "名称", sample = "aaa")
	private String name;
	/**
	 * 描述
	 */
	@ApiComment(value = "描述", sample = "aaa")
	private String desc;
	/**
	 * 预案组ID
	 */
	@ApiComment(value = "预案组ID", sample = "1")
	private Integer groupId;
	/**
	 * 预案响应流程ID
	 */
	@ApiComment(value = "预案响应流程ID", sample = "1")
	private Integer prfId;
	/**
	 * 预案组
	 */
	@ApiComment(value = "预案组", sample = "{}")
	private PlanGroup group;

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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getPrfId() {
		return prfId;
	}

	public void setPrfId(Integer prfId) {
		this.prfId = prfId;
	}

	public PlanGroup getGroup() {
		return group;
	}

	public void setGroup(PlanGroup group) {
		this.group = group;
	}

}
