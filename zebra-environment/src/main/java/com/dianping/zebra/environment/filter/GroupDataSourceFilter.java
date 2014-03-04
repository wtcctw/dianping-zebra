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

import com.dianping.zebra.group.config.SystemConfigManager;
import com.dianping.zebra.group.config.SystemConfigManagerFactory;

public class GroupDataSourceFilter implements Filter {

	// TODO Constants
	private static final String PARAM_CONFIG_MANAGE_TYPE = "configManageType";

	private static final String PARAM_RESOURCE_ID = "resourceId";

	private static final String DEFAULT_CONFIG_MANAGE_TYPE = "local";

	private static final String DEFAULT_RESOURCE_ID = "zebra.system";

	private static final String DEFAULT_COOKIE_NAME = "zebra";

	private static final String DEFAULT_COOKIE_DOMAIN = ".dianping.com";

	private static final long DEFAULT_ENCRYPT_SEED = 2123174217368174103L;

	private SystemConfigManager configManager;

	private String cookieName;

	private String cookieDomain;

	private long encryptSeed;

	private SimpleEncrypt encrypt;

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
					long expireTime = encrypt.decryptTimestamp(zebra.getValue());
					long now = System.currentTimeMillis();
					if (expireTime < now) {
						// TODO 能不能抽出一个方法叫 setShouldSetCookie
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
						// TODO bug
						Cookie cookie = new Cookie(cookieName,
						      encrypt.encryptTimestamp((now + DEFAULT_COOKIE_TIME_OUT * 1000)));
						if (cookieDomain != null) {
							cookie.setDomain(cookieDomain);
						} else {
							// TODO set default domain
						}

						cookie.setMaxAge(DEFAULT_COOKIE_TIME_OUT);
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
			resourceId = DEFAULT_RESOURCE_ID;
		}
		String configManagerType = filterConfig.getInitParameter(PARAM_CONFIG_MANAGE_TYPE);
		if (configManagerType == null) {
			configManagerType = DEFAULT_CONFIG_MANAGE_TYPE;
		}
		
		configManager = SystemConfigManagerFactory.getConfigManger(configManagerType, resourceId);

		this.cookieName = configManager.getSystemConfig().getCookieName();
		if (this.cookieName == null) {
			this.cookieName = DEFAULT_COOKIE_NAME;
		}

		this.cookieDomain = configManager.getSystemConfig().getCookieDomain();
		if (this.cookieDomain == null) {
			this.cookieDomain = DEFAULT_COOKIE_DOMAIN;
		}

		String encryptSeedStr = configManager.getSystemConfig().getEncryptSeed();
		if (encryptSeedStr != null) {
			try {
				this.encryptSeed = Long.parseLong(encryptSeedStr);
			} catch (RuntimeException e) {
				throw new IllegalArgumentException("encryptSeed should be numberic", e);
			}
		} else {
			this.encryptSeed = DEFAULT_ENCRYPT_SEED;
		}

		this.encrypt = new SimpleEncrypt(encryptSeed);
	}

	@Override
	public void destroy() {
	}

}
