package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/9 23:59
 * @Description:
 */
@Data
public class EventTaskExtend extends EventTask{

    private Integer page;

    private Integer pageSize;

    private String order;

    private String orderType;
}
