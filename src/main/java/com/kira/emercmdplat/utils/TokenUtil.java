package com.kira.emercmdplat.utils;

import com.kira.emercmdplat.config.WebSecurityConfig;
import com.kira.emercmdplat.pojo.ContactsResult;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: kira
 * @Date: 2020/6/1 13:36
 * @Description:
 */
public class TokenUtil {

    /**
     * 获取请求的token
     */
    public static String getRequestToken(HttpServletRequest httpRequest) {

        //从header中获取token
        String token = httpRequest.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = httpRequest.getParameter("token");
        }
        return token;
    }


    public static String getRongCloudToken(ContactsResult contacts) {
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
        return HttpUtils.post(WebSecurityConfig.RONGCLOUD_API_URL, params, headers, WebSecurityConfig.HTTP_CONNECT_TIMEOUT, WebSecurityConfig.HTTP_READ_TIMEOUT, "UTF-8");
    }
}
