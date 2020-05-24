package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;

/**
 * @Author: kira
 * @Date: 2020/4/30 23:34
 * @Description:
 */
public enum SourceType implements BaseEnum<SourceType> {
    HAZARD_SOURCE(1, "危险源"), PROTECTION_TARGET(3, "防护目标"), EMERGENCY_TEAM(5, "应急队伍"),
    MEDICAL_INSTITUTION(7, "医疗机构"), SHELTER(9, "避难场所"), RESERVE_LIBRARY(13, "储备库"),
    EMERGENCY_SUPPLY(15, "应急物资"), EMERGENCY_EXPERT(11, "应急专家"), EMERGENCY_FUND(17, "应急资金"),
    TRANSPORT_UNIT(19, "运输单位");

    private Integer TYPE_CODE;

    private String TYPE_NAME;

    SourceType(Integer code, String name) {
        this.TYPE_CODE = code;
        this.TYPE_NAME = name;
    }

    /**
     * 获取属性int值
     *
     * @return
     */
    @Override
    public Integer getNo() {
        return TYPE_CODE;
    }

    /**
     * 获取属性名称
     *
     * @return
     */
    @Override
    public String getName() {
        return TYPE_NAME;
    }

    /**
     * 枚举值
     *
     * @param key
     * @return
     */
    @Override
    public SourceType getProperty(Integer key) {
        switch (key) {
            case 1:
                return HAZARD_SOURCE;
            case 3:
                return PROTECTION_TARGET;
            case 5:
                return EMERGENCY_TEAM;
            case 7:
                return MEDICAL_INSTITUTION;
            case 9:
                return SHELTER;
            case 11:
                return EMERGENCY_EXPERT;
            case 13:
                return RESERVE_LIBRARY;
            case 15:
                return EMERGENCY_SUPPLY;
            case 17:
                return EMERGENCY_FUND;
            case 19:
                return TRANSPORT_UNIT;
            default:
                throw new RuntimeException("无法识别的状态");
        }
    }
}
