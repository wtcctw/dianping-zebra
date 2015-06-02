package com.dianping.zebra.admin.service;

import com.dianping.zebra.admin.dao.ShardDumpDbMapper;
import com.dianping.zebra.admin.dao.ShardDumpTaskMapper;
import com.dianping.zebra.admin.entity.ShardDumpDbEntity;
import com.dianping.zebra.admin.entity.ShardDumpTaskEntity;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Dozer @ 6/1/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
@Service
public class ShardDumpServiceImpl implements ShardDumpService {
    @Autowired
    private ShardDumpTaskMapper shardDumpTaskMapper;

    @Autowired
    private ShardDumpDbMapper shardDumpDbMapper;

    @Override
    public void updateTaskStatus(ShardDumpTaskEntity entity) {
        shardDumpTaskMapper.updateStatus(entity);
    }

    @Override
    public List<ShardDumpTaskEntity> getTaskByIp(String ip) {
        List<ShardDumpTaskEntity> result = shardDumpTaskMapper.getByExecutor(ip);
        for (ShardDumpTaskEntity entity : result) {
            entity.setSrcDbEntity(getDbByName(entity.getSrcDbName()));
            entity.setDstDbEntity(getDbByName(entity.getDstDbName()));
        }
        return result;
    }

    protected ShardDumpDbEntity getDbByName(String name) {
        List<ShardDumpDbEntity> dbs = shardDumpDbMapper.getDbByName(name);
        Preconditions.checkArgument(dbs != null && dbs.size() == 1, "the db %s is not exist or more than one", name);
        return dbs.get(0);
    }
}