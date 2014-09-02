package com.tm.wholesale.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tm.wholesale.model.Manager;

public class ManagerSessionFilter implements Filter {

	public ManagerSessionFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		Manager managerSession = (Manager) req.getSession().getAttribute("managerSession");
		System.out.println("managerSession:" + managerSession);
		
		String url = req.getRequestURL().toString();
		System.out.println("ManagerSessionFilter: " + url);
		
		if (managerSession != null) {
			chain.doFilter(request, response);
		} else {
			res.sendRedirect(req.getContextPath() + "/management");
			return;
		}

	}

	public void init(FilterConfig arg0) throws ServletException {}

}
