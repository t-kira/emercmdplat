package com.kira.emercmdplat.config;

/**
 * @Author: kira
 * @Date: 2020/6/1 10:14
 * @Description:
 */
public interface WebSecurityConfig {

    String SESSION_KEY = "1a6ac48801fd8d5adae8761e14574d74";

    int EXPIRE = 168;

    String APP_KEY = "p5tvi9dspqv74";

    String APP_SECRE = "GM5CN6mamyX8wt";

    String RONGCLOUD_API_URL = "https://api-cn.ronghub.com/user/getToken.json";

    int HTTP_CONNECT_TIMEOUT = 3000;

    int HTTP_READ_TIMEOUT = 3000;
}
