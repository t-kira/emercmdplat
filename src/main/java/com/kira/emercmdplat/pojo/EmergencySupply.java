package com.kira.emercmdplat.pojo;

/**
 * 应急物资
 * @author richard.yuq
 *
 */
public class EmergencySupply {
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
     * 计量单位
     */
    private String measurementUnit;
    /**
     * 数量
     */
    private Integer supplyNum;
    /**
     * 来源
     */
    private Integer supplySource;
    /**
     * 存储点
     */
    private Integer storagePoint;
    /**
     * 联系人
     */
    private String contactName;
    /**
     * 联系电话
     */
    private String contactNum;
    /**
     * 单价
     */
    private Double unitPrice;
    /**
     * 维护单位
     */
    private String maintenanceCompany;
    /**
     * 备注
     */
    private String remark;

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

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public Integer getSupplyNum() {
        return supplyNum;
    }

    public void setSupplyNum(Integer supplyNum) {
        this.supplyNum = supplyNum;
    }

    public Integer getSupplySource() {
        return supplySource;
    }

    public void setSupplySource(Integer supplySource) {
        this.supplySource = supplySource;
    }

    public Integer getStoragePoint() {
        return storagePoint;
    }

    public void setStoragePoint(Integer storagePoint) {
        this.storagePoint = storagePoint;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getMaintenanceCompany() {
        return maintenanceCompany;
    }

    public void setMaintenanceCompany(String maintenanceCompany) {
        this.maintenanceCompany = maintenanceCompany;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
