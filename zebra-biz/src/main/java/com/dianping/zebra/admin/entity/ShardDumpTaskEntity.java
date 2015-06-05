package com.dianping.zebra.admin.entity;

public class ShardDumpTaskEntity {
    private volatile int id;

    //binlog文件
    private volatile String binlogFile;

    //binlog位置
    private volatile long binlogPos;

    //状态
    private volatile String status;

    //执行的机器
    private volatile String executor;

    //rule name
    private volatile String name;

    private volatile String srcDbName;

    private volatile String dstDbName;

    private volatile String dataBase;

    private volatile String targetDataBase;

    private volatile String tableName;

    private volatile String targetTableName;

    private volatile String shardRule;

    private volatile String indexColumnName;

    private volatile long indexKey;

    private volatile long maxKey;

    private volatile ShardDumpDbEntity srcDbEntity;

    private volatile ShardDumpDbEntity dstDbEntity;

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
