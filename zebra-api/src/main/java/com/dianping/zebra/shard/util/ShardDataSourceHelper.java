package com.dianping.zebra.shard.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dianping.avatar.tracker.InitialInstanceThreadLocal;

public class ShardDataSourceHelper {

	private static final ThreadLocal<ShardParamsHolder> SHARD_PARAMS = new InitialInstanceThreadLocal<ShardParamsHolder>(
			ShardParamsHolder.class);

	public static List<Object> getShardParams(String shardColumn) {
		ShardParamsHolder holder = SHARD_PARAMS.get();

		if (holder != null) {
			return holder.getShardParams(shardColumn);
		}

		return null;
	}

	public static void setShardParams(String shardColumn, List<Object> params) {
		ShardParamsHolder holder = SHARD_PARAMS.get();

		if (holder == null) {
			holder = new ShardParamsHolder();
		}

		holder.setShardParams(shardColumn, params);
	}

	public static boolean hasAnyShardParams() {
		ShardParamsHolder holder = SHARD_PARAMS.get();

		return holder == null ? false : true;
	}

	public static void removeAllShardParams() {
		SHARD_PARAMS.remove();
	}

	public static class ShardParamsHolder {
		private Map<String, List<Object>> shardParams = new HashMap<String, List<Object>>();

		public void setShardParams(String shardColumn, List<Object> params) {
			this.shardParams.put(shardColumn, params);
		}

		public List<Object> getShardParams(String shardColumn) {
			return this.shardParams.get(shardColumn);
		}
	}
}
