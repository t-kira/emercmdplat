package com.kira.emercmdplat.pojo;

/**
 * 医疗机构
 * @author richard.yuq
 *
 */
public class MedicalInstitution {
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
     * 维护单位
     */
    private String maintenanceCompany;
    /**
     * 机构等级
     */
    private Integer institutionLevel;
    /**
     * 唯一识别码
     */
    private String UID;
    /**
     * 病床数
     */
    private Integer sickbedNum;
    /**
     * 诊疗科目
     */
    private String medicalSubjects;
    /**
     * 地址
     */
    private String addr;
    /**
     * 经度
     */
    private Double lng;
    /**
     * 纬度
     */
    private Double lat;
    /**
     * 负责人
     */
    private String PIC;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMaintenanceCompany() {
        return maintenanceCompany;
    }

    public void setMaintenanceCompany(String maintenanceCompany) {
        this.maintenanceCompany = maintenanceCompany;
    }

    public Integer getInstitutionLevel() {
        return institutionLevel;
    }

    public void setInstitutionLevel(Integer institutionLevel) {
        this.institutionLevel = institutionLevel;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String uID) {
        UID = uID;
    }

    public Integer getSickbedNum() {
        return sickbedNum;
    }

    public void setSickbedNum(Integer sickbedNum) {
        this.sickbedNum = sickbedNum;
    }

    public String getMedicalSubjects() {
        return medicalSubjects;
    }

    public void setMedicalSubjects(String medicalSubjects) {
        this.medicalSubjects = medicalSubjects;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
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

    public String getPIC() {
        return PIC;
    }

    public void setPIC(String pIC) {
        PIC = pIC;
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
