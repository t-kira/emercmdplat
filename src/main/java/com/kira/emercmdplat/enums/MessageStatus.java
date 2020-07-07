package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;

/**
 * @Author: kira
 * @Date: 2020/4/18 22:44
 * @Description:消息状态
 */
public enum MessageStatus implements BaseEnum<MessageStatus> {

    MESSAGE_UNREAD(0, "未读"), MESSAGE_READ(1, "已读"), MESSAGE_DELETE(2, "删除");

    private Integer MESSAGE_Code;

    private String MESSAGE_Name;

    MessageStatus(Integer code, String name) {
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
    public MessageStatus getProperty(Integer key) {
        switch (key) {
            case 0:
                return MESSAGE_UNREAD;
            case 1:
                return MESSAGE_READ;
            case 2:
                return MESSAGE_DELETE;
            default:
                throw new RuntimeException("无法识别的状态");
        }
    }
}
