package com.kira.emercmdplat.pojo;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/7/22 15:53
 * @Description:批量绑定权限对象
 */
@Data
public class BindPermission {

    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    @NotNull(message = "绑定的权限不能为空")
    @Size(min = 1, message = "至少上传一个权限ID")
    private List<Long> permissionList;
}
