package com.dianping.zebra.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.zebra.admin.dao.PumaClientSyncTaskMapper;
import com.dianping.zebra.admin.entity.PumaClientSyncTaskEntity;
import com.dianping.zebra.config.LionConfigService;
import com.dianping.zebra.config.LionKey;
import com.dianping.zebra.shard.config.RouterRuleConfig;
import com.dianping.zebra.shard.config.TableShardDimensionConfig;
import com.dianping.zebra.shard.config.TableShardRuleConfig;
import com.dianping.zebra.shard.router.rule.SimpleDataSourceProvider;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/syncTask")
public class PumaClientSyncTaskController extends BasicController {

	@Autowired
	private PumaClientSyncTaskMapper dao;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Object getExecutePlan(String ruleName) {
		List<PumaClientSyncTaskEntity> result = new ArrayList<PumaClientSyncTaskEntity>();

		LionConfigService configService = LionConfigService.getInstance();
		RouterRuleConfig routerConfig = new Gson().fromJson(
		      configService.getProperty(LionKey.getShardConfigKey(ruleName)), RouterRuleConfig.class);

		for (TableShardRuleConfig tableShardRule : routerConfig.getTableShardConfigs()) {
			String table = tableShardRule.getTableName();

			for (TableShardDimensionConfig dimensionConfig : tableShardRule.getDimensionConfigs()) {
				SimpleDataSourceProvider provider = new SimpleDataSourceProvider(table, dimensionConfig.getDbIndexes(),
				      dimensionConfig.getTbSuffix(), dimensionConfig.getTbRule());

				Map<String, Set<String>> allDBAndTables = provider.getAllDBAndTables();

				for (Entry<String, Set<String>> dbAndTables : allDBAndTables.entrySet()) {
					PumaClientSyncTaskEntity syncTask = new PumaClientSyncTaskEntity();

					syncTask.setRuleName(ruleName);
					syncTask.setTable(table);
					syncTask.setDbRule(dimensionConfig.getDbRule());
					syncTask.setDbIndexes(dimensionConfig.getDbIndexes());
					syncTask.setTbRule(dimensionConfig.getTbRule());
					syncTask.setTbSuffix(dimensionConfig.getTbSuffix());

					String syncServer = pickUpSyncServer();

					syncTask.setPumaTaskName(String.format("%s@%s", dbAndTables.getKey(), syncServer));
					syncTask.setPumaDatabase(dbAndTables.getKey());

					StringBuilder sb = new StringBuilder(128);
					boolean isFirst = true;

					for (String tb : dbAndTables.getValue()) {
						if (isFirst) {
							sb.append(tb);
							isFirst = false;
						} else {
							sb.append(",");
							sb.append(tb);
						}
					}

					syncTask.setPumaTables(sb.toString());
					syncTask.setStatus(1);
					syncTask.setExecutor(syncServer);

					result.add(syncTask);
				}
			}
		}

		return result;
	}

	private String pickUpSyncServer() {
		return "syncServer01";
	}
}
