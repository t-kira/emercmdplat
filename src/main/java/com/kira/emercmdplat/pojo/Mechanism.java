package com.kira.emercmdplat.pojo;

/**
 * @Author: kira
 * @Date: 2020/4/8 17:49
 * @Description:机构
 */
public class Mechanism {

    private Long id;
    /**
     * 机构名称
     */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
