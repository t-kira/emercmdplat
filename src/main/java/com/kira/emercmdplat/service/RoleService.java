package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.Role;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/7/21 18:06
 * @Description:
 */
public interface RoleService {

    int insert(Role role);

    boolean delete(Long id);

    boolean update(Role role);

    Role selectById(Long id);

    List<Role> queryForAllOrPage(Role role);

    Long queryForCounts(Role role);
}
