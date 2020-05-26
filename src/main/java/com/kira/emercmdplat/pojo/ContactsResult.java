package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/21 22:51
 * @Description:
 */
@Data
public class ContactsResult extends Contacts{

    private String jName;

    private String mName;

    private String gName;
}
