package com.kira.emercmdplat.pojo;

import com.terran4j.commons.api2doc.annotations.ApiComment;

import lombok.Data;

@Data
public class Monitor extends BaseObject {
	
	@ApiComment(value = "id", sample = "1")
	private Integer id;
	
	@ApiComment(value = "名称", sample = "aaa")
	private String name;
	
	@ApiComment(value = "地址", sample = "aaa")
	private String address;
	
	@ApiComment(value = "编号", sample = "aaa")
	private String code;
	
	@ApiComment(value = "经度", sample = "1")
	private Double lng;
	
	@ApiComment(value = "纬度", sample = "1")
	private Double lat;
	
}
