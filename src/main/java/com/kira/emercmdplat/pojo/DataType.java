package com.kira.emercmdplat.pojo;

import com.terran4j.commons.api2doc.annotations.ApiComment;

public class DataType extends BaseObject {

	@ApiComment(value = "id", sample = "1")
	private Long id;

	@ApiComment(value = "名称", sample = "aaa")
	private String name;

	@ApiComment(value = "类型 1 资源", sample = "1")
	private Integer type;

	@ApiComment(value = "类型名称", sample = "aaa")
	private String typeName;

	@ApiComment(value = "任务类型 1 任务", sample = "1")
	private Integer taskType;

	@ApiComment(value = "前端用组件名称", sample = "1")
	private String content;

	@ApiComment(value = "所属单位", sample = "aaa")
	private String maintenanceCompany;

	@ApiComment(value = "经度", sample = "1")
	private Double lng;

	@ApiComment(value = "纬度", sample = "1")
	private Double lat;

	@ApiComment(value = "手机", sample = "123")
	private String cellNum;

	@ApiComment(value = "负责人", sample = "aaa")
	private String pic;

	@ApiComment(value = "地址", sample = "aaa")
	private String addr;

	@ApiComment(value = "图标", sample = "aaa")
	private String icon;

	@ApiComment(value = "负责人id", sample = "aaa")
	private Long contactsId;

	protected String commonIcon = host + "/img/active.png";

	protected String activeIcon = host + "/img/common.png";

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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMaintenanceCompany() {
		return maintenanceCompany;
	}

	public void setMaintenanceCompany(String maintenanceCompany) {
		this.maintenanceCompany = maintenanceCompany;
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

	public String getCellNum() {
		return cellNum;
	}

	public void setCellNum(String cellNum) {
		this.cellNum = cellNum;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

}
