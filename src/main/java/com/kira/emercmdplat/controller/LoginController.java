package com.kira.emercmdplat.controller;

import javax.servlet.http.HttpServletRequest;

import com.kira.emercmdplat.config.WebSecurityConfig;
import com.kira.emercmdplat.pojo.Permission;
import com.kira.emercmdplat.pojo.TokenVO;
import com.kira.emercmdplat.utils.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kira.emercmdplat.pojo.Contacts;
import com.kira.emercmdplat.pojo.ContactsResult;
import com.kira.emercmdplat.service.ContactService;

import java.util.List;

@RestController
public class LoginController {

	@Autowired
	private ContactService contactService;

    @PostMapping(value = "login")
	public AlvesJSONResult login(@RequestBody Contacts contacts,HttpServletRequest request) {
    	if (null == contacts.getUsername() || null == contacts.getPassword()) {
            return AlvesJSONResult.errorMsg("用户名密码不能为空");
        }
    	Contacts user = contactService.selectByUserName(contacts.getUsername());
    	if (user == null || !StringUtil.isEq(user.getPassword(), MD52.MD5Encode(contacts.getPassword()))) {
    		return AlvesJSONResult.errorMsg("用户名或密码错误");
    	} else {
    		TokenVO tokenVo = contactService.createToken(user);
			List<Permission> permissions = contactService.findPermissionsByCid(user.getId());
			List<Permission> permissionList = TreeUtil.treeRecursionPermissionDataList(permissions, 0);
			JSONObject json = new JSONObject();
			json.put("permissionList", permissionList);
			json.put("token", tokenVo);
			return AlvesJSONResult.ok(json);
		}
    }

	/**
	 * 登出
	 *
	 * @param
	 * @return
	 */
	@PostMapping("/logout")
	public AlvesJSONResult logout(HttpServletRequest request) {
		//从request中取出token
		String token = TokenUtil.getRequestToken(request);
		contactService.logout(token);
		return AlvesJSONResult.ok();
	}
}
