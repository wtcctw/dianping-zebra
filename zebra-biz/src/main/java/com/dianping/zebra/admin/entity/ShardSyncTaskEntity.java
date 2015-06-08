package com.dianping.zebra.admin.entity;

/**
 * Dozer @ 6/8/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class ShardSyncTaskEntity {
    public final static int SYNC_TASK = 1;

    public final static int MIGRATE_TASK = 2;

    private int id;

    private String pumaServerHost;

    private int pumaServerPort;

    private String pumaTaskName;

    //执行任务的机器
    private String executor;

    //分库分表规则名
    private String ruleName;

    //每个规则下可能有多个逻辑表，每个任务只能选择一个逻辑表
    private String tableName;

    //是否为迁移任务
    private int type;

    //迁移任务需要制定 binlogName
    private String binlogName;

    //迁移任务需要制定 binlogPos
    private Long binlogPos;

    //可以制定 sequence
    private Long seqTimestamp;

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

    public String getBinlogName() {
        return binlogName;
    }

    public void setBinlogName(String binlogName) {
        this.binlogName = binlogName;
    }

    public Long getBinlogPos() {
        return binlogPos;
    }

    public void setBinlogPos(Long binlogPos) {
        this.binlogPos = binlogPos;
    }

    public Long getSeqTimestamp() {
        return seqTimestamp;
    }

    public void setSeqTimestamp(Long seqTimestamp) {
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

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
