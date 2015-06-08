package com.dianping.zebra.admin.entity;

/**
 * Dozer @ 6/8/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class ShardSyncTaskEntity {
    private String pumaServerHost;

    private int pumaServerPort;

    private String pumaTaskName;

    //分库分表规则名
    private String ruleName;

    //每个规则下可能有多个逻辑表，每个任务只能选择一个逻辑表
    private String tableName;

    //是否为迁移任务
    private boolean isMigrate;

    //迁移任务需要制定 binlogName
    private String binlogName;

    //迁移任务需要制定 binlogPos
    private long binlogPos;

    //可以制定 sequence
    private long seqTimestamp;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean isMigrate() {
        return isMigrate;
    }

    public void setIsMigrate(boolean isMigrate) {
        this.isMigrate = isMigrate;
    }

    public String getBinlogName() {
        return binlogName;
    }

    public void setBinlogName(String binlogName) {
        this.binlogName = binlogName;
    }

    public long getBinlogPos() {
        return binlogPos;
    }

    public void setBinlogPos(long binlogPos) {
        this.binlogPos = binlogPos;
    }

    public long getSeqTimestamp() {
        return seqTimestamp;
    }

    public void setSeqTimestamp(long seqTimestamp) {
        this.seqTimestamp = seqTimestamp;
    }

    public String getPumaServerHost() {
        return pumaServerHost;
    }

    public void setPumaServerHost(String pumaServerHost) {
        this.pumaServerHost = pumaServerHost;
    }

    public int getPumaServerPort() {
        return pumaServerPort;
    }

    public void setPumaServerPort(int pumaServerPort) {
        this.pumaServerPort = pumaServerPort;
    }

    public String getPumaTaskName() {
        return pumaTaskName;
    }

    public void setPumaTaskName(String pumaTaskName) {
        this.pumaTaskName = pumaTaskName;
    }
}
