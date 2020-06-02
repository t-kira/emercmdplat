package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/4/21 22:50
 * @Description:
 */
public interface ContactMapper {

    int insert(Contacts contacts);

    boolean delete(Long id);

    boolean update(Contacts contacts);

    ContactsResult selectById(Long id);

    List<ContactsResult> queryForAll(ContactsExtend contactsExtend);

    List<ContactsResult> queryForPage(ContactsExtend contactsExtend);

    Long queryForCounts(ContactsExtend contactsExtend);

    List<ContactsResult> queryForIds(Map<String, Object> map);

    List<ContactsResult> selectByGid(Long gid);

    List<Group> selectGroup(Group group);

    List<ContactsResult> selectGeoContacts();

    int insertGroup(Group group);

    boolean deleteGroup(Long id);

    boolean updateGroup(Group group);

    Group selectGroupById(Long id);

    Contacts selectByUserName(String username);

    Contacts findByToken(String token);

    List<Permission> findPermissionsByCid(Long cid);
}
