package com.dianping.zebra.admin.admin.service;

import java.io.IOException;
import java.io.InputStream;

import com.dianping.zebra.group.Constants;
import org.unidal.helper.Files;
import org.unidal.helper.Urls;

import com.dianping.cat.Cat;
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
			
			String name = obj.get("result").getAsJsonArray().get(0).getAsJsonObject().get("hostname").getAsString();
			
			int pos = name.indexOf(".");
			
			if(pos > 2){
				name = name.substring(0, pos - 2);
			}
			
			return name;
		} catch (Exception ignore) {
			Cat.logError(ignore);
		}

		return Constants.PHOENIX_APP_NO_NAME;
	}

	private String callHttpApi(String url) throws IOException {
		InputStream inputStream = Urls.forIO().connectTimeout(1000).readTimeout(5000).openStream(url);

		return Files.forIO().readFrom(inputStream, "utf-8");
	}
}
