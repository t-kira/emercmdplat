package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;

/**
 * @Author: kira
 * @Date: 2020/4/30 23:34
 * @Description:
 */
public enum EventLevel implements BaseEnum<EventLevel> {
    EVENT_COMMON(1, "一般"), EVENT_MORE(2, "较大"), EVENT_MAJOR(3, "重大"),
    EVENT_ESPECIALLY_IMPORTANT(4, "特别重大");

    private Integer LEVEL_CODE;

    private String LEVEL_NAME;

    EventLevel(Integer code, String name) {
        this.LEVEL_CODE = code;
        this.LEVEL_NAME = name;
    }

    /**
     * 获取属性int值
     *
     * @return
     */
    @Override
    public Integer getNo() {
        return LEVEL_CODE;
    }

    /**
     * 获取属性名称
     *
     * @return
     */
    @Override
    public String getName() {
        return LEVEL_NAME;
    }

    /**
     * 枚举值
     *
     * @param key
     * @return
     */
    @Override
    public EventLevel getProperty(Integer key) {
        switch (key) {
            case 1:
                return EVENT_COMMON;
            case 2:
                return EVENT_MORE;
            case 3:
                return EVENT_MAJOR;
            case 4:
                return EVENT_ESPECIALLY_IMPORTANT;
            default:
                throw new RuntimeException("无法识别的状态");
        }
    }
}
