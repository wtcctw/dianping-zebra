package com.dianping.zebra.admin.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import com.dianping.zebra.Constants;
import com.dianping.zebra.group.util.Splitters;

@Service
public class DalServiceImpl implements DalService {

	private final String PORJECT = Constants.DEFAULT_DATASOURCE_SINGLE_PRFIX;

	@Autowired
	private LionHttpService m_lionHttpService;

	private Set<String> findDataSources(String ip, String port, String database, HashMap<String, String> keyValues) {
		String content = ip + ":" + port;
		if (database != null) {
			content = content + "/" + database;
		}

		Set<String> dataSources = new HashSet<String>();

		for (Entry<String, String> entry : keyValues.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			if (key != null && key.contains(".jdbc.url") && value != null && value.contains(content.trim())) {
				int begin = "ds.".length();
				int end = key.indexOf(".jdbc.url");

				dataSources.add(key.substring(begin, end));
			}
		}

		return dataSources;
	}

	private void markDBInternal(DalResult result, Set<String> dataSources, boolean isActive) {
		Set<String> unMarkedDataSources = new HashSet<String>();
		Set<String> markedDataSource = new HashSet<String>();
		String value = "false";
		if (isActive) {
			value = "true";
		}

		for (String db : dataSources) {
			boolean isSuccess = m_lionHttpService.setConfig(result.getEnv(), "ds." + db + ".jdbc.active", value);

			if (isSuccess) {
				markedDataSource.add(db);
			} else {
				unMarkedDataSources.add(db);
			}
		}

		if (unMarkedDataSources.size() > 0) {
			result.onFail(dataSources, markedDataSource, String.format(
			      "fail to mark down %s on mysql-instance [%s:%s] because of lion problem.", unMarkedDataSources,
			      result.getIp(), result.getPort(), markedDataSource));
		} else {
			if(markedDataSource.size() > 0){
				result.onSuccess(0, dataSources);
			}else{
				result.onSuccess(2, dataSources);
			}
		}
	}

	@Override
	public DalResult markDown(String env, String ip, String port, String database) {
		Transaction transaction = Cat.newTransaction("MarkDown", database);
		Cat.logEvent("MarkDown-IP", ip);
		Cat.logEvent("MarkDown-Port", port);
		DalResult result = new DalResult(ip, port, database, env, "markdown");

		try {
			HashMap<String, String> keyValues = m_lionHttpService.getConfigByProject(env, PORJECT);

			// 找到所有符合ip:port的db
			Set<String> dataSources = findDataSources(ip, port, database, keyValues);

			if (dataSources.size() != 0) {
				markDBInternal(result, dataSources, false);
			} else {
				result.onSuccess(2, dataSources);
			}
		} catch (IOException e) {
			result.onFail(e.getMessage());
		} finally {
			transaction.complete();
		}

		return result;
	}

	@Override
	public DalResult markUp(String env, String ip, String port, String database) {
		Transaction transaction = Cat.newTransaction("MarkUp", database);
		Cat.logEvent("MarkUp-IP", ip);
		Cat.logEvent("MarkUp-Port", port);
		DalResult result = new DalResult(ip, port, database, env, "markup");

		try {
			HashMap<String, String> keyValues = m_lionHttpService.getConfigByProject(env, PORJECT);
			markDBInternal(result, findDataSources(ip, port, database, keyValues), true);
		} catch (Exception e) {
			result.onFail(e.getMessage());
		} finally {
			transaction.complete();
		}

		return result;
	}

	@SuppressWarnings("unused")
	private Set<String> validateDataSources(HashMap<String, String> keyValues, Set<String> dataSources) {
		// 找到使用这些db的读库组
		Set<String> dataBases = new HashSet<String>();
		for (String db : dataSources) {
			for (Entry<String, String> entry : keyValues.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();

				// 假设所有的读库组的key都是以zebra.v2.group.开头的
				if (value.contains(db) && key.contains("zebra.v2.group.") && value.contains(":r")) {
					dataBases.add(key);
				}
			}
		}

		// 过滤所有的读库组
		Set<String> dataBasesWithoutReadDB = new HashSet<String>();
		for (String database : dataBases) {
			String dbs = keyValues.get(database);
			Map<String, String> splits = Splitters.by(',', ':').trim().split(dbs);

			for (String db : dataSources) {
				splits.remove(db);
			}

			// 如果删除读库后，该读库组已经没有任何读库
			if (splits.size() == 0) {
				String databaseName = database.substring(database.lastIndexOf('.') + 1);
				for (Entry<String, String> entry : keyValues.entrySet()) {
					// 如果有业务用到这个读库组，则记录下来
					if (entry.getValue().contains(databaseName)) {
						dataBasesWithoutReadDB.add(databaseName);
					}
				}
			}
		}

		return dataBasesWithoutReadDB;
	}
}
