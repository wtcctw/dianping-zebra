package com.dianping.zebra.environment.filter;

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

import com.dianping.zebra.environment.util.ZebraUtil;

public class GroupDataSourceFilter implements Filter {

	private static final String DOMAIN = "domain";

	private static final String COOKIE_NAME = "zebra";

	private String domain;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
	      ServletException {

		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {

			DPDataSourceContext context = DPDataSourceContext.getContext();

			HttpServletRequest hRequest = (HttpServletRequest) request;

			// 读取cookie，有且时间未过期的话，设置context中masterFlag为true
			Cookie zebra = getCookie(hRequest);
			if (zebra != null) {
				try {
					long expireTime = ZebraUtil.decryptTimestamp(zebra.getValue());
					long now = System.currentTimeMillis();
					if (expireTime < now) {
						context.setMasterFlag(true, false);
					}
				} catch (Exception e) {
				}
			}

			try {
				chain.doFilter(request, response);

				// 从Threadlocal获取forceReadFromMaster值，如果有forceReadFromMaster，set cookie 到 response
				boolean shouldSetCookie = context.isShouldSetCookie();
				if (shouldSetCookie) {
					HttpServletResponse hResponse = (HttpServletResponse) response;
					long now = System.currentTimeMillis();
					try {
						Cookie cookie = new Cookie(COOKIE_NAME, ZebraUtil.encryptTimestamp((now)));
						if (domain != null) {
							cookie.setDomain(domain);
						}
						hResponse.addCookie(cookie);
					} catch (Exception e) {
					}
				}

			} finally {
				// 清除ThreadLocal
				DPDataSourceContext.clear();
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
		domain = filterConfig.getInitParameter(DOMAIN);
		// String configManagerType;
		// String resourceId;
		// String configManagerType;
		// GroupConfigManagerFactory.getConfigManger(configManagerType, resourceId);
	}

	@Override
	public void destroy() {
	}

}
