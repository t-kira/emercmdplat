package com.kira.emercmdplat.pojo;

import java.util.List;

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
	 * 任务成员json
	 */
	@ApiComment(value = "任务成员json", sample = "[{type:1,id:1}]")
	private String groupId;
	/**
	 * 预案响应流程ID
	 */
	@ApiComment(value = "预案响应流程ID", sample = "1")
	private Integer prfId;

	@ApiComment(value = "任务成员集合", sample = "[{type:1,typeName:'aaa',id:1,name:'bbb'}]")
	private List<DataType> groupList;

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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Integer getPrfId() {
		return prfId;
	}

	public void setPrfId(Integer prfId) {
		this.prfId = prfId;
	}

	public List<DataType> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<DataType> groupList) {
		this.groupList = groupList;
	}

}
