package com.dianping.zebra.admin.admin.service;

import java.io.IOException;
import java.util.HashMap;

public interface LionHttpService {

	public boolean setConfig(String env, String key, String value);

	public String getConfig(String env, String key) throws IOException;

	public HashMap<String, String> getConfigByProject(String env, String project) throws IOException;
}
