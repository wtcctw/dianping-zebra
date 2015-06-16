package com.dianping.zebra.admin.service.impl;

import com.dianping.lion.client.ConfigCache;
import com.dianping.zebra.admin.dao.ShardMigrateProcessMapper;
import com.dianping.zebra.admin.entity.ShardMigrateProcessEntity;
import com.dianping.zebra.admin.service.ShardMigrateProcessService;
import com.dianping.zebra.config.LionKey;
import com.dianping.zebra.shard.config.RouterRuleConfig;
import com.dianping.zebra.shard.config.TableShardRuleConfig;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Dozer @ 6/3/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
@Service
public class ShardMigrateProcessServiceImpl implements ShardMigrateProcessService {
	@Autowired
	private ShardMigrateProcessMapper shardMigrateProcessMapper;

	private ConfigCache configCache;

	public ShardMigrateProcessServiceImpl() {
		this.configCache = ConfigCache.getInstance();
	}

	@Override
	public ShardMigrateProcessEntity getProcessByName(String name) {
		ShardMigrateProcessEntity entity;

		List<ShardMigrateProcessEntity> result = shardMigrateProcessMapper.getByName(name);
		Preconditions.checkArgument(result.size() <= 1, "the %s process more than one", name);
		if (result.size() == 0) {
			entity = new ShardMigrateProcessEntity();
			entity.setName(name);
			shardMigrateProcessMapper.create(entity);
		} else {
			entity = result.get(0);
		}

		int needSyncTaskCount = 0;
		RouterRuleConfig config = new Gson()
			.fromJson(configCache.getProperty(LionKey.getShardConfigKey(name)), RouterRuleConfig.class);
		if (config != null) {
			for (TableShardRuleConfig tableConfig : config.getTableShardConfigs()) {
				if (tableConfig.getDimensionConfigs().size() >= 2) {
					entity.setNeedSync(true);
				}
				needSyncTaskCount += tableConfig.getDimensionConfigs().size();
			}
		}
		//todo:
		//        int syncTaskCount = shardSyncTaskMapper.getCountByName(name);
		//entity.setSyncCreateFinish(needSyncTaskCount == syncTaskCount);

		shardMigrateProcessMapper.update(entity);
		return entity;
	}

	@Override
	public void updateProcess(ShardMigrateProcessEntity entity) {
		shardMigrateProcessMapper.update(entity);
	}
}
