package com.kira.emercmdplat.pojo;

import lombok.Data;

import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/7/12 20:10
 * @Description:
 */
@Data
public class ShiftJob {

    private Long id;

    private String shiftJobName;

    private List<ContactsResult> contactsList;
}
