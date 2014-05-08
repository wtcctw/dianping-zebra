package com.dianping.zebra.admin.admin.service;

import java.io.IOException;
import java.util.HashMap;

public interface LionHttpService {

	public boolean setConfig(String env, String project, String key, String value);

	public HashMap<String, String> getKeyValuesByPrefix(String env, String keyPrefix) throws IOException;
}
