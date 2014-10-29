package com.dianping.zebra.admin.admin.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public interface LionHttpService {
	Set<String> getAllEnv();

	Set<String> getProductEnv();

	Set<String> getDevEnv();

	boolean isProduct();

	boolean isDev();

	boolean createKey(String project, String key) throws IOException;

	String getConfig(String env, String key) throws IOException;

	HashMap<String, String> getConfigByProject(String env, String project) throws IOException;

	void removeUnset(String key);

	boolean setConfig(String env, String key, String value);
}
