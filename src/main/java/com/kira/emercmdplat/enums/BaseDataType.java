package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;
import com.kira.emercmdplat.exception.CustomException;

/**
 * @Author: kira
 * @Date: 2020/7/9 17:15
 * @Description:基础数据枚举类
 */
public enum BaseDataType implements BaseEnum<BaseDataType> {

    URL(1, "baseUrl"), REPORT_TIME(2, "reportTime"), VIDEO_HTML(3, "videoHtml"), PARAM(4, "paramName"), AUDIO_HTML(5, "audioHtml");

    private Integer code;

    private String name;

    BaseDataType(Integer code, String name) {
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
    public BaseDataType getProperty(Integer key) {
        return null;
    }

    public static BaseDataType getByValue(Integer key) {
        switch (key) {
            case 1:
                return URL;
            case 2:
                return REPORT_TIME;
            case 3:
                return VIDEO_HTML;
            case 4:
                return PARAM;
            case 5:
                return AUDIO_HTML;
            default:
                throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "未知类型");
        }
    }
}
