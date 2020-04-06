package com.kira.emercmdplat.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 异常捕捉
 */
@ControllerAdvice //抛出的异常会被这个类捕获
@ResponseBody
public class GlobalExceptionHandler {
    public static final String PROJECT_ERROR_VIEW = "error";//这个其实是指向  error 那个页面


    @ExceptionHandler(Exception.class)
    public Object errorHandler(HttpServletRequest request,
                               HttpServletResponse response,
                               Exception e) throws Exception {
        e.printStackTrace();
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%                 error             %%%%%%                      error          %%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        if (isAjax(request)) {
            return response;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        // System.out.println("GlobalException-捕捉："+request.getRequestURL());
        mav.setViewName(PROJECT_ERROR_VIEW);
        return mav;
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
