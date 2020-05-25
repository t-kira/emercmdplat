package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/21 23:30
 * @Description:
 */
@Data
public class ContactsExtend extends Contacts {

    private Integer page;

    private Integer pageSize;
}
