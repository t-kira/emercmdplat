package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/13 23:52
 * @Description:
 */
@Data
public class Report {

    private Long id;

    private Long eventId;

    private String contactId;

    private String reportTime;

    private String keyWord;

    private String reportContent;

    private Integer type;

    private String createTime;

    private String updateTime;
}
