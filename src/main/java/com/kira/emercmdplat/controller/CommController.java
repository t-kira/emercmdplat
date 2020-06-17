package com.kira.emercmdplat.controller;

import com.kira.emercmdplat.config.WebSecurityConfig;
import com.kira.emercmdplat.pojo.ContactsResult;
import com.kira.emercmdplat.service.ContactService;
import com.kira.emercmdplat.utils.*;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
        String nonce = StringUtil.getRandomNum(10);
        String timeStamp = DateUtil.getTimeStampString();
        String signature = SecuritySHA1Utils.shaEncode(WebSecurityConfig.APP_SECRE + nonce + timeStamp);
        Map<String, String> headers = new HashMap<>();
        headers.put("App-Key", WebSecurityConfig.APP_KEY);
        headers.put("Nonce", nonce);
        headers.put("Timestamp", timeStamp);
        headers.put("Signature", signature);
        Map params = new HashMap();
        params.put("userId", contacts.getId());
        params.put("name", contacts.getContactName());
        params.put("portraitUri", contacts.getPhoto());
        String result = HttpUtils.post(WebSecurityConfig.RONGCLOUD_API_URL, params, headers, WebSecurityConfig.HTTP_CONNECT_TIMEOUT, WebSecurityConfig.HTTP_READ_TIMEOUT, "UTF-8");
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
