package com.kira.emercmdplat.pojo;

import javax.validation.constraints.NotNull;

import com.terran4j.commons.api2doc.annotations.ApiComment;

import lombok.Data;

@Data
public class EType extends Base {
	
	@ApiComment(value = "id", sample = "1")
	private Integer id;
	
	@ApiComment(value = "名称", sample = "aaa")
	@NotNull(message = "名称不能为空")
	private String name;
	
	@ApiComment(value = "数据类型id", sample = "1")
	@NotNull(message = "数据类型id不能为空")
	private Integer dataId;
	
	@ApiComment(value = "小图标", sample = "aaa.png")
	private String icon;
	
}
