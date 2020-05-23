package com.kira.emercmdplat.pojo;

import com.terran4j.commons.api2doc.annotations.ApiComment;

public class BaseObject {
	
	public static final String host = "http://47.93.126.142:8989";
	
	protected String commonIcon = host + "/img/active.png";
	
	protected String activeIcon = host + "/img/common.png";
	
	@ApiComment("第几页")
	private Integer page;
	
	@ApiComment("每页显示条数")
	private Integer pageSize; 

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

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
