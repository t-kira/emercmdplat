package com.kira.emercmdplat.exception;

import com.kira.emercmdplat.enums.ResultEnum;
import com.kira.emercmdplat.utils.AlvesJSONResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: kira
 * @Date: 2020/7/6 17:33
 * @Description:全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public AlvesJSONResult errorHandler(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Exception e){
        e.printStackTrace();
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%                 error             %%%%%%                      error          %%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        if (e instanceof CustomException) {
            CustomException exception = (CustomException) e;
            return AlvesJSONResult.build(exception.getCode(), exception.getMessage(), null);
        } else {
            return AlvesJSONResult.build(ResultEnum.UNKNOW_ERROR.getNo(), ResultEnum.UNKNOW_ERROR.getName(), null);
        }
    }

    /**
     * post请求参数校验抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AlvesJSONResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        //获取异常中随机一个异常信息
        String defaultMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        return AlvesJSONResult.build(ResultEnum.ERROR_PARAMETER.getNo(), defaultMessage, null);
    }

    /**
     * 判断是不是Ajax请求
     *
     * @param httpRequest
     * @return boolean
     */
    public static boolean isAjax(HttpServletRequest httpRequest) {
        return (httpRequest.getHeader("X-Requested-With") != null
                && "XMLHttpRequest".equals(httpRequest.getHeader("X-Requested-With").toString()));
    }
}
