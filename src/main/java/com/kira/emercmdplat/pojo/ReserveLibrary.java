package com.kira.emercmdplat.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.terran4j.commons.api2doc.annotations.ApiComment;

/**
 * 储备库
 * 
 * @author richard.yuq
 *
 */
public class ReserveLibrary extends BaseObject {

	@ApiComment(value = "id", sample = "1")
	private Long id;
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
	 * 行政区划
	 */
	@ApiComment(value = "行政区划", sample = "aaa")
	private String district;
	/**
	 * 主管单位
	 */
	@ApiComment(value = "主管单位", sample = "aaa")
	@JsonProperty(value = "PICCompany")
	private String PICCompany;
	/**
	 * 地址
	 */
	@ApiComment(value = "地址", sample = "aaa")
	private String addr;
	/**
	 * 经度
	 */
	@ApiComment(value = "经度", sample = "1.0")
	private Double lng;
	/**
	 * 纬度
	 */
	@ApiComment(value = "纬度", sample = "1.0")
	private Double lat;
	/**
	 * 负责人
	 */
	@ApiComment(value = "负责人", sample = "aaa")
	@JsonProperty(value = "PIC")
	private String PIC;
	/**
	 * 负责人id，与通讯录关联
	 */
	@ApiComment(value = "负责人id", sample = "aaa")
	private Long contactsId;
	/**
	 * 联系电话
	 */
	@ApiComment(value = "联系电话", sample = "aaa")
	private String contactNum;
	/**
	 * 手机
	 */
	@ApiComment(value = "手机", sample = "aaa")
	private String cellNum;
	/**
	 * 住宅电话
	 */
	@ApiComment(value = "住宅电话", sample = "aaa")
	private String homeNum;
	/**
	 * 传真号码
	 */
	@ApiComment(value = "传真号码", sample = "aaa")
	private String faxNum;
	/**
	 * 电子邮箱
	 */
	@ApiComment(value = "电子邮箱", sample = "aaa")
	private String email;
	/**
	 * 联系人名字
	 */
	@ApiComment(value = "联系人名字", sample = "aaa")
	private String contactName;
	/**
	 * 联系人办公电话
	 */
	@ApiComment(value = "联系人办公电话", sample = "aaa")
	private String contactWorkNum;
	/**
	 * 联系人手机号码
	 */
	@ApiComment(value = "联系人手机号码", sample = "aaa")
	private String contactCellNum;
	/**
	 * 更新时间
	 */
	@ApiComment(value = "更新时间", sample = "aaa")
	private String updateTime;

	@ApiComment(value = "类型名称", sample = "aaa")
	private String typeName;

	@ApiComment(value = "资源分组id", sample = "1")
	private Long dataTypeId;

	protected String commonIcon = host + "/img/library-common.png";

	protected String activeIcon = host + "/img/library-active.png";

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

	public String getPICCompany() {
		return PICCompany;
	}

	public void setPICCompany(String pICCompany) {
		PICCompany = pICCompany;
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

}
