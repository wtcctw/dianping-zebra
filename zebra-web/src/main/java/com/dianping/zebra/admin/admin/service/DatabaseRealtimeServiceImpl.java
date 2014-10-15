package com.dianping.zebra.admin.admin.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.plexus.logging.LogEnabled;
import org.codehaus.plexus.logging.Logger;
import org.unidal.helper.Files;
import org.unidal.helper.Urls;
import org.unidal.lookup.annotation.Inject;

import com.dianping.cat.Cat;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DatabaseRealtimeServiceImpl implements DatabaseRealtimeService, LogEnabled {

	private final String URL_DB = "http://tools.dba.dp/get_useiplist_of_db.php?dbname=%s";

	private final String URL_ALL = "http://tools.dba.dp/get_useiplist_of_db.php?type=all";

	@Inject
	private CmdbService m_cmdbService;

	private Logger m_logger;

	@Override
	public Map<String, String> getConnectedIps(String database) {
		String dbName = database.toLowerCase();
		String url = String.format(URL_DB, dbName);
		try {
			String result = callHttpApi(url);

			JsonParser parser = new JsonParser();
			JsonObject obj = parser.parse(result).getAsJsonObject();

			int status = obj.get("status").getAsInt();
			if (status == 0) {
				List<String> ips = new ArrayList<String>();

				JsonArray ipArray = obj.get("info").getAsJsonObject().get(dbName).getAsJsonArray();
				for (int i = 0; i < ipArray.size(); i++) {
					String ip = ipArray.get(i).getAsString();

					if (ip.startsWith("10.1") || ip.startsWith("10.2")) {
						ips.add(ip);
					}
				}

				return m_cmdbService.getMultiAppName(ips);
			} else {
				throw new IOException(obj.get("message").getAsString());
			}
		} catch (Exception e) {
			Cat.logError(e);
			return null;
		}
	}

	private String callHttpApi(String url) throws IOException {
		InputStream inputStream = Urls.forIO().connectTimeout(1000).readTimeout(5000).openStream(url);

		return Files.forIO().readFrom(inputStream, "utf-8");
	}

	@Override
	public Map<String, Map<String, String>> getAllConnectedIps() {
		try {
			String content = callHttpApi(URL_ALL);

			JsonParser parser = new JsonParser();
			JsonObject obj = parser.parse(content).getAsJsonObject();

			int status = obj.get("status").getAsInt();
			if (status == 0) {
				Map<String, Map<String, String>> result = new HashMap<String, Map<String, String>>();
				List<String> ips = new ArrayList<String>();

				JsonObject databases = obj.get("info").getAsJsonObject();

				for (Entry<String, JsonElement> entry : databases.entrySet()) {
					JsonArray ipArray = entry.getValue().getAsJsonArray();
					for (int i = 0; i < ipArray.size(); i++) {
						String ip = ipArray.get(i).getAsString();

						if (ip.startsWith("10.1") || ip.startsWith("10.2")) {
							ips.add(ip);
						}
					}

					m_logger.info("database begin : " + entry.getKey());
					result.put(entry.getKey(), m_cmdbService.getMultiAppName(ips));
					m_logger.info("database end : " + entry.getKey());
				}

				return result;
			} else {
				throw new IOException(obj.get("message").getAsString());
			}
		} catch (Exception e) {
			Cat.logError(e);
			return null;
		}
	}

	@Override
	public void enableLogging(Logger logger) {
		m_logger = logger;
	}
}
