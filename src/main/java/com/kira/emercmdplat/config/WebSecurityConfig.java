package com.kira.emercmdplat.config;

/**
 * @Author: kira
 * @Date: 2020/6/1 10:14
 * @Description:
 */
public interface WebSecurityConfig {

    int EXPIRE = 168;

    String APP_KEY = "p5tvi9dspqv74";

    String APP_SECRE = "GM5CN6mamyX8wt";

    String RONGCLOUD_API_URL = "https://api-cn.ronghub.com/user/getToken.json";

    int HTTP_CONNECT_TIMEOUT = 3000;

    int HTTP_READ_TIMEOUT = 3000;

    String HOST = "https://www.chinahqd.cn:9090";

    String COMMONICON = "/img/active.png";

    String ACTIVEICON = "/img/common.png";
}
