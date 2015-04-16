package com.dianping.zebra.admin.filter;

import org.jboss.netty.handler.codec.http.HttpResponseStatus;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Dozer on 10/28/14.
 */
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rsp = (HttpServletResponse) response;

        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("AuthorizationFilter")) {
                    cookie.setMaxAge(60 * 60 * 12);
                    cookie.setValue("AuthorizationFilter");
                    cookie.setPath("/");
                    rsp.addCookie(cookie);
                    chain.doFilter(request, response);
                    return;
                }
            }
        }
        rsp.setStatus(HttpResponseStatus.UNAUTHORIZED.getCode());
    }

    @Override
    public void destroy() {

    }

}
