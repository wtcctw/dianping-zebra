package com.dianping.zebra.admin.service.impl;

import com.dianping.zebra.admin.dao.ShardSyncTaskMapper;
import com.dianping.zebra.admin.entity.ShardSyncTaskEntity;
import com.dianping.zebra.admin.service.ShardSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Dozer @ 6/1/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
@Service
public class ShardSyncServiceImpl implements ShardSyncService {
    @Autowired
    private ShardSyncTaskMapper ShardSyncTaskMapper;

    @Override
    public void createTask(ShardSyncTaskEntity entity) {
        ShardSyncTaskMapper.create(entity);
    }

    @Override
    public void removeTask(int id) {
        ShardSyncTaskMapper.remove(id);
    }

    @Override
    public List<ShardSyncTaskEntity> getTaskByIp(String ip) {
        return ShardSyncTaskMapper.getByExecutor(ip);
    }

    @Override
    public List<ShardSyncTaskEntity> getTaskByName(String name) {
        return ShardSyncTaskMapper.getByName(name);
    }
}