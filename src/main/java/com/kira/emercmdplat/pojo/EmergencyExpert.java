package com.kira.emercmdplat.pojo;

import com.terran4j.commons.api2doc.annotations.ApiComment;

public class EmergencyExpert extends BaseObject {

	@ApiComment(value = "id", sample = "1")
	private Integer id;
	/**
	 * 姓名
	 */
	@ApiComment(value = "姓名", sample = "aaa")
	private String name;
	/**
	 * 类型
	 */
	@ApiComment(value = "类型", sample = "1")
	private Integer type;
	/**
	 * 单位
	 */
	@ApiComment(value = "单位", sample = "1")
	private Integer unit;
	/**
	 * 职务
	 */
	@ApiComment(value = "职务", sample = "aaa")
	private String position;
	/**
	 * 性别
	 */
	@ApiComment(value = "性别 0 男 1 女", sample = "0")
	private Integer gender;
	/**
	 * 级别
	 */
	@ApiComment(value = "级别", sample = "1")
	private Integer level;
	/**
	 * 专长
	 */
	@ApiComment(value = "专长", sample = "aaa")
	private String expertise;
	/**
	 * 电话
	 */
	@ApiComment(value = "电话", sample = "123")
	private String contactNum;
	/**
	 * 手机
	 */
	@ApiComment(value = "手机", sample = "123")
	private String cellNum;
	/**
	 * 传真
	 */
	@ApiComment(value = "传真", sample = "123")
	private String faxNum;
	/**
	 * 邮箱
	 */
	@ApiComment(value = "邮箱", sample = "123")
	private String email;
	/**
	 * 家庭电话
	 */
	@ApiComment(value = "家庭电话", sample = "123")
	private String homeNum;
	/**
	 * 家庭地址
	 */
	@ApiComment(value = "家庭地址", sample = "123")
	private String homeAddr;
	/**
	 * 补充信息
	 */
	@ApiComment(value = "补充信息", sample = "123")
	private String information;
	/**
	 * 更新时间
	 */
	@ApiComment(value = "更新时间", sample = "123")
	private String updateTime;
	/**
	 * 经度
	 */
	@ApiComment(value = "经度", sample = "1")
	private Double lng;
	/**
	 * 纬度
	 */
	@ApiComment(value = "纬度", sample = "1")
	private Double lat;
	/**
	 * 地址
	 */
	@ApiComment(value = "地址", sample = "aaa")
	private String addr;

	@ApiComment(value = "类型名称", sample = "123")
	private String typeName;

	@ApiComment(value = "单位名称", sample = "123")
	private String unitName;

	protected String commonIcon = host + "/img/expert-common.png";

	protected String activeIcon = host + "/img/expert-active.png";

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

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
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

	public String getHomeNum() {
		return homeNum;
	}

	public void setHomeNum(String homeNum) {
		this.homeNum = homeNum;
	}

	public String getHomeAddr() {
		return homeAddr;
	}

	public void setHomeAddr(String homeAddr) {
		this.homeAddr = homeAddr;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

}
