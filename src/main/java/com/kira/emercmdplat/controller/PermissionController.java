package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.annotation.MechanismPermission;
import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.PermissionService;
import com.kira.emercmdplat.service.RoleService;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import com.kira.emercmdplat.utils.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/7/21 17:51
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService ps;
    @Autowired
    private RoleService rs;

    @ResponseBody
    @PostMapping(name = "分页查看权限列表", value = "permission_page")
    public AlvesJSONResult permissionListPage(@RequestBody(required = false) Permission permission) {
        List<Permission> list = ps.queryForAllOrPage(permission);
        Long count = ps.queryForCounts(permission);
        return AlvesJSONResult.pageOk(list, count);
    }
    @ResponseBody
    @PostMapping(name = "新增角色", value = "role_add")
    public AlvesJSONResult insertRole(@Validated @RequestBody Role role) {
        int result = rs.insert(role);
        if (result > 0)
            return AlvesJSONResult.ok("角色添加成功");
        else
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "角色添加失败");
    }
    @ResponseBody
    @GetMapping(name = "删除角色", value = "role_delete")
    public AlvesJSONResult deleteRole(@PathVariable Long roleId) {
        boolean result = rs.delete(roleId);
        if (result)
            return AlvesJSONResult.ok("角色删除成功");
        else
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "角色删除失败");
    }
    @ResponseBody
    @GetMapping(name = "查看角色详情", value = "role/{roleId}")
    public AlvesJSONResult role(@PathVariable Long roleId) {
        Role role = rs.selectById(roleId);
        return AlvesJSONResult.ok(role);
    }
    @ResponseBody
    @PostMapping(name = "修改角色信息", value = "role_update")
    public AlvesJSONResult updateRole(@RequestBody Role role) {
        boolean result = rs.update(role);
        if (result)
            return AlvesJSONResult.ok("角色修改成功");
        else
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "角色修改失败");
    }
//    @MechanismPermission(message = "角色查看")
    @ResponseBody
    @PostMapping(name = "分页查看角色列表", value = "list_role")
    public AlvesJSONResult roleList(@RequestBody(required = false) Role role) {
        List<Role> list = rs.queryForAllOrPage(role);
        Long count = rs.queryForCounts(role);
        return AlvesJSONResult.pageOk(list, count);
    }
    @ResponseBody
    @PostMapping(name = "为角色绑定权限", value = "bind_permission")
    public AlvesJSONResult insertRolePermission(@Validated @RequestBody BindPermission bindPermission) {

        int result = ps.insertRolePermission(bindPermission);
        if (result > 0)
            return AlvesJSONResult.ok("权限绑定成功");
        else
            throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "权限绑定失败");
    }
    @ResponseBody
    @GetMapping(name = "查询所有权限包括已绑定的权限", value = "all_permission/{roleId}")
    public AlvesJSONResult allPermission(@PathVariable Long roleId) {
        List<RolePermissionResult> resultList = ps.queryRoleAllPermissionByRoleId(roleId);
        List<RolePermissionResult> list = TreeUtil.treeRecursionRolePermissionDataList(resultList, 0);
        return AlvesJSONResult.ok(list);
    }
}
