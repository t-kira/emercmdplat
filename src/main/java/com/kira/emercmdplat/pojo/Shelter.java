package com.kira.emercmdplat.pojo;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kira.emercmdplat.annotation.ContactNum;
import com.kira.emercmdplat.annotation.Phone;
import com.terran4j.commons.api2doc.annotations.ApiComment;

/**
 * 避难场所
 * 
 * @author richard.yuq
 *
 */
public class Shelter extends BaseObject {

	@ApiComment(value = "id", sample = "1")
	private Long id;
	/**
	 * 名称
	 */
	@ApiComment(value = "名称", sample = "aaa")
	@NotNull(message = "名称不能为空")
	private String name;
	/**
	 * 类型
	 */
	@ApiComment(value = "类型", sample = "1")
	@NotNull(message = "类型不能为空")
	private Integer type;
	/**
	 * 行政区划
	 */
	@ApiComment(value = "行政区划", sample = "aaa")
	@NotNull(message = "行政区划不能为空")
	private String district;
	/**
	 * 维护单位
	 */
	@ApiComment(value = "维护单位", sample = "aaa")
	@NotNull(message = "维护单位不能为空")
	private String maintenanceCompany;
	/**
	 * 级别
	 */
	@ApiComment(value = "级别", sample = "1")
	@NotNull(message = "级别不能为空")
	@DecimalMin(value = "1", message = "级别必须大于0")
	@DecimalMax(value = "10", message = "级别必须小于10")
	private Integer level;
	/**
	 * 面积
	 */
	@ApiComment(value = "面积", sample = "1")
	@NotNull(message = "面积不能为空")
	@DecimalMin(value = "0", message = "面积必须大于0")
	private Double area;
	/**
	 * 容纳人数
	 */
	@ApiComment(value = "容纳人数", sample = "1")
	@NotNull(message = "容纳人数不能为空")
	@DecimalMin(value = "0", message = "容纳人数必须大于0")
	private Integer capacity;
	/**
	 * 地址
	 */
	@ApiComment(value = "地址", sample = "aaa")
	@NotNull(message = "地址不能为空")
	private String addr;
	/**
	 * 经度
	 */
	@ApiComment(value = "经度", sample = "1")
	@NotNull(message = "经度不能为空")
	private Double lng;
	/**
	 * 纬度
	 */
	@ApiComment(value = "纬度", sample = "1")
	@NotNull(message = "纬度不能为空")
	private Double lat;
	/**
	 * 负责人
	 */
	@ApiComment(value = "负责人", sample = "aaa")
	@JsonProperty(value = "PIC")
	@NotNull(message = "负责人不能为空")
	private String PIC;
	/**
	 * 负责人id，与通讯录关联
	 */
	@ApiComment(value = "负责人id", sample = "aaa")
	private Long contactsId;
	/**
	 * 联系电话
	 */
	@ApiComment(value = "联系电话", sample = "123")
	@ContactNum(message = "联系电话格式不正确")
	private String contactNum;
	/**
	 * 手机
	 */
	@ApiComment(value = "手机", sample = "123")
	@NotNull(message = "手机不能为空")
	@Phone
	private String cellNum;
	/**
	 * 住宅电话
	 */
	@ApiComment(value = "住宅电话", sample = "123")
	@ContactNum(message = "住宅电话格式不正确")
	private String homeNum;
	/**
	 * 传真号码
	 */
	@ApiComment(value = "传真号码", sample = "123")
	@ContactNum(message = "传真号码格式不正确")
	private String faxNum;
	/**
	 * 电子邮箱
	 */
	@ApiComment(value = "电子邮箱", sample = "123")
	@Email
	private String email;
	/**
	 * 联系人名字
	 */
	@ApiComment(value = "联系人名字", sample = "aaa")
	private String contactName;
	/**
	 * 联系人办公电话
	 */
	@ApiComment(value = "联系人办公电话", sample = "123")
	@ContactNum(message = "联系人办公电话格式不正确")
	private String contactWorkNum;
	/**
	 * 联系人手机号码
	 */
	@ApiComment(value = "联系人手机号码", sample = "123")
	@Phone
	private String contactCellNum;
	/**
	 * 更新时间
	 */
	@ApiComment(value = "更新时间", sample = "2020-05-01")
	private String updateTime;

	@ApiComment(value = "类型名称", sample = "aaa")
	private String typeName;

	@ApiComment(value = "资源分组id", sample = "1")
	private Long dataTypeId;

	@ApiComment(value = "资源分组名称", sample = "1")
	private String dataTypeName;

	protected String commonIcon = host + "/img/shelter-common.png";

	protected String activeIcon = host + "/img/shelter-active.png";

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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
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

	public String getCommonIcon() {
		return commonIcon;
	}

	public void setCommonIcon(String commonIcon) {
		this.commonIcon = commonIcon;
	}

	public String getActiveIcon() {
		return activeIcon;
	}

	public void setActiveIcon(String activeIcon) {
		this.activeIcon = activeIcon;
	}

	public Long getContactsId() {
		return contactsId;
	}

	public void setContactsId(Long contactsId) {
		this.contactsId = contactsId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getDataTypeId() {
		return dataTypeId;
	}

	public void setDataTypeId(Long dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

	public String getDataTypeName() {
		return dataTypeName;
	}

	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}

}
