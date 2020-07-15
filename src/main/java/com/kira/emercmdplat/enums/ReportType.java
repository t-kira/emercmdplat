package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;
import com.kira.emercmdplat.exception.CustomException;

/**
 * @Author: kira
 * @Date: 2020/7/9 17:15
 * @Description:信息快报报告类型
 */
public enum ReportType implements BaseEnum<ReportType> {

    DAY(1, "每日一报"), WEEK(2, "周报"), MONTH(3, "月报");

    private Integer code;

    private String name;

    ReportType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
    /**
     * 获取属性int值
     *
     * @return
     */
    @Override
    public Integer getNo() {
        return this.code;
    }

    /**
     * 获取属性名称
     *
     * @return
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * 枚举值
     *
     * @param key
     * @return
     */
    @Override
    public ReportType getProperty(Integer key) {
        return null;
    }

    public static ReportType getByValue(Integer key) {
        switch (key) {
            case 1:
                return DAY;
            case 2:
                return WEEK;
            case 3:
                return MONTH;
            default:
                throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "未知类型");
        }
    }
}
