package com.kira.emercmdplat.pojo;

import java.util.List;

import com.terran4j.commons.api2doc.annotations.ApiComment;

public class PlanVersion {
	
	@ApiComment(value = "id", sample = "1")
	private Integer id;
	/**
	 * 名称
	 */
	@ApiComment(value = "名称", sample = "aaa")
	private String name;
	/**
	 * 版本
	 */
	@ApiComment(value = "版本", sample = "1")
	private String version;
	/**
	 * 类型
	 */
	@ApiComment(value = "预案类型id", sample = "1")
	private Integer type;
	
	@ApiComment(value = "预案类型名称", sample = "aaa")
	private String typeName;
	/**
	 * 编码
	 */
	@ApiComment(value = "编码", sample = "1")
	private String code;
	/**
	 * 编制单位
	 */
	@ApiComment(value = "编制单位", sample = "aaa")
	private String org;
	/**
	 * 编制人
	 */
	@ApiComment(value = "编制人id", sample = "1")
	private Integer userId;
	
	@ApiComment(value = "编制人", sample = "aaa")
	private String userName;
	/**
	 * 发布时间
	 */
	@ApiComment(value = "发布时间", sample = "1")
	private String pubTime;
	/**
	 * 适用范围
	 */
	@ApiComment(value = "适用范围", sample = "1")
	private String scope;
	/**
	 * 文件
	 */
	@ApiComment(value = "文件", sample = "1")
	private String file;
	/**
	 * 参数id
	 */
	@ApiComment(value = "参数ids", sample = "1,2,3")
	private String params;
	/**
	 * 标签id
	 */
	@ApiComment(value = "标签ids", sample = "1,2,3")
	private String tags;
	/**
	 * 审核状态
	 */
	@ApiComment(value = "审核状态 0 待审核 1已审核 2未通过", sample = "0")
	private Integer status;
	/**
	 * 预案参数集合
	 */
	@ApiComment(value = "预案参数集合", sample = "[]")
	private List<PlanParam> paramList;
	/**
	 * 预案标签集合 
	 */
	@ApiComment(value = "预案标签集合", sample = "[]")
	private List<PlanTag> tagList;
	
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPubTime() {
		return pubTime;
	}
	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<PlanParam> getParamList() {
		return paramList;
	}
	public void setParamList(List<PlanParam> paramList) {
		this.paramList = paramList;
	}
	public List<PlanTag> getTagList() {
		return tagList;
	}
	public void setTagList(List<PlanTag> tagList) {
		this.tagList = tagList;
	}
	
}
