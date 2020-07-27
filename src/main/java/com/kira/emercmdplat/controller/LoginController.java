package com.kira.emercmdplat.controller;

import javax.servlet.http.HttpServletRequest;

import com.kira.emercmdplat.config.InitData;
import com.kira.emercmdplat.enums.BaseDataType;
import com.kira.emercmdplat.pojo.*;
import com.kira.emercmdplat.service.PermissionService;
import com.kira.emercmdplat.utils.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kira.emercmdplat.service.ContactService;

import java.util.List;

@RestController
public class LoginController {

	@Autowired
	private ContactService contactService;
	@Autowired
	private PermissionService ps;

	@ResponseBody
    @PostMapping(value = "login")
	public AlvesJSONResult login(@RequestBody Contacts contacts,HttpServletRequest request) {
    	if (null == contacts.getUsername() || null == contacts.getPassword()) {
            return AlvesJSONResult.errorMsg("用户名密码不能为空");
        }
    	ContactsResult user = contactService.selectByUserName(contacts.getUsername());
    	if (user == null || !StringUtil.isEq(user.getPassword(), MD52.MD5Encode(contacts.getPassword()))) {
    		return AlvesJSONResult.errorMsg("用户名或密码错误");
    	} else {
    		TokenVO tokenVo = contactService.createToken(user);
			List<Permission> permissions = ps.findPermissionsByCid(user.getId());
			List<Permission> permissionList = TreeUtil.treeRecursionPermissionDataList(permissions, 0);
			String baseUrl = InitData.getVal(BaseDataType.URL.getNo());
			JSONObject json = new JSONObject();
			json.put("permissionList", permissionList);
			json.put("token", tokenVo);
			json.put("user", user);
			json.put("baseUrl", baseUrl);
			return AlvesJSONResult.ok(json);
		}
    }

    @ResponseBody
	@GetMapping(name = "获取权限菜单", value = "list_permission")
    public AlvesJSONResult permissionList(HttpServletRequest request) {
		String token = TokenUtil.getRequestToken(request);
		Contacts contacts = contactService.findByToken(token);
		List<Permission> permissions = ps.findPermissionsByCid(contacts.getId());
		List<Permission> permissionList = TreeUtil.treeRecursionPermissionDataList(permissions, 0);
		return AlvesJSONResult.ok(permissionList);
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
