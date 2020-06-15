package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/6/1 13:38
 * @Description:
 */
@Data
public class TokenVO {

    private String token;

    private String expireTime;

    private String rongCloudToken;
}
