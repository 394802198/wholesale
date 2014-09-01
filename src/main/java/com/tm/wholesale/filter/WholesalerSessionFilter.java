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
import com.tm.wholesale.model.Wholesaler;

public class WholesalerSessionFilter implements Filter {

	public WholesalerSessionFilter() {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		Wholesaler wholesalerSession = (Wholesaler) req.getSession().getAttribute("wholesalerSession");
		System.out.println("wholesalerSession:" + wholesalerSession);
		
		String url = req.getRequestURL().toString();
		System.out.println("WholesalerSessionFilter: " + url);
		
		if (wholesalerSession != null) {
			chain.doFilter(request, response);
		} else {
			res.sendRedirect(req.getContextPath() + "/sign-in");
			return;
		}

	}

	public void init(FilterConfig arg0) throws ServletException {}

}
