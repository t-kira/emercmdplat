package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;

/**
 * @Author: kira
 * @Date: 2020/7/7 11:46
 * @Description:要情速报来源
 */
public enum QuickReportOrigin implements BaseEnum<QuickReportOrigin> {
    QUICK_REPORT_ORIGIN(0, "要情速报入口"), VERIFY_REPORT_ORIGIN(1, "核实报告入口");

    private Integer CODE;

    private String NAME;

    QuickReportOrigin(Integer code, String name) {
        this.CODE = code;
        this.NAME = name;
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
    public QuickReportOrigin getProperty(Integer key) {
        return null;
    }
}
