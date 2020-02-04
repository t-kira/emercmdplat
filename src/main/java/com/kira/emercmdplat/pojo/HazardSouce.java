package com.kira.emercmdplat.pojo;

/**
 * @Author: kira
 * @Date: 2020/2/1 11:01
 * @Description:
 */
public class HazardSouce {

    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 行政区划
     */
    private String district;
    /**
     * 威胁人数
     */
    private Integer number;
    /**
     * 负责人
     */
    private String PIC;
    /**
     * 地址
     */
    private String addr;
    /**
     * 维护单位
     */
    private String maintenanceCompany;
    /**
     * 危险等级
     */
    private Integer warningLevel;
    /**
     * 经度
     */
    private Double lng;
    /**
     * 纬度
     */
    private Double lat;
    /**
     * 联系电话
     */
    private String contactNum;
    /**
     * 手机
     */
    private String cellNum;
    /**
     * 住宅电话
     */
    private String homeNum;
    /**
     * 传真号码
     */
    private String faxNum;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 联系人名字
     */
    private String contactName;
    /**
     * 联系人办公电话
     */
    private String contactWorkNum;
    /**
     * 联系人手机号码
     */
    private String contactCellNum;
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



    public String getDistrict() {
        return district;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPIC() {
        return PIC;
    }

    public void setPIC(String PIC) {
        this.PIC = PIC;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getMaintenanceCompany() {
        return maintenanceCompany;
    }

    public void setMaintenanceCompany(String maintenanceCompany) {
        this.maintenanceCompany = maintenanceCompany;
    }

    public Integer getWarningLevel() {
        return warningLevel;
    }

    public void setWarningLevel(Integer warningLevel) {
        this.warningLevel = warningLevel;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
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

    public String getHomeNum() {
        return homeNum;
    }

    public void setHomeNum(String homeNum) {
        this.homeNum = homeNum;
    }

    public String getFaxNum() {
        return faxNum;
    }

    public void setFaxNum(String faxNum) {
        this.faxNum = faxNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactWorkNum() {
        return contactWorkNum;
    }

    public void setContactWorkNum(String contactWorkNum) {
        this.contactWorkNum = contactWorkNum;
    }

    public String getContactCellNum() {
        return contactCellNum;
    }

    public void setContactCellNum(String contactCellNum) {
        this.contactCellNum = contactCellNum;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
