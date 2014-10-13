package com.dianping.zebra.admin.admin.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.unidal.helper.Files;
import org.unidal.helper.Urls;

import com.dianping.cat.Cat;
import com.dianping.zebra.group.Constants;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CmdbServiceImpl implements CmdbService {

	private final String URL = "http://api.cmdb.dp/api/v0.1/ci/s?q=_type:(server;vserver),private_ip:%s&fl=hostname";

	@Override
	public String getAppName(String ip) {
		String url = String.format(URL, ip);

		try {
			String result = callHttpApi(url);
			JsonParser parser = new JsonParser();
			JsonObject obj = parser.parse(result).getAsJsonObject();
			JsonArray array = obj.get("result").getAsJsonArray();
			if (array.size() > 0) {
				String name = array.get(0).getAsJsonObject().get("hostname").getAsString();

				int pos = name.indexOf(".");

				if (pos > 2) {
					name = name.substring(0, pos - 2);
				}

				return name;
			}
		} catch (Exception ignore) {
			Cat.logError(ignore);
		}

		return Constants.PHOENIX_APP_NO_NAME;
	}

	public Map<String, String> getMultiAppName(List<String> ips) {
		Map<String, String> ipNames = new HashMap<String, String>();

		for (String ip : ips) {
			ipNames.put(ip, getAppName(ip));
		}

		return ipNames;
	}

	private String callHttpApi(String url) throws IOException {
		InputStream inputStream = Urls.forIO().connectTimeout(1000).readTimeout(5000).openStream(url);

		return Files.forIO().readFrom(inputStream, "utf-8");
	}
}
