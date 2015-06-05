package com.dianping.zebra.admin.service.impl;

import com.dianping.zebra.admin.dao.ShardDumpDbMapper;
import com.dianping.zebra.admin.dao.ShardDumpTaskMapper;
import com.dianping.zebra.admin.entity.ShardDumpDbEntity;
import com.dianping.zebra.admin.entity.ShardDumpTaskEntity;
import com.dianping.zebra.admin.service.ShardDumpService;
import com.google.common.base.Preconditions;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public void createTask(ShardDumpTaskEntity entity) {
        shardDumpTaskMapper.create(entity);
    }

    @Override
    public String getPrimaryKey(String instance, String db, String table) {
        JdbcTemplate template = getJdbcTemplate(instance, db);
        return template.query("SELECT * FROM " + table + " limit 1", new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                return rs.getMetaData().getColumnName(1);
            }
        });
    }

    public long getMaxIndex(String instance, String db, String table, String key) {
        JdbcTemplate template = getJdbcTemplate(instance, db);
        return template
            .queryForObject(String.format("SELECT %s FROM %s ORDER BY %s DESC LIMIT 1", key, table, key), Long.class);
    }

    private JdbcTemplate getJdbcTemplate(String instance, String db) {
        ShardDumpDbEntity dbEntity = shardDumpDbMapper.getDbByName(instance).get(0);
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl(String.format("jdbc:mysql://%s:%d/%s", dbEntity.getHost(), dbEntity.getPort(), db));
        cpds.setUser(dbEntity.getUsername());
        cpds.setPassword(dbEntity.getPassword());
        return new JdbcTemplate(cpds);
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

    @Override
    public List<ShardDumpTaskEntity> getTaskByName(String name) {
        return shardDumpTaskMapper.getByName(name);
    }

    @Override
    public List<ShardDumpDbEntity> getAllDb() {
        return shardDumpDbMapper.getAll();
    }

    @Override
    public void createDb(ShardDumpDbEntity entity) {
        shardDumpDbMapper.create(entity);
    }

    @Override
    public void deleteDb(int id) {
        shardDumpDbMapper.delete(id);
    }

    protected ShardDumpDbEntity getDbByName(String name) {
        List<ShardDumpDbEntity> dbs = shardDumpDbMapper.getDbByName(name);
        Preconditions.checkArgument(dbs != null && dbs.size() == 1, "the db %s is not exist or more than one", name);
        return dbs.get(0);
    }
}