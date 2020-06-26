package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;

/**
 * @Author: kira
 * @Date: 2020/4/13 02:15
 * @Description:核实状态
 */
public enum EventVerifyStatus implements BaseEnum<EventVerifyStatus> {

    IS_TRUE(1,"属实"),IS_FALSE(2, "不属实"),IS_MERGE(3, "被合并");

    private Integer VERIFY_CODE;

    private String VERIFY_NAME;


    EventVerifyStatus(Integer code, String name) {
        this.VERIFY_CODE = code;
        this.VERIFY_NAME = name;
    }

    /**
     * 获取属性int值
     * @return
     */
    @Override
    public Integer getNo() {
        return VERIFY_CODE;
    }

    /**
     * 获取属性名称
     * @return
     */
    @Override
    public String getName() {
        return VERIFY_NAME;
    }

    /**
     * 枚举值
     * @param key
     * @return
     */
    @Override
    public EventVerifyStatus getProperty(Integer key) {
        switch (key) {
            case 1:
                return IS_TRUE;
            case 2:
                return IS_FALSE;
            case 3:
                return IS_MERGE;
            default:
                throw new RuntimeException("无法识别的状态");
        }
    }

    public static EventVerifyStatus getByValue(Integer key) {
        switch (key) {
            case 1:
                return IS_TRUE;
            case 2:
                return IS_FALSE;
            case 3:
                return IS_MERGE;
            default:
                throw new RuntimeException("无法识别的状态");
        }
    }
}
