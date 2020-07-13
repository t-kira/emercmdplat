package com.kira.emercmdplat.pojo;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.terran4j.commons.api2doc.annotations.ApiComment;
/**
 * 预案应急资源保障
 * @author richard.yuq
 *
 */
public class PlanResponseGuard {

	@ApiComment(value = "id", sample = "1")
	private Integer id;
	/**
	 * 类型
	 */
	@ApiComment(value = "类型1 保障项 2 资源项", sample = "1")
	@NotNull(message = "类型不能为空")
	private Integer type;
	/**
	 * 保障名称
	 */
	@ApiComment(value = "保障名称", sample = "aaa")
	@NotNull(message = "保障名称不能为空")
	private String name;
	/**
	 * 保障描述
	 */
	@ApiComment(value = "保障描述", sample = "aaa")
	private String desc;
	/**
	 * 资源项json
	 */
	@ApiComment(value = "资源项json", sample = "[{type:1,id:1}]")
	private String res;

	@ApiComment(value = "资源项节点名", sample = "aaa-bbb")
	private String resName;
	/**
	 * 父级ID
	 */
	@ApiComment(value = "父级ID", sample = "1")
	private Integer parentId;
	/**
	 * 预案响应ID
	 */
	@ApiComment(value = "预案响应ID", sample = "1")
	@NotNull(message = "预案响应ID不能为空")
	private Integer prId;
	/**
	 * 资源项列表
	 */
	@ApiComment(value = "资源项json", sample = "[{type:1,typeName:'aaa',id:1,name:'bbb'}]")
	private List<DataType> resList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getPrId() {
		return prId;
	}

	public void setPrId(Integer prId) {
		this.prId = prId;
	}

	public List<DataType> getResList() {
		return resList;
	}

	public void setResList(List<DataType> resList) {
		this.resList = resList;
	}

}
