package com.dianping.zebra.admin.controller;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
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

import com.dianping.zebra.admin.dao.PumaClientSyncTaskMapper;
import com.dianping.zebra.admin.dto.PumaClientSyncTaskDto;
import com.dianping.zebra.admin.entity.PumaClientSyncTaskEntity;
import com.dianping.zebra.admin.entity.SyncServerMonitorEntity;
import com.dianping.zebra.admin.service.SyncServerMonitorService;
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
	public void updateSyncServer(String pumaTaskName,String executor, String executor1,String executor2){
		if(pumaTaskName != null && executor != null && executor1 != null && executor2 != null){
			dao.updateSyncTaskSyncServers(pumaTaskName, executor, executor1, executor2);
		}
	}

	@RequestMapping(value = "/schedule", method = RequestMethod.GET)
	@ResponseBody
	public void updateExecutePlan(String pumaTaskName) {
		if (StringUtils.isNotBlank(pumaTaskName)) {
			dao.updateSyncTaskStatus(pumaTaskName, 2);
		}
	}

	private void insertOrUpdate(List<PumaClientSyncTaskEntity> allEntities, PumaClientSyncTaskDto dto) {
		PumaClientSyncTaskEntity tmp = null;
		for (PumaClientSyncTaskEntity entity : allEntities) {
			if (entity.getPumaTaskName().equals(dto.getPumaTaskName())) {
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

	private List<PumaClientSyncTaskDto> generatePlan(String ruleName) {
		List<PumaClientSyncTaskDto> result = new ArrayList<PumaClientSyncTaskDto>();

		LionConfigService configService = LionConfigService.getInstance();
		RouterRuleConfig routerConfig = new Gson().fromJson(
		      configService.getProperty(LionKey.getShardConfigKey(ruleName)), RouterRuleConfig.class);

		for (TableShardRuleConfig tableShardRule : routerConfig.getTableShardConfigs()) {
			String table = tableShardRule.getTableName();
			Map<String, Set<String>> allDBAndTables = null;

			for (TableShardDimensionConfig dimensionConfig : tableShardRule.getDimensionConfigs()) {
				SimpleDataSourceProvider provider = new SimpleDataSourceProvider(table, dimensionConfig.getDbIndexes(),
				      dimensionConfig.getTbSuffix(), dimensionConfig.getTbRule());

				if (dimensionConfig.isMaster()) {
					allDBAndTables = provider.getAllDBAndTables();
					break;
				}
			}

			for (TableShardDimensionConfig dimensionConfig : tableShardRule.getDimensionConfigs()) {
				SimpleDataSourceProvider provider = new SimpleDataSourceProvider(table, dimensionConfig.getDbIndexes(),
				      dimensionConfig.getTbSuffix(), dimensionConfig.getTbRule());

				if (dimensionConfig.isMaster()) {
					allDBAndTables = provider.getAllDBAndTables();
					continue;
				}

				if (dimensionConfig.isMaster() == false) {
					for (Entry<String, Set<String>> dbAndTables : allDBAndTables.entrySet()) {
						PumaClientSyncTaskDto syncTask = new PumaClientSyncTaskDto();

						syncTask.setRuleName(ruleName);
						syncTask.setTableName(table);
						syncTask.setPk(tableShardRule.getGeneratedPK());
						syncTask.setDbRule(dimensionConfig.getDbRule());
						syncTask.setDbIndexes(dimensionConfig.getDbIndexes());
						syncTask.setTbRule(dimensionConfig.getTbRule());
						String shardColumn = getShardColumn(dimensionConfig.getTbRule());
						syncTask.setShardColumn(shardColumn);
						syncTask.setTbSuffix(dimensionConfig.getTbSuffix());

						String jdbcRef = dbAndTables.getKey();
						DefaultDataSourceConfigManager configManager = new DefaultDataSourceConfigManager(jdbcRef,
						      LionConfigService.getInstance());
						configManager.init();

						DataSourceConfig dsConfig = findTheOnlyWriteDataSourceConfig(configManager.getGroupDataSourceConfig());
						Matcher matcher = JDBC_URL_PATTERN.matcher(dsConfig.getJdbcUrl());
						checkArgument(matcher.matches(), dsConfig.getJdbcUrl());
						String dbName = matcher.group(2);

						syncTask.setPumaDatabase(dbName);

						syncTask.setPumaTaskName(String.format("%s@%s@%s", dbName, table, shardColumn));
						configManager.close();

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

						result.add(syncTask);
					}
				}
			}
		}

		return result;
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
