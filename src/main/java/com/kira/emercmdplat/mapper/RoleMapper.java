package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.pojo.Role;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/8 17:48
 * @Description:
 */
public interface RoleMapper {

    int insert(Role role);

    boolean delete(Long id);

    boolean update(Role role);

    Role selectById(Long id);

    List<Role> queryForAllOrPage(Role role);

    Long queryForCounts(Role role);
}
