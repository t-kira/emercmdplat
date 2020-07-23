package com.kira.emercmdplat.pojo;

import javax.validation.constraints.NotNull;

import com.terran4j.commons.api2doc.annotations.ApiComment;

import lombok.Data;

/**
 * 监控
 * @author richard.yuq
 *
 */
@Data
public class Monitor extends BaseObject {
	
	@ApiComment(value = "id", sample = "1")
	private Integer id;
	
	@ApiComment(value = "名称", sample = "aaa")
	@NotNull(message = "名称不能为空")
	private String name;
	
	@ApiComment(value = "地址", sample = "aaa")
	@NotNull(message = "地址不能为空")
	private String addr;
	
	@ApiComment(value = "编号", sample = "aaa")
	private String code;
	
	@ApiComment(value = "经度", sample = "1")
	@NotNull(message = "经度不能为空")
	private Double lng;
	
	@ApiComment(value = "纬度", sample = "1")
	@NotNull(message = "纬度不能为空")
	private Double lat;
	
	private String commonIcon = host + "/img/monitor-common.png";

	private String activeIcon = host + "/img/monitor-active.png";
	
}
