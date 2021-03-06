package com.kira.emercmdplat.enums;

import com.kira.emercmdplat.enums.base.BaseEnum;

/**
 * @Author: kira
 * @Date: 2020/7/6 17:45
 * @Description:
 */
public enum ResultEnum implements BaseEnum<ResultEnum> {
    SUCCESS(200, "成功"), UNKNOW_ERROR(-1, "未知错误"), MISSING_PARAMETER(10001, "缺少参数"), ERROR_PARAMETER(10002, "参数错误"), NON_DATA(10003, "数据不存在"),
    EXIST_DATA(10004, "数据已存在"), EVENT_FINISH(10005, "事件已结束,无法操作"), PLAN_TYPE_RELATE(10006, "预案类型相关信息已存在，不能删除"),
    PLAN_VERSION_RELATE(10007, "预案相关信息已存在，不能删除"), PLAN_RESPONSE_RELATE(10008, "预案响应相关信息已存在，不能删除"), RELATED_DATA(10009, "有关联数据");
    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
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
        return this.msg;
    }

    /**
     * 枚举值
     *
     * @param key
     * @return
     */
    @Override
    public ResultEnum getProperty(Integer key) {
        return null;
    }

    public static ResultEnum getByValue(Integer key) {
        switch (key) {
            case 200:
                return SUCCESS;
            case 10001:
                return MISSING_PARAMETER;
            case 10002:
                return ERROR_PARAMETER;
            case 10003:
                return NON_DATA;
            case 10004:
                return EXIST_DATA;
            case 10005:
                return EVENT_FINISH;
            case 10006:
            	return PLAN_TYPE_RELATE;
            case 10007:
            	return PLAN_VERSION_RELATE;
            case 10008:
            	return PLAN_RESPONSE_RELATE;
            case 10009:
                return RELATED_DATA;
            default:
                return UNKNOW_ERROR;
        }
    }
}
