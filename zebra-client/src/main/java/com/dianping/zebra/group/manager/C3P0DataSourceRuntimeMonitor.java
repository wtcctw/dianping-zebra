package com.dianping.zebra.group.manager;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public enum C3P0DataSourceRuntimeMonitor {
	INSTANCE;

	private ConcurrentMap<String, AtomicInteger> checkedOutCounter = new ConcurrentHashMap<String, AtomicInteger>();

	public void incCheckedOutCount(String dsId) {
		if (!checkedOutCounter.containsKey(dsId)) {
			checkedOutCounter.putIfAbsent(dsId, new AtomicInteger(1));
		} else {
			checkedOutCounter.get(dsId).incrementAndGet();
		}
	}

	public void descCheckedOutCount(String dsId) {
		checkedOutCounter.putIfAbsent(dsId, new AtomicInteger(0)).decrementAndGet();
	}

	public int getCheckedOutCount(String dsId) {
		return checkedOutCounter.putIfAbsent(dsId, new AtomicInteger(0)).get();
	}

	public void removeCounter(String dsId) {
		checkedOutCounter.remove(dsId);
	}
}
