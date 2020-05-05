package com.kira.emercmdplat.pojo;

import com.terran4j.commons.api2doc.annotations.ApiComment;

/**
 * 应急物资
 * @author richard.yuq
 *
 */
public class EmergencySupply extends BaseObject {
	
	@ApiComment(value = "id", sample = "1")
    private Integer id;
    /**
     * 名称
     */
	@ApiComment(value = "名称", sample = "aaa")
    private String name;
    /**
     * 类型
     */
	@ApiComment(value = "类型", sample = "1")
    private Integer type;
    /**
     * 计量单位
     */
	@ApiComment(value = "计量单位", sample = "aaa")
    private String measurementUnit;
    /**
     * 数量
     */
	@ApiComment(value = "数量", sample = "1")
    private Integer supplyNum;
    /**
     * 来源
     */
	@ApiComment(value = "来源", sample = "1")
    private Integer supplySource;
    /**
     * 存储点
     */
	@ApiComment(value = "存储点", sample = "1")
    private Integer storagePoint;
    /**
     * 联系人
     */
	@ApiComment(value = "联系人", sample = "aaa")
    private String contactName;
    /**
     * 联系电话
     */
	@ApiComment(value = "联系电话", sample = "aaa")
    private String contactNum;
    /**
     * 单价
     */
	@ApiComment(value = "单价", sample = "100.00")
    private Double unitPrice;
    /**
     * 维护单位
     */
	@ApiComment(value = "维护单位", sample = "aaa")
    private String maintenanceCompany;
    /**
     * 备注
     */
	@ApiComment(value = "备注", sample = "aaa")
    private String remark;
	
	@ApiComment(value = "类型名称", sample = "aaa")
	private String typeName;
	
	@ApiComment(value = "来源名称", sample = "aaa")
	private String sourceName;

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
