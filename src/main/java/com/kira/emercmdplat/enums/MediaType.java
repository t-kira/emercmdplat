package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;
import com.kira.emercmdplat.exception.CustomException;

/**
 * @Author: kira
 * @Date: 2020/7/9 17:15
 * @Description:基础数据枚举类
 */
public enum MediaType implements BaseEnum<MediaType> {

    AUDIO(1, "音频"), VIDEO(2, "视频");

    private Integer code;

    private String name;

    MediaType(Integer code, String name) {
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
    public MediaType getProperty(Integer key) {
        return null;
    }

    public static MediaType getByValue(Integer key) {
        switch (key) {
            case 1:
                return AUDIO;
            case 2:
                return VIDEO;
            default:
                throw new CustomException(ResultEnum.UNKNOW_ERROR.getNo(), "未知类型");
        }
    }
}
