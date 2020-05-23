package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.pojo.Contacts;
import com.kira.emercmdplat.pojo.ContactsExtend;
import com.kira.emercmdplat.pojo.ContactsResult;
import com.kira.emercmdplat.pojo.Group;

import java.util.List;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/4/21 22:50
 * @Description:
 */
public interface ContactMapper {

    int insert(Contacts contacts);

    boolean delete(Contacts contacts);

    boolean update(Contacts contacts);

    ContactsResult selectById(Long id);

    List<ContactsResult> queryForAll(ContactsExtend contactsExtend);

    List<ContactsResult> queryForPage(ContactsExtend contactsExtend);

    Long queryForCounts(ContactsExtend contactsExtend);

    List<ContactsResult> queryForIds(Map<String, Object> map);

    List<ContactsResult> selectByGid(Long gid);

    List<Group> selectGroup(Group group);

    List<ContactsResult> selectGeoContacts();
    
    ContactsResult selectByUserName(String username);
}
