package com.dianping.zebra.admin.admin.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.unidal.helper.Files;
import org.unidal.helper.Urls;

import com.google.gson.Gson;

public class LionHttpServiceImpl implements LionHttpService {

	private static final String ID = "2";

	private final String LION_SERVER = "http://lionapi.dp:8080";

	private String callLionHttpApi(String url) throws IOException {
		InputStream inputStream = Urls.forIO().connectTimeout(100).readTimeout(100).openStream(url);

		return Files.forIO().readFrom(inputStream, "utf-8");
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getKeyValuesByPrefix(String env, String keyPrefix) throws IOException {
		String url = String.format("%s/config/get?env=%s&prefix=%s&id=%s", LION_SERVER, env, keyPrefix, ID);
		String result = callLionHttpApi(url);

		if (result != null && result.length() > 4) {
			String status = result.substring(0, 1);

			if (status != null && status.equals("0")) {
				String jsonObject = result.substring(2);
				Gson gson = new Gson();
				HashMap<String, String> map = new HashMap<String, String>();

				return (HashMap<String, String>) gson.fromJson(jsonObject, map.getClass());
			}
		}

		throw new IOException("cannot get config from lion");
	}

	@Override
	public boolean setConfig(String env, String project, String key, String value) {
		try {
			String result = callLionHttpApi(String.format("%s/setconfig?e=%s&id=%s&p=%s&k=%s&v=%s&ef=1", LION_SERVER, env,
			      ID, project, key, value));

			if (result != null && result.charAt(0) == '0') {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			return false;
		}
	}
}
