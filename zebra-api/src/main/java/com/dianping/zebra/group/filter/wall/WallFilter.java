package com.dianping.zebra.group.filter.wall;

import com.dianping.avatar.tracker.ExecutionContextHolder;
import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.SystemConfigManager;
import com.dianping.zebra.group.config.SystemConfigManagerFactory;
import com.dianping.zebra.group.config.system.entity.SystemConfig;
import com.dianping.zebra.group.filter.DefaultJdbcFilter;
import com.dianping.zebra.group.filter.JdbcContext;
import com.dianping.zebra.group.filter.delegate.FilterFunctionWithSQLException;
import com.dianping.zebra.group.spring.SpringBeanHelper;
import com.dianping.zebra.group.util.StringUtils;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dozer on 9/24/14.
 */
public class WallFilter extends DefaultJdbcFilter {
	private static final int MAX_ID_LENGTH = 8;

	protected final static Pattern ID_PATTERN = Pattern
		  .compile(".*(\\/\\*z:)([a-zA-Z0-9]{" + MAX_ID_LENGTH + "})(\\*\\/).*");

	private static final String SQL_STATEMENT_NAME = "sql_statement_name";

	protected volatile static Set<String> blackList = new HashSet<String>();

	private static Map<String, String> generatedIdCache = new ConcurrentHashMap<String, String>();

	protected String configManagerType = Constants.CONFIG_MANAGER_TYPE_REMOTE;

	private SystemConfigManager systemConfigManager;

	protected String addIdToSql(String sql, JdbcContext metaData) throws SQLException {
		try {
			String id = generateId(metaData);
			checkBlackList(sql, id);
			return String.format("/*z:%s*/%s", id, sql);
		} catch (NoSuchAlgorithmException e) {
			return sql;
		}
	}

	@Override public void init() {
		super.init();
		this.initWallFilter();
		this.initBlackList();
	}

	private void initWallFilter() {
		Map<String, WallFilterConfig> configs = SpringBeanHelper.getBeanByClass(WallFilterConfig.class);
		for (WallFilterConfig config : configs.values()) {
			this.configManagerType = config.getConfigManagerType();
		}
	}

	private void initBlackList() {
		this.systemConfigManager = SystemConfigManagerFactory.getConfigManger(configManagerType,
			  Constants.DEFAULT_SYSTEM_RESOURCE_ID);

		buildBlackListFromSystemConfig(this.systemConfigManager.getSystemConfig());

		this.systemConfigManager.addListerner(new PropertyChangeListener() {
			@Override public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
				if (propertyChangeEvent.getPropertyName()
					  .startsWith(Constants.DEFAULT_DATASOURCE_ZEBRA_SQL_BLACKLIST_PRFIX)) {
					buildBlackListFromSystemConfig(systemConfigManager.getSystemConfig());
				}
			}
		});
	}

	private synchronized void buildBlackListFromSystemConfig(SystemConfig config) {
		Set<String> newblackList = new HashSet<String>();
		if (StringUtils.isNotBlank(config.getGlobalBlackList())) {
			newblackList.addAll(Arrays.asList(config.getGlobalBlackList().split(",")));
		}
		if (StringUtils.isNotBlank(config.getAppBlackList())) {
			newblackList.addAll(Arrays.asList(config.getAppBlackList().split(",")));
		}
		blackList = newblackList;
	}

	protected void checkBlackList(String sql, String id) throws SQLException {
		if (StringUtils.isNotBlank(id) && blackList.contains(id)) {
			throw new SQLException("This SQL is in blacklist. Please check it from dba! SQL:" + sql);
		}
	}

	@Override
	public <S, T> T execute(JdbcContext metaData, S source, FilterFunctionWithSQLException<S, T> action)
		  throws SQLException {
		T result = super.execute(metaData, source, action);
		return result;
	}

	protected String generateId(JdbcContext metaData) throws NoSuchAlgorithmException {
		String token = ExecutionContextHolder.getContext().get(SQL_STATEMENT_NAME);
		if (StringUtils.isBlank(token)) {
			token = metaData.getSql();
		}

		if (StringUtils.isBlank(token)) {
			return null;
		}

		if (metaData.getRealJdbcContext() != null && metaData.getRealJdbcContext().getDataSourceId() != null) {
			token = String.format("/*%s*/%s", metaData.getRealJdbcContext().getDataSourceId(), token);
		}

		return generateId(token);
	}

	private String generateId(String token) throws NoSuchAlgorithmException {
		String result;
		boolean needToCache = true;
		if (token != null && token.length() > 1024) {
			needToCache = false;
		}
		if (generatedIdCache.size() > 1024) {
			needToCache = false;
		}

		if (needToCache) {
			result = generatedIdCache.get(token);
			if (result != null) {
				return result;
			}
		}

		result = StringUtils.md5(token).substring(0, MAX_ID_LENGTH);

		if (needToCache) {
			generatedIdCache.put(token, result);
		}

		return result;
	}

	protected String getIdFromSQL(String sql) {
		if (StringUtils.isBlank(sql)) {
			return null;
		}
		Matcher matcher = ID_PATTERN.matcher(sql);
		if (matcher.matches()) {
			return matcher.group(2);
		} else {
			return null;
		}
	}

	public int getOrder() {
		return MIN_ORDER;
	}

	@Override
	public <S> String sql(JdbcContext metaData, S source, FilterFunctionWithSQLException<S, String> action)
		  throws SQLException {
		String result = super.sql(metaData, source, action);
		result = addIdToSql(result, metaData);
		return result;
	}
}