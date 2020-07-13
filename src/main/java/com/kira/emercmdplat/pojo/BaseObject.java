package com.kira.emercmdplat.pojo;

public class BaseObject extends Base {

	public static final String host = "https://www.chinahqd.cn:9090";

	protected String commonIcon = host + "/img/active.png";

	protected String activeIcon = host + "/img/common.png";

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

}
