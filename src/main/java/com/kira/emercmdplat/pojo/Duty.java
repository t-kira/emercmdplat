package com.kira.emercmdplat.pojo;

/**
 * @Author: kira
 * @Date: 2020/4/6 13:47
 * @Description:
 */
public class Duty {

    private Long id;
    /**
     * 值班人员姓名
     */
    private String name;
    /**
     * 性别 0：男 非0：女
     */
    private Integer gender;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 所属机构ID
     */
    private Long mId;
    /**
     * 职务id
     */
    private Long jId;
    /**
     * 联系电话
     */
    private String contactNum;
    /**
     * 手机号码
     */
    private String cellNum;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 传真号码
     */
    private String faxNum;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
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
