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

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
	      ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			try {
				HttpServletRequest hRequest = (HttpServletRequest) request;
				HttpServletResponse hResponse = (HttpServletResponse) response;
				SimpleEncrypt encrypt = getEncrypt();

				String cookieName = configManager.getSystemConfig().getCookieName();
				// 读取cookie，有且时间未过期的话，设置context中masterFlag为true
				Cookie zebra = getCookie(cookieName, hRequest);
				if (zebra != null) {
					try {
						long expireTime = encrypt.decryptTimestamp(zebra.getValue());
						long now = System.currentTimeMillis();
						if (now < expireTime) {
							CustomizedReadWriteStrategyImpl.setForceReadFromMaster(false);
						}
					} catch (Exception e) {
					}
				}

				// pigeon service需要存在trackerContext才会在调用返回时对trackerContext设值，所以此处事先创建一下
				CustomizedReadWriteStrategyImpl.createTrackerContextIfNotExists();

				chain.doFilter(request, response);

				// 从Threadlocal获取forceReadFromMaster值，如果有forceReadFromMaster，set cookie 到 response
				boolean shouldSetCookie = CustomizedReadWriteStrategyImpl.shouldSetCookie();
				if (shouldSetCookie) {
					int cookieExpiredTime = configManager.getSystemConfig().getCookieExpiredTime();
					long expiredTime = System.currentTimeMillis() + cookieExpiredTime * 1000;
					try {
						Cookie cookie = new Cookie(cookieName, encrypt.encryptTimestamp(expiredTime));
						cookie.setDomain(configManager.getSystemConfig().getCookieDomain());
						cookie.setMaxAge(cookieExpiredTime);

						hResponse.addCookie(cookie);
					} catch (Exception e) {
					}
				}
			} finally {
				CustomizedReadWriteStrategyImpl.clear();
			}
		}
	}

	private Cookie getCookie(String name, HttpServletRequest hRequest) {
		Cookie[] cookies = hRequest.getCookies();

		if (cookies != null) {
			Cookie zebraCookie = null;
			for (Cookie cookie : cookies) {
				String cookieName = cookie.getName();
				if (name.equalsIgnoreCase(cookieName)) {
					zebraCookie = cookie;
					break;
				}
			}
			return zebraCookie;
		} else {
			return null;
		}
	}

	private SimpleEncrypt getEncrypt() {
		String encryptSeedStr = configManager.getSystemConfig().getEncryptSeed();
		long encryptSeed;
		try {
			encryptSeed = Long.parseLong(encryptSeedStr);
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("encryptSeed should be numberic", e);
		}

		return new SimpleEncrypt(encryptSeed);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String resourceId = filterConfig.getInitParameter(PARAM_RESOURCE_ID);
		if (resourceId == null) {
			resourceId = Constants.DEFAULT_SYSTEM_RESOURCE_ID;
		}
		String configManagerType = filterConfig.getInitParameter(PARAM_CONFIG_MANAGE_TYPE);
		if (configManagerType == null) {
			configManagerType = Constants.CONFIG_MANAGER_TYPE_REMOTE;
		}

		configManager = SystemConfigManagerFactory.getConfigManger(configManagerType, resourceId);
	}

}
