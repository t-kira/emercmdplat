package com.kira.emercmdplat.pojo;

public class PlanOrg {
	
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
	 * 职责
	 */
	private String duty;
	/**
	 * 成员ID
	 */
	private String userIds;
	/**
	 * 父级ID
	 */
	private Integer parentId;
	/**
	 * 预案版本ID
	 */
	private Integer pvId;
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
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getPvId() {
		return pvId;
	}
	public void setPvId(Integer pvId) {
		this.pvId = pvId;
	}

}
