package com.kira.emercmdplat.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: kira
 * @Date: 2020/4/8 17:49
 * @Description:机构
 */
@Data
public class Mechanism extends Base{

    private Long id;
    /**
     * 机构名称
     */
    @NotNull(message = "机构名称不能为空")
    private String name;
}
