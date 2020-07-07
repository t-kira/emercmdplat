package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;

/**
 * @Author: kira
 * @Date: 2020/4/13 02:15
 * @Description:预案状态
 */
public enum ReservePlanStatus implements BaseEnum<ReservePlanStatus> {

    UNEDIT(0,"未编辑"),UNSTART(1, "未启动"), START(2, "已启动"),STOP(3, "终止"), ADJUST(4, "调整");

    private Integer PLAN_Code;

    private String PLAN_Name;


    ReservePlanStatus(Integer code, String name) {
        this.PLAN_Code = code;
        this.PLAN_Name = name;
    }

    /**
     * 获取属性int值
     * @return
     */
    @Override
    public Integer getNo() {
        return PLAN_Code;
    }

    /**
     * 获取属性名称
     * @return
     */
    @Override
    public String getName() {
        return PLAN_Name;
    }

    /**
     * 枚举值
     * @param key
     * @return
     */
    @Override
    public ReservePlanStatus getProperty(Integer key) {
        switch (key) {
            case 0:
                return UNEDIT;
            case 1:
                return UNSTART;
            case 2:
                return START;
            case 3:
                return STOP;
            case 4:
                return ADJUST;
            default:
                throw new RuntimeException("无法识别的状态");
        }
    }
}
