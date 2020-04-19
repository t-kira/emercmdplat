package com.kira.emercmdplat.pojo;

import com.terran4j.commons.api2doc.annotations.ApiComment;

/**
 * @Author: kira
 * @Date: 2020/4/6 13:47
 * @Description:
 */
public class Duty {
	
	@ApiComment(value = "id", sample = "1")
    private Long id;
    /**
     * 值班人员姓名
     */
	@ApiComment(value = "姓名", sample = "aaa")
    private String name;
    /**
     * 性别 0：男 非0：女
     */
	@ApiComment(value = "性别 0：男 非0：女", sample = "0")
    private Integer gender;
    /**
     * 用户名
     */
	@ApiComment(value = "用户名", sample = "aaa")
    private String userName;
    /**
     * 所属机构ID
     */
	@ApiComment(value = "所属机构ID", sample = "1")
    private Long mId;
    /**
     * 职务id
     */
	@ApiComment(value = "职务id", sample = "1")
    private Long jId;
    /**
     * 联系电话
     */
	@ApiComment(value = "联系电话", sample = "1")
    private String contactNum;
    /**
     * 手机号码
     */
	@ApiComment(value = "手机号码", sample = "1")
    private String cellNum;
    /**
     * 邮箱地址
     */
	@ApiComment(value = "邮箱地址", sample = "1")
    private String email;
    /**
     * 传真号码
     */
	@ApiComment(value = "传真号码", sample = "1")
    private String faxNum;
    /**
     * 创建时间
     */
	@ApiComment(value = "创建时间", sample = "1")
    private String createTime;
    /**
     * 更新时间
     */
	@ApiComment(value = "更新时间", sample = "1")
    private String updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public Long getjId() {
        return jId;
    }

    public void setjId(Long jId) {
        this.jId = jId;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getCellNum() {
        return cellNum;
    }

    public void setCellNum(String cellNum) {
        this.cellNum = cellNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaxNum() {
        return faxNum;
    }

    public void setFaxNum(String faxNum) {
        this.faxNum = faxNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
