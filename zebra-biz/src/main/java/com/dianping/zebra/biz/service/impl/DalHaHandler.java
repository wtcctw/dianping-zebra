package com.dianping.zebra.biz.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.unidal.helper.Files;
import org.unidal.helper.Urls;

import com.dianping.cat.Cat;
import com.dianping.lion.EnvZooKeeperConfig;
import com.dianping.lion.client.ConfigCache;
import com.dianping.lion.client.LionException;
import com.dianping.zebra.biz.dao.MonitorHistoryMapper;
import com.dianping.zebra.biz.entity.MonitorHistoryEntity;
import com.dianping.zebra.biz.service.HaHandler;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class DalHaHandler implements HaHandler {

	private static final String LION_SET_CONFIG_URL = "http://lionapi.dp:8080/config2/set?env=%s&id=%s&key=%s&value=%s";

	private static String DEFAULT_ENV = EnvZooKeeperConfig.getEnv();

	private static final String ID = "2";

	private static final String UP = "true";

	private static final String DOWN = "false";

	@Autowired
	private MonitorHistoryMapper monitorHistoryDao;

	@Autowired
	private DataSourceTransactionManager transactionManager;

	private DefaultTransactionDefinition def = new DefaultTransactionDefinition(
	      TransactionDefinition.PROPAGATION_REQUIRED);

	@Override
	public boolean markdown(String dsId, Operator operator) {
		MonitorHistoryEntity entity = new MonitorHistoryEntity();
		entity.setOperator(-1);
		entity.setDsId(dsId);
		entity.setWho(operator.getOperator());

		String key = String.format("ds.%s.jdbc.active", dsId);

		String value = getConfigFromZk(key);

		if (value == null || value.length() == 0 || value.equalsIgnoreCase(UP)) {
			TransactionStatus status = transactionManager.getTransaction(def);
			try {
				monitorHistoryDao.insertMonitorHistory(entity);
			} catch (Exception e) {
				Cat.logError(e);
				transactionManager.rollback(status);

				return false;
			}

			if (setConfig(DEFAULT_ENV, key, DOWN)) {
				transactionManager.commit(status);

				return true;
			} else {
				transactionManager.rollback(status);

				return false;
			}
		} else {
			return true;
		}
	}

	@Override
	public boolean markup(String dsId, Operator operator) {
		MonitorHistoryEntity entity = new MonitorHistoryEntity();
		entity.setOperator(0);
		entity.setDsId(dsId);
		entity.setWho(operator.getOperator());

		String key = String.format("ds.%s.jdbc.active", dsId);

		String value = getConfigFromZk(key);

		if (value == null || value.length() == 0 || value.equalsIgnoreCase(DOWN)) {
			TransactionStatus status = transactionManager.getTransaction(def);
			try {
				monitorHistoryDao.insertMonitorHistory(entity);
			} catch (Exception e) {
				Cat.logError(e);
				transactionManager.rollback(status);

				return false;
			}

			if (setConfig(DEFAULT_ENV, key, UP)) {
				transactionManager.commit(status);

				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	private static String sendGet(String url) {
		InputStream inputStream;
		try {
			inputStream = Urls.forIO().connectTimeout(1000).readTimeout(5000).openStream(url);
			return Files.forIO().readFrom(inputStream, "utf-8");
		} catch (IOException e) {
			return "";
		}
	}

	private static String getConfigFromZk(String key) {
		try {
			return ConfigCache.getInstance(EnvZooKeeperConfig.getZKAddress()).getProperty(key);
		} catch (LionException e) {
			return null;
		}
	}

	private static boolean setConfig(String env, String key, String value) {
		String result = null;
		try {
			result = sendGet(String.format(LION_SET_CONFIG_URL, env, ID, key, URLEncoder.encode(value, "utf-8")));
		} catch (UnsupportedEncodingException e) {
			return false;
		}

		if (result != null && result.length() > 0) {
			JsonParser parser = new JsonParser();
			JsonObject obj = parser.parse(result).getAsJsonObject();

			if (obj.get("status").getAsString().equals("success")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
