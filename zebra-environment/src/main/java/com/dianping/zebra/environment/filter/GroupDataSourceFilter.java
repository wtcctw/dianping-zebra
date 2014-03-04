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

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.SystemConfigManager;
import com.dianping.zebra.group.config.SystemConfigManagerFactory;

public class GroupDataSourceFilter implements Filter {

	private static final String PARAM_CONFIG_MANAGE_TYPE = "configManageType";

	private static final String PARAM_RESOURCE_ID = "resourceId";

	private SystemConfigManager configManager;

	private String cookieName;

	private String cookieDomain;

	private long encryptSeed;

	private int cookieExpiredTime;

	private SimpleEncrypt encrypt;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
	      ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			try {
				DPDataSourceContext context = DPDataSourceContext.getContext();
				HttpServletRequest hRequest = (HttpServletRequest) request;
				HttpServletResponse hResponse = (HttpServletResponse) response;

				// 读取cookie，有且时间未过期的话，设置context中masterFlag为true
				Cookie zebra = getCookie(hRequest);
				if (zebra != null) {
					try {
						long expireTime = encrypt.decryptTimestamp(zebra.getValue());
						long now = System.currentTimeMillis();
						if (expireTime < now) {
							context.setMasterFlag(true, false);
						}
					} catch (Exception e) {
					}
				}

				chain.doFilter(request, response);
				
				// 从Threadlocal获取forceReadFromMaster值，如果有forceReadFromMaster，set cookie 到 response
				boolean shouldSetCookie = context.isShouldSetCookie();
				if (shouldSetCookie) {
					long expiredTime = System.currentTimeMillis() + cookieExpiredTime * 1000;
					try {
						Cookie cookie = new Cookie(cookieName, encrypt.encryptTimestamp(expiredTime));
						cookie.setDomain(cookieDomain);
						cookie.setMaxAge(cookieExpiredTime);

						hResponse.addCookie(cookie);
					} catch (Exception e) {
					}
				}
			} finally {
				DPDataSourceContext.clear();
			}
		}
	}

	private Cookie getCookie(HttpServletRequest hRequest) {
		Cookie[] cookies = hRequest.getCookies();

		if (cookies != null) {
			Cookie zebraCookie = null;
			for (Cookie cookie : cookies) {
				String cookieName = cookie.getName();
				if (this.cookieName.equalsIgnoreCase(cookieName)) {
					zebraCookie = cookie;
					break;
				}
			}
			return zebraCookie;
		} else {
			return null;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String resourceId = filterConfig.getInitParameter(PARAM_RESOURCE_ID);
		if (resourceId == null) {
			resourceId = Constants.RESOURCE_ID_SYSTEM_CONFIG;
		}
		String configManagerType = filterConfig.getInitParameter(PARAM_CONFIG_MANAGE_TYPE);
		if (configManagerType == null) {
			configManagerType = Constants.LOCAL_CONFIG_MANAGER_TYPE;
		}

		configManager = SystemConfigManagerFactory.getConfigManger(configManagerType, resourceId);

		this.cookieName = configManager.getSystemConfig().getCookieName();
		this.cookieDomain = configManager.getSystemConfig().getCookieDomain();
		this.cookieExpiredTime = configManager.getSystemConfig().getCookieExpiredTime();

		String encryptSeedStr = configManager.getSystemConfig().getEncryptSeed();
		try {
			this.encryptSeed = Long.parseLong(encryptSeedStr);
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("encryptSeed should be numberic", e);
		}

		this.encrypt = new SimpleEncrypt(encryptSeed);
	}

	@Override
	public void destroy() {
	}

}
