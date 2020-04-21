package com.kira.emercmdplat.mapper;

import com.kira.emercmdplat.pojo.Contacts;
import com.kira.emercmdplat.pojo.ContactsExtend;
import com.kira.emercmdplat.pojo.ContactsResult;

import java.util.List;

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
}
