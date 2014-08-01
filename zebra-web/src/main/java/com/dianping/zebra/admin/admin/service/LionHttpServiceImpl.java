package com.dianping.zebra.admin.admin.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.unidal.helper.Files;
import org.unidal.helper.Urls;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LionHttpServiceImpl implements LionHttpService {

	private static final String ID = "2";

	private final String LION_SERVER = "http://lionapi.dp:8080";

	private String callLionHttpApi(String url) throws IOException {
		InputStream inputStream = Urls.forIO().connectTimeout(1000).readTimeout(5000).openStream(url);

		return Files.forIO().readFrom(inputStream, "utf-8");
	}

	@Override
	public HashMap<String, String> getConfigByProject(String env, String project) throws IOException {
		String url = String.format("%s/config2/get?env=%s&project=%s&id=%s", LION_SERVER, env, project, ID);
		String result = callLionHttpApi(url);

		try {
			JsonParser parser = new JsonParser();
			JsonObject obj = parser.parse(result).getAsJsonObject();
			HashMap<String, String> maps = new HashMap<String, String>();

			if (obj.get("status").getAsString().equals("success")) {
				JsonObject asJsonObject = obj.get("result").getAsJsonObject();

				for (Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
					maps.put(entry.getKey(), entry.getValue().getAsString());
				}
				
				return maps;
			}
		} catch (Throwable t) {
			throw new IOException("cannot get config from lion", t);
		}

		throw new IOException("cannot get config from lion");
	}

	@Override
	public boolean setConfig(String env, String key, String value) {
		try {
			String result = callLionHttpApi(String.format("%s/config2/set?env=%s&id=%s&key=%s&value=%s", LION_SERVER, env,
			      ID, key, value));

			JsonParser parser = new JsonParser();
			JsonObject obj = parser.parse(result).getAsJsonObject();

			if (obj.get("status").getAsString().equals("success")) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public String getConfig(String env, String key) throws IOException {
		String url = String.format("%s/config2/get?env=%s&key=%s&id=%s", LION_SERVER, env, key, ID);
		String result = callLionHttpApi(url);

		try {
			JsonParser parser = new JsonParser();
			JsonObject obj = parser.parse(result).getAsJsonObject();
			return obj.get("result").getAsString();
		} catch (Throwable t) {
			throw new IOException("cannot get config from lion", t);
		}
	}
}
