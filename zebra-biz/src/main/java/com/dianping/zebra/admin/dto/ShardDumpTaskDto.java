package com.dianping.zebra.admin.dto;

import com.dianping.zebra.admin.entity.ShardDumpTaskEntity;

import java.util.List;

/**
 * Dozer @ 6/4/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class ShardDumpTaskDto extends ShardDumpTaskEntity {
    private List<ShardDumpTaskEntity> targets;

    public List<ShardDumpTaskEntity> getTargets() {
        return targets;
    }

    public void setTargets(List<ShardDumpTaskEntity> targets) {
        this.targets = targets;
    }
}
