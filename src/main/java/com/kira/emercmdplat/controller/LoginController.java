package com.kira.emercmdplat.controller;

import javax.servlet.http.HttpServletRequest;

import com.kira.emercmdplat.config.WebSecurityConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kira.emercmdplat.pojo.Contacts;
import com.kira.emercmdplat.pojo.ContactsResult;
import com.kira.emercmdplat.service.ContactService;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import com.kira.emercmdplat.utils.MD52;

@RestController
public class LoginController {

	@Autowired
	private ContactService contactService;

    @PostMapping(value = "login")
	public AlvesJSONResult login(@RequestBody Contacts contacts,HttpServletRequest request) {
    	if (null == contacts.getUsername() || null == contacts.getPassword()) {
            return AlvesJSONResult.errorMsg("用户名密码不能为空");
        }
    	ContactsResult user = contactService.selectByUserName(contacts.getUsername());
    	if (user == null) {
    		return AlvesJSONResult.errorMsg("账户不存在");
    	}
    	String md5password = user.getPassword();
    	String _pwd = MD52.MD5Encode(contacts.getPassword());
    	if (StringUtils.equals(md5password, _pwd)) {
    		request.getSession().setAttribute(WebSecurityConfig.SESSION_KEY, user);
    		return AlvesJSONResult.ok(user);
    	} else {
    		return AlvesJSONResult.errorMsg("密码错误");
    	}
    }

	@GetMapping("/login_page")
	public String login(){
		return "login";
	}
}
