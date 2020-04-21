package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.mapper.ContactMapper;
import com.kira.emercmdplat.pojo.Contacts;
import com.kira.emercmdplat.pojo.ContactsExtend;
import com.kira.emercmdplat.pojo.ContactsResult;
import com.kira.emercmdplat.service.ContactService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/4/21 23:33
 * @Description:
 */
@Service
public class ContactServiceImpl implements ContactService {

    private ContactMapper cm;

    @Override
    public int insert(Contacts contacts) {
        return cm.insert(contacts);
    }

    @Override
    public boolean delete(Contacts contacts) {
        return cm.delete(contacts);
    }

    @Override
    public boolean update(Contacts contacts) {
        return cm.update(contacts);
    }

    @Override
    public ContactsResult selectById(Long id) {
        return cm.selectById(id);
    }

    @Override
    public List<ContactsResult> queryForAll(ContactsExtend contactsExtend) {
        return cm.queryForAll(contactsExtend);
    }

    @Override
    public List<ContactsResult> queryForPage(ContactsExtend contactsExtend) {
        return cm.queryForPage(contactsExtend);
    }

    @Override
    public Long queryForCounts(ContactsExtend contactsExtend) {
        return cm.queryForCounts(contactsExtend);
    }
}
