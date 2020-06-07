package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/18 22:33
 * @Description:
 */
@Data
public class MessageExtend extends Message{

    private Integer page;

    private Integer pageSize;

    private String order;

    private String orderType;
}
