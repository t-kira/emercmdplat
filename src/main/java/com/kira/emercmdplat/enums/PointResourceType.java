package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;

/**
 * @Author: kira
 * @Date: 2020/4/30 23:34
 * @Description:轨迹资源类型
 */
public enum PointResourceType implements BaseEnum<PointResourceType> {
    UNDEFINED_SOURCE(0, "未知类型"), PERSON(1, "人"), AUTO(2, "汽车");

    private Integer CODE;

    private String NAME;

    PointResourceType(Integer code, String name) {
        this.CODE = code;
        this.NAME = name;
    }

    /**
     * 获取属性int值
     *
     * @return
     */
    @Override
    public Integer getNo() {
        return CODE;
    }

    /**
     * 获取属性名称
     *
     * @return
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * 枚举值
     *
     * @param key
     * @return
     */
    @Override
    public PointResourceType getProperty(Integer key) {
        switch (key) {
            case 1:
                return PERSON;
            case 2:
                return AUTO;
            default:
                return UNDEFINED_SOURCE;
        }
    }

    public static PointResourceType getByValue(Integer key) {
        switch (key) {
            case 1:
                return PERSON;
            case 2:
                return AUTO;
            default:
                return UNDEFINED_SOURCE;
        }
    }
}
