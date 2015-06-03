package com.dianping.zebra.admin.service;

import com.dianping.zebra.admin.dao.ShardMigrateProcessMapper;
import com.dianping.zebra.admin.entity.ShardMigrateProcessEntity;
import com.google.common.base.Preconditions;
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

    @Override
    public ShardMigrateProcessEntity getProcessByName(String name) {
        ShardMigrateProcessEntity entity;

        List<ShardMigrateProcessEntity> result = shardMigrateProcessMapper.getByName(name);
        Preconditions.checkArgument(result.size() <= 1, "the %s process more than one", name);
        if (result.size() == 0) {
            entity = new ShardMigrateProcessEntity();
            entity.setName(name);
            entity.setId(shardMigrateProcessMapper.create(entity));
        } else {
            entity = result.get(0);
        }

        //todo: check other properties
        return entity;
    }

    @Override
    public void updateProcess(ShardMigrateProcessEntity entity) {
        shardMigrateProcessMapper.update(entity);
    }
}
