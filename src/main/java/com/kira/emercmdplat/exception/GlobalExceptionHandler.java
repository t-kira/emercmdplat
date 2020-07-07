package com.kira.emercmdplat.exception;

import com.kira.emercmdplat.enums.ResultEnum;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

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
    public ErrorResponseEntity errorHandler(HttpServletRequest request,
                               HttpServletResponse response,
                               Exception e){
        e.printStackTrace();
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%                 error             %%%%%%                      error          %%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        if (e instanceof CustomException) {
            CustomException exception = (CustomException) e;
            return new ErrorResponseEntity(exception.getCode(), exception.getMessage());
        } else {
            return new ErrorResponseEntity(ResultEnum.UNKNOW_ERROR.getNo(), ResultEnum.UNKNOW_ERROR.getName());
        }
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
