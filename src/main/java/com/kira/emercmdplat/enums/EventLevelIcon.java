package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;

/**
 * @Author: kira
 * @Date: 2020/4/30 23:34
 * @Description:事件等级
 */
public enum EventLevelIcon implements BaseEnum<EventLevelIcon> {
    UNDEFINED_LEVEL(0, "未识别事件等级"), EVENT_COMMON(3, "/img/event_common.png,/img/event_common.png"), EVENT_MORE(6, "/img/event_more.png,/img/event_more.png"), EVENT_MAJOR(9, "/img/event_imp.png,/img/event_imp.png"),
    EVENT_ESPECIALLY_IMPORTANT(10, "/img/event_v_imp.png,/img/event_v_imp.png");

    private Integer LEVEL_CODE;

    private String LEVEL_NAME;

    EventLevelIcon(Integer code, String name) {
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
    public EventLevelIcon getProperty(Integer key) {
        switch (key) {
            case 3:
                return EVENT_COMMON;
            case 6:
                return EVENT_MORE;
            case 9:
                return EVENT_MAJOR;
            case 10:
                return EVENT_ESPECIALLY_IMPORTANT;
            default:
                throw new RuntimeException("无法识别的状态");
        }
    }

    public static EventLevelIcon getByValue(Integer key) {
        switch (key) {
            case 3:
                return EVENT_COMMON;
            case 6:
                return EVENT_MORE;
            case 9:
                return EVENT_MAJOR;
            case 10:
                return EVENT_ESPECIALLY_IMPORTANT;
            default:
                return UNDEFINED_LEVEL;
        }
    }
}
