package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;

/**
 * @Author: kira
 * @Date: 2020/4/18 22:44
 * @Description:消息类型
 */
public enum MessageType implements BaseEnum<MessageType> {

    NORMAL_TYPE(0,"正常类型"),MESSAGE_TRANSFER(1, "转办督办"), MESSAGE_REPORT(2, "呈报上报"), MESSAGE_RETURN(3, "退回");

    private Integer MESSAGE_Code;

    private String MESSAGE_Name;

    MessageType(Integer code, String name) {
        this.MESSAGE_Code = code;
        this.MESSAGE_Name = name;
    }

    /**
     * 获取属性int值
     * @return
     */
    @Override
    public Integer getNo() {
        return MESSAGE_Code;
    }

    /**
     * 获取属性名称
     * @return
     */
    @Override
    public String getName() {
        return MESSAGE_Name;
    }

    /**
     * 枚举值
     * @param key
     * @return
     */
    @Override
    public MessageType getProperty(Integer key) {
        switch (key) {
            case 0:
                return NORMAL_TYPE;
            case 1:
                return MESSAGE_TRANSFER;
            case 2:
                return MESSAGE_REPORT;
            case 3:
                return MESSAGE_RETURN;
            default:
                throw new RuntimeException("无法识别的状态");
        }
    }
}
