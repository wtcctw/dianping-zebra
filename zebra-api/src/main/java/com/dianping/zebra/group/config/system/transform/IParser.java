package com.dianping.zebra.group.config.system.transform;

import com.dianping.zebra.group.config.system.entity.SystemConfig;

public interface IParser<T> {
   public SystemConfig parse(IMaker<T> maker, ILinker linker, T node);
}
