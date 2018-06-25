package org.interior.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.interior.repository.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		//System.out.println("분석/통계");
		HttpSession session = request.getSession();
		if((User) session.getAttribute("user") == null)
		{
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}
		
		
		return true;
	}
}


