package com.kira.emercmdplat.pojo;

import java.util.List;

import com.terran4j.commons.api2doc.annotations.ApiComment;

public class PlanResponseFlow {

	@ApiComment(value = "id", sample = "1")
	private Integer id;
	/**
	 * 名称
	 */
	@ApiComment(value = "名称", sample = "aaa")
	private String name;
	/**
	 * 内容
	 */
	@ApiComment(value = "内容", sample = "aaa")
	private String content;
	/**
	 * 预案组ID
	 */
	@ApiComment(value = "预案组ID", sample = "1,2,3")
	private String groupIds;
	/**
	 * 预案响应ID
	 */
	@ApiComment(value = "预案响应ID", sample = "1")
	private Integer prId;
	/**
	 * 预案组集合
	 */
	@ApiComment(value = "预案组集合", sample = "[]")
	private List<PlanGroup> groupList;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}

	public Integer getPrId() {
		return prId;
	}

	public void setPrId(Integer prId) {
		this.prId = prId;
	}

	public List<PlanGroup> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<PlanGroup> groupList) {
		this.groupList = groupList;
	}

}
