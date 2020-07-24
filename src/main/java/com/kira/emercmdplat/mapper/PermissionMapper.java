package com.kira.emercmdplat.mapper;
import com.kira.emercmdplat.pojo.Permission;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/7/21 17:54
 * @Description:
 */
public interface PermissionMapper {

    List<Permission> queryForAllOrPage(Permission permission);

    Long queryForCounts(Permission permission);

    List<Permission> findPermissionsByCid(Long cid);
}
