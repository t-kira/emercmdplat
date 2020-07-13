package com.kira.emercmdplat.pojo;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.terran4j.commons.api2doc.annotations.ApiComment;

/**
 * 预案组
 * @author richard.yuq
 *
 */
public class PlanGroup extends BaseObject {

	@ApiComment(value = "id", sample = "1")
    private Integer id;
    /**
     * 预案组名称
     */
	@ApiComment(value = "预案组名称", sample = "aaa")
	@NotNull(message = "名称不能为空")
    private String name;
    /**
     * 预案组组长
     */
	@ApiComment(value = "预案组组长", sample = "aaa")
	@NotNull(message = "组长不能为空")
    private String leader;
    /**
     * 预案组成员ID
     */
	@ApiComment(value = "预案组成员json(type 0 通讯录 1 指挥架构)", sample = "[{type:1,id:1}]")
    private String userIds;
    /**
     * 预案组职责
     */
	@ApiComment(value = "预案组职责", sample = "aaa")
    private String duty;
    /**
     * 预案类型ID
     */
	@ApiComment(value = "预案类型ID", sample = "1")
    private Integer ptId;
	/**
	 * 预案组成员集合
	 */
	@ApiComment(value = "预案组成员集合", sample = "[{type:1,typeName:'aaa',id:1,name:'bbb'}]")
	private List<DataType> userList;

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

	public List<DataType> getUserList() {
		return userList;
	}

	public void setUserList(List<DataType> userList) {
		this.userList = userList;
	}

}
