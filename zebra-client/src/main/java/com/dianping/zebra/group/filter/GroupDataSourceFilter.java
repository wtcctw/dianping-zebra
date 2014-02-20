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

import com.dianping.lion.EnvZooKeeperConfig;
import com.dianping.lion.client.ConfigCache;
import com.dianping.lion.client.LionException;
import com.dianping.zebra.group.router.GroupDataSourceContext;
import com.dianping.zebra.group.util.ZebraUtil;

public class GroupDataSourceFilter implements Filter {

	private static final String DOMAIN = "domain";

	private static final String COOKIE_NAME = "zebra";

	private String domain;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
	      ServletException {

		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {

			GroupDataSourceContext context = GroupDataSourceContext.get();

			HttpServletRequest hRequest = (HttpServletRequest) request;

			// 读取cookie，有且时间未过期的话，设置context中forceReadFromMasterByCookie为true
			Cookie zebra = getCookie(hRequest);
			if (zebra != null) {
				try {
					long expireTime = Long.parseLong(ZebraUtil.decrypt(zebra.getValue()));
					long now = System.currentTimeMillis();
					if (expireTime < now) {
						context.setForceReadFromMasterByCookie(true);
					}
				} catch (Exception e) {
				}
			}

			try {
				chain.doFilter(request, response);

				// 从Threadlocal获取forceReadFromMaster值，如果有forceReadFromMaster，set cookie 到 response
				boolean forceReadFromMaster = context.isForceReadFromMaster();
				if (forceReadFromMaster) {
					HttpServletResponse hResponse = (HttpServletResponse) response;
					long now = System.currentTimeMillis();
					try {
						Cookie cookie = new Cookie(COOKIE_NAME, ZebraUtil.encrypt(String.valueOf(now)));
						if (domain != null) {
							cookie.setDomain(domain);
						}
						hResponse.addCookie(cookie);
					} catch (Exception e) {
					}
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
		domain = filterConfig.getInitParameter(DOMAIN);
		if (domain == null) {
			try {
				Class.forName("com.dianping.lion.client.ConfigCache");
				ConfigCache cc = ConfigCache.getInstance(EnvZooKeeperConfig.getZKAddress());
				domain = cc.getProperty("domain.lion.xxx");// TODO lion domain key
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Without lion, 'domain' should be set from filter config", e);
			} catch (LionException e) {
				throw new RuntimeException("Init lion error.", e);
			}
		}
	}

	@Override
	public void destroy() {
	}

}
