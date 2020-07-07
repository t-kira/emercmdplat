package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;

/**
 * @Author: kira
 * @Date: 2020/6/26 12:50
 * @Description:事件核实途径
 */
public enum VerifyMethod implements BaseEnum<VerifyMethod> {

    VIDEO_METHOD(1, "视频监控"), PHONE_METHOD(2, "电话"), APP_METHOD(3, "app");

    private String methodName;

    private Integer methodType;

    VerifyMethod(Integer methodType, String methodName) {
        this.methodName = methodName;
        this.methodType = methodType;
    }

    /**
     * 获取属性int值
     *
     * @return
     */
    @Override
    public Integer getNo() {
        return this.methodType;
    }

    /**
     * 获取属性名称
     *
     * @return
     */
    @Override
    public String getName() {
        return this.methodName;
    }

    /**
     * 枚举值
     *
     * @param key
     * @return
     */
    @Override
    public VerifyMethod getProperty(Integer key) {
        return null;
    }

    public static VerifyMethod getByValue(Integer key) {
        switch (key) {
            case 1:
                return VIDEO_METHOD;
            case 2:
                return PHONE_METHOD;
            case 3:
                return APP_METHOD;
            default:
                throw new RuntimeException("无法识别的状态");
        }
    }
}
