package com.kira.emercmdplat.pojo;

import com.kira.emercmdplat.config.WebSecurityConfig;

public class BaseObject extends Base {

	public static final String host = WebSecurityConfig.HOST;

	protected String commonIcon;

	protected String activeIcon;

	public BaseObject() {
		this.commonIcon = this.host + WebSecurityConfig.COMMONICON;
		this.activeIcon = this.host + WebSecurityConfig.ACTIVEICON;
	}

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
