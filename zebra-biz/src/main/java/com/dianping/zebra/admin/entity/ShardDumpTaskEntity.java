package com.dianping.zebra.admin.entity;

import java.util.Arrays;
import java.util.List;

public class ShardDumpTaskEntity {
    private int id;

    //binlog文件
    private String binlogFile;

    //binlog位置
    private long binlogPos;

    //状态
    private String status;

    //执行的机器
    private String executor;

    //rule name
    private String name;

    private String srcDbName;

    private String dstDbName;

    private String dataBase;

    private String targetDataBase;

    private String tableName;

    private String targetTableName;

    private String shardRule;



    private String indexColumnName;

    private long indexKey;

    //需要自动调整
    private long indexIncrease = 1000000;

    private long maxKey;



    private ShardDumpDbEntity srcDbEntity;

    private ShardDumpDbEntity dstDbEntity;

    public String getShardRule() {
        return shardRule;
    }

    public void setShardRule(String shardRule) {
        this.shardRule = shardRule;
    }

    public String getSrcDbName() {
        return srcDbName;
    }

    public void setSrcDbName(String srcDbName) {
        this.srcDbName = srcDbName;
    }

    public long getIndexKey() {
        return indexKey;
    }

    public void setIndexKey(long indexKey) {
        this.indexKey = indexKey;
    }

    public long getMaxKey() {
        return maxKey;
    }

    public void setMaxKey(long maxKey) {
        this.maxKey = maxKey;
    }

    public String getDstDbName() {
        return dstDbName;
    }

    public void setDstDbName(String dstDbName) {
        this.dstDbName = dstDbName;
    }

    public String getDataBase() {
        return dataBase;
    }

    public void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getIndexColumnName() {
        return indexColumnName;
    }

    public void setIndexColumnName(String indexColumnName) {
        this.indexColumnName = indexColumnName;
    }

    public String getTargetTableName() {
        return targetTableName;
    }

    public void setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
    }

    public String getTargetDataBase() {
        return targetDataBase;
    }

    public void setTargetDataBase(String targetDataBase) {
        this.targetDataBase = targetDataBase;
    }

    public long getIndexIncrease() {
        return indexIncrease;
    }

    public void setIndexIncrease(long indexIncrease) {
        this.indexIncrease = indexIncrease;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public ShardDumpDbEntity getSrcDbEntity() {
        return srcDbEntity;
    }

    public void setSrcDbEntity(ShardDumpDbEntity srcDbEntity) {
        this.srcDbEntity = srcDbEntity;
    }

    public ShardDumpDbEntity getDstDbEntity() {
        return dstDbEntity;
    }

    public void setDstDbEntity(ShardDumpDbEntity dstDbEntity) {
        this.dstDbEntity = dstDbEntity;
    }

    public String getBinlogFile() {
        return binlogFile;
    }

    public void setBinlogFile(String binlogFile) {
        this.binlogFile = binlogFile;
    }

    public long getBinlogPos() {
        return binlogPos;
    }

    public void setBinlogPos(long binlogPos) {
        this.binlogPos = binlogPos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
