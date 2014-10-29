package com.dianping.zebra.admin.filter;

import org.jboss.netty.handler.codec.http.HttpResponseStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Dozer on 10/28/14.
 */
public class AuthorizationFilter implements Filter {

	@Override public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		  throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse rsp = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		if (session.getAttribute("AuthorizationFilter") == null) {
			rsp.setStatus(HttpResponseStatus.UNAUTHORIZED.getCode());
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override public void destroy() {

	}
}
