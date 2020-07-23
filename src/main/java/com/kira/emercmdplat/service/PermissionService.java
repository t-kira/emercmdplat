package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.BindPermission;
import com.kira.emercmdplat.pojo.Permission;
import com.kira.emercmdplat.pojo.RolePermission;
import com.kira.emercmdplat.pojo.RolePermissionResult;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/7/21 17:55
 * @Description:
 */
public interface PermissionService {

    List<Permission> queryForAllOrPage(Permission permission);

    Long queryForCounts(Permission permission);

    int insertRolePermission(BindPermission bindPermission);

    List<Permission> findPermissionsByCid(Long cid);

    List<RolePermissionResult> queryRoleAllPermissionByRoleId(Long roleId);
}
