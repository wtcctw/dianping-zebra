package com.dianping.zebra.group.config.system.transform;

import com.dianping.zebra.group.config.system.entity.SystemConfig;

public interface IMaker<T> {

   public SystemConfig buildSystemConfig(T node);
}
