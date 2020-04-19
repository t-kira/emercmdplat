package com.kira.emercmdplat.pojo;

import com.terran4j.commons.api2doc.annotations.ApiComment;

public class DataType {

	@ApiComment(value = "id", sample = "1")
	private Long id;

	@ApiComment(value = "名称", sample = "aaa")
	private String name;

	@ApiComment(value = "类型 1 资源", sample = "1")
	private Integer type;

	@ApiComment(value = "类型名称", sample = "aaa")
	private String typeName;

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

}
