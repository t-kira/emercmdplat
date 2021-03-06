package com.kira.emercmdplat.service;

import com.kira.emercmdplat.pojo.*;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/21 23:33
 * @Description:
 */
public interface ContactService {

    int insert(Contacts contacts);

    boolean delete(Long contactId);

    boolean update(Contacts contacts);

    ContactsResult selectById(Long contactId);

    List<ContactsResult> queryForAllOrPage(Contacts contacts);

    Long queryForCounts(Contacts contacts);

    List<ContactsResult> queryForIds(List ids);

    List<ContactsResult> selectByGid(Long gid);

    List<Group> selectGroup(Group group);

    List<ContactsResult> selectGeoContacts();

    int insertGroup(Group group);

    boolean deleteGroup(Long id);

    boolean updateGroup(Group group);

    Long queryForGroupCounts(Group group);

    Group selectGroupById(Long id);

    ContactsResult selectByUserName(String username);

    TokenVO createToken(ContactsResult contacts);

    ContactsResult findByToken(String token);

    void logout(String token);

    List<Group> selectContactList();

    List<BaseData> queryAllData();
}
