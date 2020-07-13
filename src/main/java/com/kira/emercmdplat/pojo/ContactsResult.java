package com.kira.emercmdplat.pojo;

import com.terran4j.commons.api2doc.annotations.ApiComment;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/21 22:51
 * @Description:
 */
public class ContactsResult extends Contacts{

	@ApiComment(value = "职务名称", sample = "aaa")
    private String jName;

	@ApiComment(value = "工作单位名称", sample = "aaa")
    private String mName;

	@ApiComment(value = "分组名称", sample = "aaa")
    private String gName;

    public String getjName() {
        return jName;
    }

    public void setjName(String jName) {
        this.jName = jName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }
}
