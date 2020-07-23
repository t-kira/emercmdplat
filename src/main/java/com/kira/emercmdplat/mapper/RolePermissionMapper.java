package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.pojo.RolePermission;
import com.kira.emercmdplat.pojo.RolePermissionResult;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/7/21 17:54
 * @Description:
 */
public interface RolePermissionMapper {

    int insert(RolePermission rolePermission);

    boolean deleteByRoleId(Long roleId);

    List<RolePermissionResult> queryRoleAllPermissionByRoleId(Long roleId);

}
