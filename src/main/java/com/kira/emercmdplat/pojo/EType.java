package com.kira.emercmdplat.pojo;

import com.terran4j.commons.api2doc.annotations.ApiComment;

import lombok.Data;

@Data
public class EType {
	
	@ApiComment(value = "id", sample = "1")
	private Integer id;
	
	@ApiComment(value = "名称", sample = "aaa")
	private String name;
	
	@ApiComment(value = "数据类型id", sample = "1")
	private Integer dataId;
	
	@ApiComment(value = "小图标", sample = "aaa.png")
	private String icon;
}
