package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/14 00:20
 * @Description:
 */
@Data
public class ReportExtend extends Report{

    private Integer page;

    private Integer pageSize;
}
