package com.kira.emercmdplat.exception;

import com.kira.emercmdplat.enums.ResultEnum;

/**
 * @Author: kira
 * @Date: 2020/7/6 17:33
 * @Description:自定义异常类
 */
public class CustomException extends RuntimeException{

    private static final long serialVersionUID = 4564124491192825748L;

    private int code;

    public CustomException() {
        super();
    }
    public CustomException(int code) {
        super(ResultEnum.getByValue(code).getName());
        this.setCode(code);
    }

    public CustomException(int code, String message) {
        super(message);
        this.setCode(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
