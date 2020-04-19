package com.kira.emercmdplat.pojo;

import java.util.List;

import com.terran4j.commons.api2doc.annotations.ApiComment;

public class PlanParamResult {
	
	@ApiComment(value = "总条数", sample = "100")
	private long count;
	
	@ApiComment(value = "记录集合", sample = "[]", seeClass=PlanParam.class)
	private List<PlanParam> list;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<PlanParam> getList() {
		return list;
	}

	public void setList(List<PlanParam> list) {
		this.list = list;
	}

}
