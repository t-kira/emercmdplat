package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;

/**
 * @Author: kira
 * @Date: 2020/4/13 02:15
 * @Description:事件状态
 */
public enum EventStatus implements BaseEnum<EventStatus> {

    UNFINISH(1,"未完结"),FINISH(2, "已完结");

    private Integer EVENT_Code;

    private String EVENT_Name;


    EventStatus(Integer code, String name) {
        this.EVENT_Code = code;
        this.EVENT_Name = name;
    }

    /**
     * 获取属性int值
     * @return
     */
    @Override
    public Integer getNo() {
        return EVENT_Code;
    }

    /**
     * 获取属性名称
     * @return
     */
    @Override
    public String getName() {
        return EVENT_Name;
    }

    /**
     * 枚举值
     * @param key
     * @return
     */
    @Override
    public EventStatus getProperty(Integer key) {
        switch (key) {
            case 1:
                return UNFINISH;
            case 2:
                return FINISH;
            default:
                throw new RuntimeException("无法识别的状态");
        }
    }
}
