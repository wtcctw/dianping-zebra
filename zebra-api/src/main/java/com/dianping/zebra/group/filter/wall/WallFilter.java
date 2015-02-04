package com.dianping.zebra.group.filter.wall;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.config.SystemConfigManager;
import com.dianping.zebra.group.config.SystemConfigManagerFactory;
import com.dianping.zebra.group.config.system.entity.SystemConfig;
import com.dianping.zebra.group.datasources.SingleConnection;
import com.dianping.zebra.group.filter.DefaultJdbcFilter;
import com.dianping.zebra.group.filter.JdbcFilter;
import com.dianping.zebra.group.util.StringUtils;

/**
 * Created by Dozer on 9/24/14.
 */
public class WallFilter extends DefaultJdbcFilter {
	private static final int MAX_ID_LENGTH = 8;

	private static Map<String, String> sqlIDCache = new ConcurrentHashMap<String, String>(1024);

	private static Set<String> blackList = new HashSet<String>();

	private SystemConfigManager systemConfigManager;

	public static Set<String> getBlackList() {
		return blackList;
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

	protected String generateId(SingleConnection conn, String sql) throws NoSuchAlgorithmException {
		String token = String.format("/*%s*/%s", conn.getId(), sql);
		String resultId = sqlIDCache.get(String.format("/*%s*/%s", conn.getId(), sql));

		if (resultId != null) {
			return resultId;
		} else {
			resultId = StringUtils.md5(token).substring(0, MAX_ID_LENGTH);
			sqlIDCache.put(token, resultId);

			return resultId;
		}
	}

	public int getOrder() {
		return MIN_ORDER;
	}

	@Override
	public void init() {
		super.init();
		this.initBlackList();
	}

	private void initBlackList() {
		this.systemConfigManager = SystemConfigManagerFactory.getConfigManger(configManagerType,
		      Constants.DEFAULT_SYSTEM_RESOURCE_ID);

		buildBlackListFromSystemConfig(this.systemConfigManager.getSystemConfig());

		this.systemConfigManager.addListerner(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
				if (propertyChangeEvent.getPropertyName()
				      .startsWith(Constants.DEFAULT_DATASOURCE_ZEBRA_SQL_BLACKLIST_PRFIX)) {
					buildBlackListFromSystemConfig(systemConfigManager.getSystemConfig());
				}
			}
		});
	}

	@Override
	public String sql(SingleConnection conn, String sql, boolean isPreparedStmt, JdbcFilter chain) throws SQLException {
		if (chain != null) {
			sql = chain.sql(conn, sql, isPreparedStmt, chain);
		}

		if (isPreparedStmt && conn != null && StringUtils.isNotBlank(sql)) {
			try {
				String id = generateId(conn, sql);
				
				checkBlackList(sql, id);

				return String.format("/*id:%s*/%s", id, sql);
			} catch (NoSuchAlgorithmException e) {
				return sql;
			}
		} else {
			return sql;
		}
	}
}