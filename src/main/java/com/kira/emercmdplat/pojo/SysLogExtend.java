package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/5/4 22:58
 * @Description:
 */
@Data
public class SysLogExtend extends SysLog{

    private Integer page;

    private Integer pageSize;
}
