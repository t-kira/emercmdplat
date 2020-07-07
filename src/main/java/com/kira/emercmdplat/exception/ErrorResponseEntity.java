package com.kira.emercmdplat.exception;

import lombok.Data;

/**
 * @Author: kira
 * @Date: 2020/7/6 17:35
 * @Description:错误信息返回模板
 */
public class ErrorResponseEntity {

    private int code;

    private String message;

    public ErrorResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
