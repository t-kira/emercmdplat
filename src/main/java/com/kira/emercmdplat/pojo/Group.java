package com.kira.emercmdplat.pojo;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/5/7 22:55
 * @Description:
 */
public class Group {

    private Long id;

    private String gName;

    private Long superGid;

    private List<ContactsResult> contactsList;

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
