package com.dianping.zebra.group.config.system.entity;

import com.dianping.zebra.group.config.system.BaseEntity;
import com.dianping.zebra.group.config.system.IVisitor;

public class SystemConfig extends BaseEntity<SystemConfig> {
	private volatile int m_healthCheckInterval = 30;

	private volatile int m_maxErrorCounter = 3;

	private volatile String m_cookieDomain = ".dianping.com";

	private volatile String m_cookieName = "_zd_";

	private volatile int m_retryTimes = 0;

	private volatile String m_encryptSeed = "2123174217368174103L";

	private volatile int m_cookieExpiredTime = 2;

	private volatile String m_globalBlackList = "";

	private volatile String m_appBlackList = "";

	public SystemConfig() {
	}

	@Override
	public void accept(IVisitor visitor) {
		visitor.visitSystemConfig(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SystemConfig) {
			SystemConfig _o = (SystemConfig) obj;
			int healthCheckInterval = _o.getHealthCheckInterval();
			int maxErrorCounter = _o.getMaxErrorCounter();
			String cookieDomain = _o.getCookieDomain();
			String cookieName = _o.getCookieName();
			int retryTimes = _o.getRetryTimes();
			String encryptSeed = _o.getEncryptSeed();
			int cookieExpiredTime = _o.getCookieExpiredTime();
			String globalBlackList = _o.getGlobalBlackList();
			String appBlackList = _o.getAppBlackList();
			boolean result = true;

			result &= (m_healthCheckInterval == healthCheckInterval);
			result &= (m_maxErrorCounter == maxErrorCounter);
			result &= (m_cookieDomain == cookieDomain || m_cookieDomain != null && m_cookieDomain.equals(cookieDomain));
			result &= (m_cookieName == cookieName || m_cookieName != null && m_cookieName.equals(cookieName));
			result &= (m_retryTimes == retryTimes);
			result &= (m_encryptSeed == encryptSeed || m_encryptSeed != null && m_encryptSeed.equals(encryptSeed));
			result &= (m_cookieExpiredTime == cookieExpiredTime);
			result &= (m_globalBlackList == globalBlackList || m_globalBlackList != null && m_globalBlackList
				.equals(globalBlackList));
			result &= (m_appBlackList == appBlackList || m_appBlackList != null && m_appBlackList.equals(appBlackList));

			return result;
		}

		return false;
	}

	public String getAppBlackList() {
		return m_appBlackList;
	}

	public String getCookieDomain() {
		return m_cookieDomain;
	}

	public int getCookieExpiredTime() {
		return m_cookieExpiredTime;
	}

	public String getCookieName() {
		return m_cookieName;
	}

	public String getEncryptSeed() {
		return m_encryptSeed;
	}

	public String getGlobalBlackList() {
		return m_globalBlackList;
	}

	public int getHealthCheckInterval() {
		return m_healthCheckInterval;
	}

	public int getMaxErrorCounter() {
		return m_maxErrorCounter;
	}

	public int getRetryTimes() {
		return m_retryTimes;
	}

	@Override
	public int hashCode() {
		int hash = 0;

		hash = hash * 31 + m_healthCheckInterval;
		hash = hash * 31 + m_maxErrorCounter;
		hash = hash * 31 + (m_cookieDomain == null ? 0 : m_cookieDomain.hashCode());
		hash = hash * 31 + (m_cookieName == null ? 0 : m_cookieName.hashCode());
		hash = hash * 31 + m_retryTimes;
		hash = hash * 31 + (m_encryptSeed == null ? 0 : m_encryptSeed.hashCode());
		hash = hash * 31 + m_cookieExpiredTime;
		hash = hash * 31 + (m_globalBlackList == null ? 0 : m_globalBlackList.hashCode());
		hash = hash * 31 + (m_appBlackList == null ? 0 : m_appBlackList.hashCode());

		return hash;
	}

	@Override
	public void mergeAttributes(SystemConfig other) {
	}

	public SystemConfig setAppBlackList(String appBlackList) {
		m_appBlackList = appBlackList;
		return this;
	}

	public SystemConfig setCookieDomain(String cookieDomain) {
		m_cookieDomain = cookieDomain;
		return this;
	}

	public SystemConfig setCookieExpiredTime(int cookieExpiredTime) {
		m_cookieExpiredTime = cookieExpiredTime;
		return this;
	}

	public SystemConfig setCookieName(String cookieName) {
		m_cookieName = cookieName;
		return this;
	}

	public SystemConfig setEncryptSeed(String encryptSeed) {
		m_encryptSeed = encryptSeed;
		return this;
	}

	public SystemConfig setGlobalBlackList(String globalBlackList) {
		m_globalBlackList = globalBlackList;
		return this;
	}

	public SystemConfig setHealthCheckInterval(int healthCheckInterval) {
		m_healthCheckInterval = healthCheckInterval;
		return this;
	}

	public SystemConfig setMaxErrorCounter(int maxErrorCounter) {
		m_maxErrorCounter = maxErrorCounter;
		return this;
	}

	public SystemConfig setRetryTimes(int retryTimes) {
		m_retryTimes = retryTimes;
		return this;
	}

}
