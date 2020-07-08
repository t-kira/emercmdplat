package com.kira.emercmdplat.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/6/1 17:14
 * @Description:权限列表
 */
@Data
public class Permission {

    private Long id;

    private String pName;

    private String path;

    private String component;

    private String meta;

    private Long parentId;

    private List<Permission> permissionList;
}
