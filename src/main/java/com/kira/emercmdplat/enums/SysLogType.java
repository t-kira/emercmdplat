package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;
import com.kira.emercmdplat.pojo.Report;

/**
 * @Author: kira
 * @Date: 2020/6/26 10:36
 * @Description:
 */
public enum SysLogType implements BaseEnum<SysLogType> {
    UNDEFINED_TYPE(0, "无法识别的状态"), EVENT_INSERT(1, "事件接报"), EVENT_VERIFY(2, "事件核实"),
    VERIFY_REPORT_INSERT(3, "添加核实报告"),
    INSERT_LEADER_INSTRUCT_INSERT(4, "添加领导拟办意见"), START_RESERVE_PLAN(5, "启动预案"),
    DISPATCH_CONTROL(6, "指挥调度"), EVENT_END(7, "完结事件"), EVENT_DELETE(8, "删除事件"),
    EVENT_UPDATE(9, "更新事件"), UPDATE_EVENT_DEVELOPMENT(10, "修改事件发展"),
    INSERT_EVENT_DEVELOPMENT(11, "添加事件发展"), EVENT_MERGE(12, "事件合并"), COMMON(13, "显示原始内容");

    private Integer methodType;

    private String methodName;

    SysLogType(Integer code, String name) {
        this.methodType = code;
        this.methodName = name;
    }

    /**
     * 获取属性int值
     *
     * @return
     */
    @Override
    public Integer getNo() {
        return methodType;
    }

    /**
     * 获取属性名称
     *
     * @return
     */
    @Override
    public String getName() {
        return methodName;
    }

    /**
     * 枚举值
     *
     * @param key
     * @return
     */
    @Override
    public SysLogType getProperty(Integer key) {
        switch (key) {
            case 0:
                return UNDEFINED_TYPE;
            case 1:
                return EVENT_INSERT;
            case 2:
                return EVENT_VERIFY;
            case 3:
                return VERIFY_REPORT_INSERT;
            case 4:
                return INSERT_LEADER_INSTRUCT_INSERT;
            case 5:
                return START_RESERVE_PLAN;
            case 6:
                return DISPATCH_CONTROL;
            case 7:
                return EVENT_END;
            case 8:
                return EVENT_DELETE;
            case 9:
                return EVENT_UPDATE;
            case 10:
                return UPDATE_EVENT_DEVELOPMENT;
            case 11:
                return INSERT_EVENT_DEVELOPMENT;
            case 12:
                return EVENT_MERGE;
            default:
                throw new RuntimeException("无法识别的状态");
        }
    }

    public static SysLogType getByValue(Integer key) {
        switch (key) {
            case 0:
                return UNDEFINED_TYPE;
            case 1:
                return EVENT_INSERT;
            case 2:
                return EVENT_VERIFY;
            case 3:
                return VERIFY_REPORT_INSERT;
            case 4:
                return INSERT_LEADER_INSTRUCT_INSERT;
            case 5:
                return START_RESERVE_PLAN;
            case 6:
                return DISPATCH_CONTROL;
            case 7:
                return EVENT_END;
            case 8:
                return EVENT_DELETE;
            case 9:
                return EVENT_UPDATE;
            case 10:
                return UPDATE_EVENT_DEVELOPMENT;
            case 11:
                return INSERT_EVENT_DEVELOPMENT;
            case 12:
                return EVENT_MERGE;
            case 13:
                return COMMON;
            default:
                throw new RuntimeException("无法识别的状态");
        }
    }
}
