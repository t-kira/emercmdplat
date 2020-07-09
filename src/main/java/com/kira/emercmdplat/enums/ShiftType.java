package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;
import com.kira.emercmdplat.exception.CustomException;

/**
 * @Author: kira
 * @Date: 2020/7/9 17:15
 * @Description:班次枚举类
 */
public enum ShiftType implements BaseEnum<ShiftType> {

    MORNING_SHIFT(1, "早班"), MIDDLE_SHIFT(2, "中班"), EVENING_SHIFT(3, "晚班");

    private Integer code;

    private String name;

    ShiftType(Integer code, String name) {
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
    public ShiftType getProperty(Integer key) {
        return null;
    }

    public static ShiftType getByValue(Integer key) {
        switch (key) {
            case 1:
                return MORNING_SHIFT;
            case 2:
                return MIDDLE_SHIFT;
            case 3:
                return EVENING_SHIFT;
            default:
                throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "未知班次");
        }
    }
}
