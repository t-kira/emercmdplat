package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;
import com.kira.emercmdplat.exception.CustomException;

/**
 * @Author: kira
 * @Date: 2020/7/9 17:15
 * @Description:班次枚举类
 */
public enum ShiftStatus implements BaseEnum<ShiftStatus> {

    START_UP(1, "启用"), SHUT_DOWN(2, "关闭");

    private Integer code;

    private String name;

    ShiftStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
    /**
     * 获取属性int值
     *
     * @return
     */
    @Override
    public Integer getNo() {
        return this.code;
    }

    /**
     * 获取属性名称
     *
     * @return
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * 枚举值
     *
     * @param key
     * @return
     */
    @Override
    public ShiftStatus getProperty(Integer key) {
        return null;
    }

    public static ShiftStatus getByValue(Integer key) {
        switch (key) {
            case 1:
                return START_UP;
            case 2:
                return SHUT_DOWN;
            default:
                throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "未知班次状态");
        }
    }
}
