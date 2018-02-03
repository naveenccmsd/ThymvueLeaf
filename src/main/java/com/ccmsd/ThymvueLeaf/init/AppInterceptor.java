package com.ccmsd.ThymvueLeaf.init;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AppInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// System.out.println(ServletRequestUtils.getStringParameter(request, "id"));
		boolean alreadyLoggedIn = request.getSession().getAttribute("LOGGEDIN_USER") != null;
		if (request.getRequestURI().equalsIgnoreCase("/login") && request.getParameter("logout") != null) {
			request.getSession().invalidate();
		}
		boolean loginPageRequested = request.getRequestURI().equalsIgnoreCase("/login")
				|| request.getRequestURI().equalsIgnoreCase("/register") ? true : false;
		if (alreadyLoggedIn && loginPageRequested) {
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		} else if (!alreadyLoggedIn && !loginPageRequested) {
			response.sendRedirect(request.getContextPath() + "/login?msg=NotLoggedIn");
			return false;
		}
		return true;
	}

}