package com.kira.emercmdplat.pojo;

import java.util.List;

import com.terran4j.commons.api2doc.annotations.ApiComment;

/**
 * @Author: kira
 * @Date: 2020/5/7 22:55
 * @Description:
 */
public class Group {
	
	@ApiComment(value = "id", sample = "1")
    private Long id;
    
    @ApiComment(value = "分组名", sample = "aaa")
    private String gName;
    
    @ApiComment(value = "父组id", sample = "1")
    private Long superGid;

    @ApiComment(value = "联系人列表", sample = "[]")
    private List<ContactsResult> contactsList;
    
    @ApiComment(value = "分组列表", sample = "[]")
    private List<Group> groupList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public Long getSuperGid() {
        return superGid;
    }

    public void setSuperGid(Long superGid) {
        this.superGid = superGid;
    }

    public List<ContactsResult> getContactsList() {
        return contactsList;
    }

    public void setContactsList(List<ContactsResult> contactsList) {
        this.contactsList = contactsList;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }
}
