package com.dianping.zebra.admin.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public interface LionService {
	Set<String> getAllEnv();

	Set<String> getProductEnv();

	Set<String> getDevEnv();
	
	String getEnv();

	boolean isProduct();

	boolean isDev();

	boolean createKey(String project, String key) throws IOException;

	String getConfigByHttp(String env, String key) throws IOException;
	
	String getConfigFromZk(String key);

	HashMap<String, String> getConfigByProject(String env, String project) throws IOException;

	void removeUnset(String key);

	boolean setConfig(String env, String key, String value);
}
