package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.mapper.ContactMapper;
import com.kira.emercmdplat.mapper.RoleMapper;
import com.kira.emercmdplat.pojo.Contacts;
import com.kira.emercmdplat.pojo.Role;
import com.kira.emercmdplat.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/7/21 18:06
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper rm;
    @Autowired
    private ContactMapper cm;

    @Override
    public int insert(Role role) {
        return rm.insert(role);
    }

    @Override
    public boolean delete(Long id) {
        Contacts contacts = new Contacts();
        contacts.setRoleId(id);
        Long count = cm.queryForCounts(contacts);
        if (count > 0)
            throw new CustomException(ResultEnum.RELATED_DATA.getNo(), "有用户已绑定该角色，无法删除");
        return rm.delete(id);
    }

    @Override
    public boolean update(Role role) {
        return rm.update(role);
    }

    @Override
    public Role selectById(Long id) {
        return rm.selectById(id);
    }

    @Override
    public List<Role> queryForAllOrPage(Role role) {
        if (role != null && role.getPage() != null) {
            role.setPage((role.getPage() - 1) * role.getPageSize());
        }
        return rm.queryForAllOrPage(role);
    }

    @Override
    public Long queryForCounts(Role role) {
        return rm.queryForCounts(role);
    }
}
