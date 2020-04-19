package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;

/**
 * @Author: kira
 * @Date: 2020/4/13 02:33
 * @Description:事件进程
 */
public enum  EventProcess implements BaseEnum<EventProcess> {

    EVENT_RECEIVE(1, "接报"), VERIFY_REPORT(2, "核实报告"),
    LEADER_INSTRUCT(3, "领导批示"), RESERVE_PLAN(4, "启动预案"),
    EVENT_FINISH(5, "事件完结");

    private Integer PROCESS_Code;

    private String PROCESS_Name;

    EventProcess(Integer code, String name) {
        this.PROCESS_Code = code;
        this.PROCESS_Name = name;
    }

    /**
     * 获取属性int值
     *
     * @return
     */
    @Override
    public Integer getNo() {
        return PROCESS_Code;
    }

    /**
     * 获取属性名称
     *
     * @return
     */
    @Override
    public String getName() {
        return PROCESS_Name;
    }

    /**
     * 枚举值
     *
     * @param key
     * @return
     */
    @Override
    public EventProcess getProperty(Integer key) {
        switch (key) {
            case 1:
                return EVENT_RECEIVE;
            case 2:
                return VERIFY_REPORT;
            case 3:
                return LEADER_INSTRUCT;
            case 4:
                return RESERVE_PLAN;
            case 5:
                return EVENT_FINISH;
            default:
                throw new RuntimeException("无法识别的状态");
        }
    }
}
