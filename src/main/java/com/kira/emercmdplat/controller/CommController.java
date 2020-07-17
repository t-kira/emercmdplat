package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.pojo.ContactsResult;
import com.kira.emercmdplat.service.ContactService;
import com.kira.emercmdplat.utils.*;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: kira
 * @Date: 2020/6/11 16:00
 * @Description:融云接口
 */
@CrossOrigin
@RestController
@RequestMapping("/comm")
public class CommController {

    private static final Logger logger = LoggerFactory.getLogger(CommController.class);

    @Autowired
    private ContactService cs;

    @ResponseBody
    @GetMapping("rongCloud_token")
    public AlvesJSONResult getRongCloudToken(HttpServletRequest request) {
        String token = TokenUtil.getRequestToken(request);
        ContactsResult contacts = cs.findByToken(token);
        String result = TokenUtil.getRongCloudToken(contacts);
        if (StringUtil.isEmpty(result)) {
            return AlvesJSONResult.errorMsg("获取Token失败...");
        } else {
            JSONObject resultJson = JSONObject.fromObject(result);
            int code = resultJson.getInt("code");
            if (code == 200) {
                logger.error("用户Id：" + contacts.getId() + ",用户名：" + contacts.getUsername() +",融云token:" + resultJson.getString("token"));
                return AlvesJSONResult.ok(null, resultJson.getString("token"));
            } else {
                return AlvesJSONResult.errorMsg(resultJson.getString("errorMessage"));
            }
        }
    }
}
