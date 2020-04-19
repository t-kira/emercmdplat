package com.kira.emercmdplat.pojo;

import java.util.List;

import com.terran4j.commons.api2doc.annotations.ApiComment;

public class PlanGroupResult {

	@ApiComment(value = "总条数", sample = "100")
	private long count;

	@ApiComment(value = "记录集合", sample = "[]", seeClass = PlanParam.class)
	private List<PlanGroup> list;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<PlanGroup> getList() {
		return list;
	}

	public void setList(List<PlanGroup> list) {
		this.list = list;
	}

}
