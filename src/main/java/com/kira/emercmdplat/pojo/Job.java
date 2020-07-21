package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/7/21 15:03
 * @Description:
 */
@Data
public class Job extends Base{

    private Long id;
    /**
     * 职务名称
     */
    private String name;
}
