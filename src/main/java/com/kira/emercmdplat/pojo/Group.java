package com.kira.emercmdplat.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/5/7 22:55
 * @Description:
 */
@Data
public class Group {

    private Long id;

    private String gName;

    private Long superGid;

    private List<ContactsResult> contactsList;

    private List<Group> groupList;
}
