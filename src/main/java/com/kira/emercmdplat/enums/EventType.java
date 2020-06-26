package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;

/**
 * @Author: kira
 * @Date: 2020/6/26 12:57
 * @Description:
 */
public enum EventType implements BaseEnum<EventType> {
    NORMAL_EVENT(1, "一般事件"), EMERGENCY_EVENT(2, "应急事件");

    private String typeName;

    private Integer type;

    EventType(Integer type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    /**
     * 获取属性int值
     *
     * @return
     */
    @Override
    public Integer getNo() {
        return null;
    }

    /**
     * 获取属性名称
     *
     * @return
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * 枚举值
     *
     * @param key
     * @return
     */
    @Override
    public EventType getProperty(Integer key) {
        return null;
    }

    public static EventType getByValue(Integer key) {
        switch (key) {
            case 1:
                return NORMAL_EVENT;
            case 2:
                return EMERGENCY_EVENT;
            default:
                throw new RuntimeException("无法识别的状态");
        }
    }
}
