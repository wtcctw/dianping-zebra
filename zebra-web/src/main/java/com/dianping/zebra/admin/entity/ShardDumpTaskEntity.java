package com.dianping.zebra.admin.entity;

import java.util.Arrays;
import java.util.List;

public class ShardDumpTaskEntity {

    private String binlogFile;

    private String binlogPos;

    private String status;

    private String executor;

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

    private long indexIncrease = 1000000;

    private long maxKey;

    private ShardDumpDbEntity srcDbEntity;

    private ShardDumpDbEntity dstDbEntity;

    private List<String> options = Arrays
            .asList("--master-data=2", "--disable-keys", "--skip-comments", "--quick", "--add-drop-database=false",
                    "--no-create-info", "--add-drop-table=false", "--skip-add-locks", "--default-character-set=utf8",
                    "--max_allowed_packet=1073741824", "-i", "--single-transaction", "--hex-blob", "--compact");

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

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

    public String getBinlogPos() {
        return binlogPos;
    }

    public void setBinlogPos(String binlogPos) {
        this.binlogPos = binlogPos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
