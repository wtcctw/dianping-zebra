package com.dianping.zebra.group.manager;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public enum C3P0DataSourceRuntimeMonitor {
	INSTANCE;

	private ConcurrentMap<String, AtomicInteger> checkedOutCounter = new ConcurrentHashMap<String, AtomicInteger>();

	public void incCheckedOutCount(String dsId) {
		checkedOutCounter.get(dsId).incrementAndGet();
	}

	public void descCheckedOutCount(String dsId) {
		checkedOutCounter.get(dsId).decrementAndGet();
	}

	public int getCheckedOutCount(String dsId) {
		return checkedOutCounter.get(dsId).intValue();
	}

	public void initCounter(String dsId) {
		checkedOutCounter.put(dsId, new AtomicInteger(0));
	}

	public void removeCounter(String dsId) {
		checkedOutCounter.remove(dsId);
	}
}
