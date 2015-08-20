package com.dianping.zebra.admin.controller;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.zebra.biz.dao.PumaClientSyncTaskMapper;
import com.dianping.zebra.biz.dto.PumaClientSyncTaskDto;
import com.dianping.zebra.biz.entity.PumaClientSyncTaskBaseEntity;
import com.dianping.zebra.biz.entity.PumaClientSyncTaskEntity;
import com.dianping.zebra.biz.entity.SyncServerMonitorEntity;
import com.dianping.zebra.biz.service.SyncServerMonitorService;
import com.dianping.zebra.config.LionConfigService;
import com.dianping.zebra.config.LionKey;
import com.dianping.zebra.group.config.DefaultDataSourceConfigManager;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import com.dianping.zebra.shard.config.RouterRuleConfig;
import com.dianping.zebra.shard.config.TableShardDimensionConfig;
import com.dianping.zebra.shard.config.TableShardRuleConfig;
import com.dianping.zebra.shard.router.rule.SimpleDataSourceProvider;
import com.dianping.zebra.util.StringUtils;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.gson.Gson;

@Controller
@RequestMapping(value = "/syncTask")
public class PumaClientSyncTaskController extends BasicController {

	@Autowired
	private PumaClientSyncTaskMapper dao;

	@Autowired
	private SyncServerMonitorService syncServers;

	private static final Pattern JDBC_URL_PATTERN = Pattern.compile("jdbc:mysql://([^:]+):\\d+/([^\\?]+).*");

	private static final Pattern SHARD_PATTERN = Pattern.compile("#([^#]+)#");

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Object getExecutePlan(String ruleName) {
		if (StringUtils.isNotBlank(ruleName)) {
			List<PumaClientSyncTaskEntity> allEntities = dao.findAllByRuleName(ruleName);

			List<PumaClientSyncTaskDto> result = generatePlan(ruleName);

			for (PumaClientSyncTaskDto dto : result) {
				insertOrUpdate(allEntities, dto);
			}
			return result;
		}

		return null;
	}

	@RequestMapping(value = "/updateSyncServer", method = RequestMethod.GET)
	@ResponseBody
	public void updateSyncServer(String pumaClientName, String executor, String executor1, String executor2) {
		if (pumaClientName != null && executor != null && executor1 != null && executor2 != null) {
			dao.updateSyncTaskSyncServers(pumaClientName, executor, executor1, executor2);
		}
	}

	@RequestMapping(value = "/schedule", method = RequestMethod.GET)
	@ResponseBody
	public void updateExecutePlan(String pumaClientName) {
		if (StringUtils.isNotBlank(pumaClientName)) {
			dao.updateSyncTaskStatus(pumaClientName, 2);
		}
	}

	private void insertOrUpdate(List<PumaClientSyncTaskEntity> allEntities, PumaClientSyncTaskDto dto) {
		PumaClientSyncTaskEntity tmp = null;
		for (PumaClientSyncTaskEntity entity : allEntities) {
			if (entity.getPumaClientName().equals(dto.getPumaClientName())) {
				tmp = entity;
				break;
			}
		}

		if (tmp == null) {
			SyncServerMonitorEntity chooseOne = syncServers.chooseOne();
			if (chooseOne != null) {
				String executor = chooseOne.getName();
				dto.setExecutor(executor);
				dto.setExecutor1(executor);
				dto.setExecutor2("");
			}
			dto.setStatus(1);

			dao.insertSyncTask(dto);
		} else {
			dto.setExecutor(tmp.getExecutor());
			dto.setExecutor1(tmp.getExecutor1());
			dto.setExecutor2(tmp.getExecutor2());
			dto.setStatus(tmp.getStatus());
			dao.updateSyncTask(dto);
		}
	}

	protected List<PumaClientSyncTaskDto> generatePlan(String ruleName) {
		Map<String, PumaClientSyncTaskDto> tasks = new HashMap<String, PumaClientSyncTaskDto>();

		LionConfigService configService = LionConfigService.getInstance();
		RouterRuleConfig routerConfig = new Gson().fromJson(
		      configService.getProperty(LionKey.getShardConfigKey(ruleName)), RouterRuleConfig.class);

		for (TableShardRuleConfig tableShardRule : routerConfig.getTableShardConfigs()) {
			String table = tableShardRule.getTableName();
			Map<String, Set<String>> allDBAndTables = null;

			for (TableShardDimensionConfig dimensionConfig : tableShardRule.getDimensionConfigs()) {
				if (dimensionConfig.isMaster()) {
					SimpleDataSourceProvider provider = new SimpleDataSourceProvider(table, dimensionConfig.getDbIndexes(),
					      dimensionConfig.getTbSuffix(), dimensionConfig.getTbRule());
					allDBAndTables = provider.getAllDBAndTables();
					break;
				}
			}

			for (TableShardDimensionConfig dimensionConfig : tableShardRule.getDimensionConfigs()) {
				if (dimensionConfig.isMaster() == false && dimensionConfig.isNeedSync()) {
					for (Entry<String, Set<String>> dbAndTables : allDBAndTables.entrySet()) {
						String jdbcRef = dbAndTables.getKey();
						String shardColumn = getShardColumn(dimensionConfig.getTbRule());

						DefaultDataSourceConfigManager configManager = new DefaultDataSourceConfigManager(jdbcRef,
						      LionConfigService.getInstance());
						configManager.init();
						configManager.close();

						DataSourceConfig dsConfig = findTheOnlyWriteDataSourceConfig(configManager.getGroupDataSourceConfig());
						Matcher matcher = JDBC_URL_PATTERN.matcher(dsConfig.getJdbcUrl());
						checkArgument(matcher.matches(), dsConfig.getJdbcUrl());
						String database = matcher.group(2);

						String pumaClientName = database + "-" + shardColumn;

						PumaClientSyncTaskDto syncTask = tasks.get(pumaClientName);

						if (syncTask == null) {
							syncTask = new PumaClientSyncTaskDto();
							syncTask.setRuleName(ruleName);
							syncTask.setPumaClientName(pumaClientName);
							syncTask.setShardColumn(shardColumn);
							syncTask.setPumaDatabase(database);

							tasks.put(pumaClientName, syncTask);
						}

						for (String tb : dbAndTables.getValue()) {
							syncTask.addPumaTable(tb);
						}

						PumaClientSyncTaskBaseEntity baseEntity = new PumaClientSyncTaskBaseEntity();

						baseEntity.setTableName(table);
						baseEntity.setPk(tableShardRule.getGeneratedPK());
						baseEntity.setDbRule(dimensionConfig.getDbRule());
						baseEntity.setDbIndex(dimensionConfig.getDbIndexes());
						baseEntity.setTbRule(dimensionConfig.getTbRule());
						baseEntity.setTbSuffix(dimensionConfig.getTbSuffix());

						syncTask.getPumaBaseEntities().put(table, baseEntity);
					}
				}
			}
		}

		return FluentIterable.from(tasks.values()).toList();
	}

	private DataSourceConfig findTheOnlyWriteDataSourceConfig(GroupDataSourceConfig config) {
		checkNotNull(config, "ds");
		List<DataSourceConfig> dataSourceConfigs = FluentIterable.from(config.getDataSourceConfigs().values())
		      .filter(new Predicate<DataSourceConfig>() {
			      @Override
			      public boolean apply(DataSourceConfig dataSourceConfig) {
				      return dataSourceConfig.isCanWrite();
			      }
		      }).toList();

		checkArgument(dataSourceConfigs.size() == 1, config.toString());
		return dataSourceConfigs.get(0);
	}

	private String getShardColumn(String tableRule) {
		StringBuilder sb = new StringBuilder();
		Matcher matcher = SHARD_PATTERN.matcher(tableRule);

		boolean isFirst = true;
		while (matcher.find()) {
			String column = matcher.group(1);
			if (isFirst) {
				sb.append(column);
				isFirst = false;
			} else {
				sb.append("-");
				sb.append(column);
			}
		}

		return sb.toString();
	}
}
