package com.dianping.zebra.group.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.zebra.group.router.GroupDataSourceContext;
import com.dianping.zebra.group.util.ZebraCookieUtil;

public class GroupDataSourceFilter implements Filter {

	private static final String COOKIE_NAME = "zebra";

	private static final Logger LOG = LoggerFactory.getLogger(GroupDataSourceFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
	      ServletException {

		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {

			GroupDataSourceContext context = GroupDataSourceContext.get();

			try {
				HttpServletRequest hRequest = (HttpServletRequest) request;

				// 读取cookie，有且时间未过期的话，设置context中forceReadFromMasterByCookie为true
				Cookie zebra = getCookie(hRequest);
				if (zebra != null) {
					long expireTime = ZebraCookieUtil.decryptTimestamp(zebra.getValue());
					long now = System.currentTimeMillis();
					if (expireTime < now) {
						context.setForceReadFromMasterByCookie(true);
					}
				}

			} catch (RuntimeException e) {
				LOG.warn(e.getMessage(), e);
			}

			try {
				chain.doFilter(request, response);

				// 从Threadlocal获取forceReadFromMaster值，如果有forceReadFromMaster，set cookie 到 response
				boolean forceReadFromMaster = context.isForceReadFromMaster();
				if (forceReadFromMaster) {
					HttpServletResponse hResponse = (HttpServletResponse) response;
					long now = System.currentTimeMillis();
					Cookie cookie = new Cookie(COOKIE_NAME, ZebraCookieUtil.encryptTimestamp(now));
					hResponse.addCookie(cookie);
				}

			} finally {
				// 清除ThreadLocal
				GroupDataSourceContext.clear();
			}
		}
	}

	private Cookie getCookie(HttpServletRequest hRequest) {
		Cookie zebraCookie = null;
		Cookie[] cookies = hRequest.getCookies();
		for (Cookie cookie : cookies) {
			String cookieName = cookie.getName();
			if (COOKIE_NAME.equalsIgnoreCase(cookieName)) {
				zebraCookie = cookie;
				break;
			}
		}
		return zebraCookie;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
