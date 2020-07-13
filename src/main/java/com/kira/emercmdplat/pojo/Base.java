package com.kira.emercmdplat.pojo;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/7/13 12:06
 * @Description:基类
 */
@Data
public class Base {
    /**
     * 页码
     */
    private Integer page;
    /**
     * 每页显示行数
     */
    private Integer pageSize;
    /**
     * 按字段排序
     */
    private String order;
    /**
     * 排序类型 asc or desc
     */
    private String orderType;
}
