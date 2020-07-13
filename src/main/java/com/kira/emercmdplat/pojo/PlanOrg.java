package com.kira.emercmdplat.pojo;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.terran4j.commons.api2doc.annotations.ApiComment;

/**
 * 预案组织架构
 * @author richard.yuq
 *
 */
public class PlanOrg {

	@ApiComment(value = "id", sample = "1")
    private Integer id;
    /**
     * 名称
     */
	@ApiComment(value = "名称", sample = "aaa")
	@NotNull(message = "名称不能为空")
    private String name;
    /**
     * 类型
     */
	@ApiComment(value = "类型1 职位 2 部门", sample = "1")
	@NotNull(message = "类型不能为空")
    private Integer type;
    /**
     * 职责
     */
	@ApiComment(value = "职责", sample = "aaa")
	@NotNull(message = "职责不能为空")
    private String duty;
    /**
     * 成员ID
     */
	@ApiComment(value = "成员ID", sample = "1,2,3")
    private String userIds;
    /**
     * 父级ID
     */
	@ApiComment(value = "父级ID", sample = "1")
	@NotNull(message = "父级ID不能为空")
    private Integer parentId;
    /**
     * 预案ID
     */
	@ApiComment(value = "预案ID", sample = "1")
	@NotNull(message = "预案ID不能为空")
    private Integer pvId;
	/**
	 * 成员集合
	 */
	@ApiComment(value = "成员集合", sample = "[]")
	private List<ContactsResult> userList;

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

	public List<ContactsResult> getUserList() {
		return userList;
	}

	public void setUserList(List<ContactsResult> userList) {
		this.userList = userList;
	}

}
