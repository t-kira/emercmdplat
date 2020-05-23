package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/5/22 21:15
 * @Description:
 */
@Data
public class EventTaskResult extends EventTask{

    private String prfName;

    private String contactName;

    private String telephone;

    private String backupPhone;

    private String workPhone;

    private String email;

    private String faxNumber;

    private String otherPhone;

    private String homePhone;
}
