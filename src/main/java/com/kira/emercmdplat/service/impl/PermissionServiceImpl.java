package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.PermissionMapper;
import com.kira.emercmdplat.mapper.RolePermissionMapper;
import com.kira.emercmdplat.pojo.BindPermission;
import com.kira.emercmdplat.pojo.Permission;
import com.kira.emercmdplat.pojo.RolePermission;
import com.kira.emercmdplat.pojo.RolePermissionResult;
import com.kira.emercmdplat.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/7/21 17:56
 * @Description:
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper pm;
    @Autowired
    private RolePermissionMapper rpm;

    @Override
    public List<Permission> queryForAllOrPage(Permission permission) {
        if (permission != null && permission.getPage() != null) {
            permission.setPage((permission.getPage() - 1) * permission.getPageSize());
        }
        return pm.queryForAllOrPage(permission);
    }

    @Override
    public Long queryForCounts(Permission permission) {
        return pm.queryForCounts(permission);
    }

    @Transactional
    @Override
    public int insertRolePermission(BindPermission bindPermission) {
        rpm.deleteByRoleId(bindPermission.getRoleId());
        for (Long permissionId : bindPermission.getPermissionList()) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(bindPermission.getRoleId());
            rolePermission.setPermissionId(permissionId);
            rpm.insert(rolePermission);
        }
        return 1;
    }

    @Override
    public List<Permission> findPermissionsByCid(Long cid) {
        return pm.findPermissionsByCid(cid);
    }

    @Override
    public List<RolePermissionResult> queryRoleAllPermissionByRoleId(Long roleId) {
        return rpm.queryRoleAllPermissionByRoleId(roleId);
    }
}
