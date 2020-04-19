package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/4/10 22:47
 * @Description:
 */
@Data
public class DutyExtent extends Duty{
    /**
     * 机构名称
     */
    private String mechanismName;
    /**
     * 职务名称
     */
    private String jobName;

}
