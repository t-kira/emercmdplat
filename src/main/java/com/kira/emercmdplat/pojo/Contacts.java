package com.kira.emercmdplat.pojo;

import com.terran4j.commons.api2doc.annotations.ApiComment;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: kira
 * @Date: 2020/4/21 22:26
 * @Description:
 */
//@Data
public class Contacts extends BaseObject {

    private Long id;
    @ApiComment(value = "姓名", sample = "张三")
    private String contactName;
    /**
     * 性别 0：男 非0：女
     */
    @ApiComment(value = "性别", sample = "0")
    private Integer gender;
    @ApiComment(value = "职务ID", sample = "1")
    private Long jId;
    @ApiComment(value = "工作单位ID", sample = "1")
    private Long mId;
    @ApiComment(value = "手机号码", sample = "18223457654")
    private String telephone;
    @ApiComment(value = "备用手机", sample = "17523126754")
    private String backupPhone;
    @ApiComment(value = "手持终端", sample = "iphone xs")
    private String terminal;
    @ApiComment(value = "办公电话", sample = "0571-86432347")
    private String workPhone;
    @ApiComment(value = "家庭电话", sample = "0571-85463242")
    private String homePhone;
    @ApiComment(value = "其他电话", sample = "15623749876")
    private String otherPhone;
    @ApiComment(value = "传真号码", sample = "0571-86574327")
    private String faxNumber;
    @ApiComment(value = "电子邮箱", sample = "kk@126.com")
    private String email;
    /**
     * 0：一般 1：紧急
     */
    @ApiComment(value = "重要程度", sample = "0")
    private Integer importanceDegree;
    @ApiComment(value = "照片地址", sample = "/usr/static/2.jpg")
    private String photo;
    @ApiComment(value = "备注", sample = "这是一条备注信息")
    private String notes;
    @ApiComment(value = "分组ID", sample = "1")
    private Long gId;
    @ApiComment(value = "纬度", sample = "23.0001")
    private Double lat;
    @ApiComment(value = "经度", sample = "23.0001")
    private Double lng;
    @ApiComment(value = "添加时间", sample = "2020-04-21 22:44:40")
    private String createTime;
    @ApiComment(value = "添加时间", sample = "2020-04-22 22:44:40")
    private String updateTime;
    /**
     * 0：非值班人员 1：值班人员
     */
    @ApiComment(value = "人物属性", sample = "0")
    private Integer personAttribute;
    @ApiComment(value = "登录用户名", sample = "aaa")
    private String username;
    @ApiComment(value = "登录密码", sample = "123")
    private String password;

    private String commonIcon = host + "/img/contacts-common.png";

	private String activeIcon = host + "/img/contacts-active.png";

	private String loginTime;

	private String expireTime;

	private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Long getjId() {
        return jId;
    }

    public void setjId(Long jId) {
        this.jId = jId;
    }

    public Long getmId() {
        return mId;
    }

    public void setmId(Long mId) {
        this.mId = mId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBackupPhone() {
        return backupPhone;
    }

    public void setBackupPhone(String backupPhone) {
        this.backupPhone = backupPhone;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getOtherPhone() {
        return otherPhone;
    }

    public void setOtherPhone(String otherPhone) {
        this.otherPhone = otherPhone;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getImportanceDegree() {
        return importanceDegree;
    }

    public void setImportanceDegree(Integer importanceDegree) {
        this.importanceDegree = importanceDegree;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getgId() {
        return gId;
    }

    public void setgId(Long gId) {
        this.gId = gId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
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

    public Integer getPersonAttribute() {
        return personAttribute;
    }

    public void setPersonAttribute(Integer personAttribute) {
        this.personAttribute = personAttribute;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getCommonIcon() {
        return commonIcon;
    }

    @Override
    public void setCommonIcon(String commonIcon) {
        this.commonIcon = commonIcon;
    }

    @Override
    public String getActiveIcon() {
        return activeIcon;
    }

    @Override
    public void setActiveIcon(String activeIcon) {
        this.activeIcon = activeIcon;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
