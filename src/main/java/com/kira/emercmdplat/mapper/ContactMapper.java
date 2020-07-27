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

    boolean delete(Long contactId);

    boolean update(Contacts contacts);

    ContactsResult selectById(Long contactId);

    List<ContactsResult> queryForAllOrPage(Contacts contacts);

    Long queryForCounts(Contacts contacts);

    List<ContactsResult> queryForIds(Map<String, Object> map);

    List<ContactsResult> selectByGid(Long gid);

    List<Group> selectGroup(Group group);

    List<ContactsResult> selectGeoContacts();

    Long queryForGroupCounts(Group group);

    int insertGroup(Group group);

    boolean deleteGroup(Long id);

    boolean updateGroup(Group group);

    Group selectGroupById(Long id);

    ContactsResult selectByUserName(String username);

    ContactsResult findByToken(String token);

    List<BaseData> queryAllData();
}
