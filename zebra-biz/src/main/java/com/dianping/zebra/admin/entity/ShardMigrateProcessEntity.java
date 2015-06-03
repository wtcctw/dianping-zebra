package com.dianping.zebra.admin.entity;

/**
 * Dozer @ 6/3/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class ShardMigrateProcessEntity {
    private int id;

    private String name;

    private boolean initFinish;

    private boolean dumpFinish;

    private boolean needSync;

    private boolean syncCreateFinish;

    private boolean catchUpFinish;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInitFinish() {
        return initFinish;
    }

    public void setInitFinish(boolean initFinish) {
        this.initFinish = initFinish;
    }

    public boolean isDumpFinish() {
        return dumpFinish;
    }

    public void setDumpFinish(boolean dumpFinish) {
        this.dumpFinish = dumpFinish;
    }

    public boolean isNeedSync() {
        return needSync;
    }

    public void setNeedSync(boolean needSync) {
        this.needSync = needSync;
    }

    public boolean isSyncCreateFinish() {
        return syncCreateFinish;
    }

    public void setSyncCreateFinish(boolean syncCreateFinish) {
        this.syncCreateFinish = syncCreateFinish;
    }

    public boolean isCatchUpFinish() {
        return catchUpFinish;
    }

    public void setCatchUpFinish(boolean catchUpFinish) {
        this.catchUpFinish = catchUpFinish;
    }
}
