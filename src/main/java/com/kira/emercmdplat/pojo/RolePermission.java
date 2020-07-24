package com.kira.emercmdplat.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: kira
 * @Date: 2020/7/22 09:16
 * @Description:角色权限
 */
@Data
public class RolePermission{

    private Long id;
    @NotNull(message = "角色ID不能为空")
    private Long roleId;
    @NotNull(message = "权限ID不能为空")
    private Long permissionId;
}
