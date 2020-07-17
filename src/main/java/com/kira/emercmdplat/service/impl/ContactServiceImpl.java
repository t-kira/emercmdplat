package com.kira.emercmdplat.service.impl;

import com.kira.emercmdplat.config.WebSecurityConfig;
import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.exception.CustomException;
import com.kira.emercmdplat.mapper.ContactMapper;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.ContactService;

import com.kira.emercmdplat.utils.*;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

	@Autowired
    private ContactMapper cm;

    @Override
    public int insert(Contacts contacts) {
        return cm.insert(contacts);
    }

    @Override
    public boolean delete(Long contactId) {
        return cm.delete(contactId);
    }

    @Override
    public boolean update(Contacts contacts) {
        return cm.update(contacts);
    }

    @Override
    public ContactsResult selectById(Long contactId) {
        return cm.selectById(contactId);
    }

    @Override
    public List<ContactsResult> queryForAllOrPage(Contacts contacts) {
        if (contacts != null && contacts.getPage() != null) {
            contacts.setPage((contacts.getPage() - 1) * contacts.getPageSize());
        }
        return cm.queryForAllOrPage(contacts);
    }

    @Override
    public Long queryForCounts(Contacts contacts) {
        return cm.queryForCounts(contacts);
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
        if (group.getPage() != null) {
            group.setPage((group.getPage() - 1) * group.getPageSize());
        }
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
    public TokenVO createToken(ContactsResult contacts) {
        String token = contacts.getToken();
        //当前时间
        String now = DateUtil.getNowStr();
        //过期时间
        String expireTime = DateUtil.getExpireTime(now, WebSecurityConfig.EXPIRE);
        Contacts newContact = new Contacts();
        newContact.setId(contacts.getId());
        newContact.setLoginTime(now);
        //重新生成时间
        newContact.setExpireTime(expireTime);
        //登录已过失效时间，重新生成token
        if (StringUtil.isEmpty(token)) {
            token = UUID.randomUUID().toString();
            newContact.setToken(token);
        } else {
            if (DateUtil.isBefore(contacts.getExpireTime())) {
                //用UUID生成token
                token = UUID.randomUUID().toString();
                newContact.setToken(token);
            }
        }
        String rongCloudResult = TokenUtil.getRongCloudToken(contacts);
        if (!StringUtil.isEmpty(rongCloudResult)) {
            JSONObject resultJson = JSONObject.fromObject(rongCloudResult);
            int code = resultJson.getInt("code");
            if (code == 200) {
                newContact.setRongToken(resultJson.getString("token"));
            } else {
                logger.error(resultJson.getString("errorMessage"));
                throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "融云token获取失败");
            }
        }
        logger.error("用户Id：" + contacts.getId() + ",用户名：" + contacts.getUsername() +",融云token:" + newContact.getRongToken());
        cm.update(newContact);
        TokenVO tokenVO = new TokenVO();
        tokenVO.setRongCloudToken(newContact.getRongToken());
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

    @Override
    public List<Group> selectContactList() {
        List<Group> groups = cm.selectGroup(new Group());
        for (Group group : groups) {
            List<ContactsResult> contactsResultList = cm.selectByGid(group.getId());
            if (contactsResultList != null && contactsResultList.size() > 0) {
                group.setContactsList(contactsResultList);
            }
        }
        List<Group> groupList = TreeUtil.treeRecursionDataList(groups, 0);
        return groupList;
    }

    @Override
    public Long queryForGroupCounts(Group group) {
        return cm.queryForGroupCounts(group);
    }

    @Override
    public List<Role> queryRoleForAllOrPage(Role role) {
        if (role.getPage() != null) {
            role.setPage((role.getPage() - 1) * role.getPageSize());
        }
        return cm.queryRoleForAllOrPage(role);
    }

    @Override
    public Long queryRoleForCounts(Role role) {
        return cm.queryRoleForCounts(role);
    }

    @Override
    public BaseData selectDataById(Integer type) {
        return cm.selectDataById(type);
    }
}
