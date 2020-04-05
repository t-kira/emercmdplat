package com.kira.emercmdplat.pojo;

public class PlanGroup {
	
	private Integer id;
	/**
	 * 预案组名称
	 */
	private String name;
	/**
	 * 预案组组长
	 */
	private String leader;
	/**
	 * 预案组成员ID
	 */
	private String userIds;
	/**
	 * 预案组职责
	 */
	private String duty;
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
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public Integer getPtId() {
		return ptId;
	}
	public void setPtId(Integer ptId) {
		this.ptId = ptId;
	}

}
