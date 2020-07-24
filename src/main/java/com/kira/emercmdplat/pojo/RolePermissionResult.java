package com.kira.emercmdplat.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/7/22 09:46
 * @Description:
 */
@Data
public class RolePermissionResult extends RolePermission{

    private String permissionName;

    private Long parentId;

    private List<RolePermissionResult> rolePermissionList;
}
