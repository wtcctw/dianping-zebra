package com.dianping.zebra.admin.dao;

import com.dianping.zebra.admin.entity.PumaClientSyncTaskEntity;
import com.dianping.zebra.admin.entity.ShardDumpTaskEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PumaClientSyncTaskMapper {

	List<PumaClientSyncTaskEntity> findAllByRuleName(@Param("ruleName") String ruleName);

	List<PumaClientSyncTaskEntity> findEffectiveTaskByRuleName(@Param("ruleName") String ruleName);

	List<PumaClientSyncTaskEntity> findEffectiveTaskByExecutor(@Param("executor") String executor);

	void insertSyncTask(PumaClientSyncTaskEntity entity);

	void updateSyncTask(PumaClientSyncTaskEntity entity);

	void updateSequence(PumaClientSyncTaskEntity entity);

	void updateSyncTaskStatus(@Param("pumaTaskName") String pumaTaskName, @Param("status")  int status);
}
