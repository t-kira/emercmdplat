package com.kira.emercmdplat.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kira.emercmdplat.pojo.ContactsResult;

/**
 * Description:
 * Created by kira on 2017/7/5.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// 获取请求的URL
		String url = request.getRequestURI();
		// URL:login.jsp是公开的,除了login.jsp是可以公开访问的，其它的URL都进行拦截控制
		boolean state = urlState(url, request, response);
		if (state) {
			return state;
		}
		// 跳转到登录界面
		response.sendRedirect("/#/login");
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean urlState(String url, HttpServletRequest request,
			HttpServletResponse response) {
		if (url.indexOf("login") >= 0 || url.indexOf("logout") >= 0) {
			return true;
		}
		// 获取Session
		HttpSession session = request.getSession();
		ContactsResult user_info = (ContactsResult) session.getAttribute("userInfo");
		if (user_info != null) {
			return true;
		}
		return false;
	}
}
