package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.config.WebSecurityConfig;
import com.kira.emercmdplat.mapper.ContactMapper;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.ContactService;

import com.kira.emercmdplat.utils.DateUtil;
import com.kira.emercmdplat.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: kira
 * @Date: 2020/4/21 23:33
 * @Description:
 */
@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
    private ContactMapper cm;

    @Override
    public int insert(Contacts contacts) {
        return cm.insert(contacts);
    }

    @Override
    public boolean delete(Long id) {
        return cm.delete(id);
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
        contactsExtend.setPage((contactsExtend.getPage() - 1) * contactsExtend.getPageSize());
        return cm.queryForPage(contactsExtend);
    }

    @Override
    public Long queryForCounts(ContactsExtend contactsExtend) {
        return cm.queryForCounts(contactsExtend);
    }

	@Override
	public List<ContactsResult> queryForIds(List ids) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("ids", ids);
		return cm.queryForIds(paramMap);
	}

    @Override
    public List<ContactsResult> selectByGid(Long gid) {
        return cm.selectByGid(gid);
    }

    @Override
    public List<Group> selectGroup(Group group) {
        return cm.selectGroup(group);
    }

    @Override
    public List<ContactsResult> selectGeoContacts() {
        return cm.selectGeoContacts();
    }

    @Override
    public int insertGroup(Group group) {
        return cm.insertGroup(group);
    }

    @Override
    public boolean deleteGroup(Long id) {
        return cm.deleteGroup(id);
    }

    @Override
    public boolean updateGroup(Group group) {
        return cm.updateGroup(group);
    }

    @Override
    public Group selectGroupById(Long id) {
        return cm.selectGroupById(id);
    }
	@Override
	public ContactsResult selectByUserName(String username) {
		return cm.selectByUserName(username);
	}

    @Override
    public TokenVO createToken(Contacts contacts) {
        String token = contacts.getToken();
        //当前时间
        String now = DateUtil.getNowStr("yyyy-MM-dd HH:mm:ss");
        //过期时间
        String expireTime = DateUtil.getExpireTime(now, WebSecurityConfig.EXPIRE);
        Contacts newContact = new Contacts();
        newContact.setId(contacts.getId());
        newContact.setLoginTime(now);
        //重新生成时间
        newContact.setExpireTime(expireTime);
        //登录已过失效时间，重新生成token
        if (DateUtil.isBefore(contacts.getExpireTime())) {
            //用UUID生成token
            token = UUID.randomUUID().toString();
            newContact.setToken(token);
        }
        cm.update(newContact);
        TokenVO tokenVO = new TokenVO();
        tokenVO.setToken(token);
        tokenVO.setExpireTime(expireTime);
        return tokenVO;
    }

    @Override
    public ContactsResult findByToken(String token) {
        return cm.findByToken(token);
    }

    @Override
    public void logout(String token) {
        Contacts contacts = cm.findByToken(token);
        //用UUID生成token
        token = UUID.randomUUID().toString();
        contacts.setToken(token);
        cm.update(contacts);
    }

    @Override
    public List<Permission> findPermissionsByCid(Long cid) {
        return cm.findPermissionsByCid(cid);
    }
}
