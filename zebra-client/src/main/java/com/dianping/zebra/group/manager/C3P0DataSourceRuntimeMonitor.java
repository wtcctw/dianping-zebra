package com.dianping.zebra.group.manager;

import java.util.HashMap;
import java.util.Map;

public enum C3P0DataSourceRuntimeMonitor {
	INSTANCE;

	private Map<String, Integer> checkedOutCounter = new HashMap<String, Integer>();

	public synchronized void incCheckedOutCount(String dsId) {
		Integer counter = checkedOutCounter.get(dsId);

		if (counter == null) {
			checkedOutCounter.put(dsId, new Integer(1));
		} else {
			checkedOutCounter.put(dsId, counter++);
		}
	}

	public synchronized void descCheckedOutCount(String dsId) {
		Integer counter = checkedOutCounter.get(dsId);

		if (counter == null) {
			checkedOutCounter.put(dsId, new Integer(0));
		} else {
			checkedOutCounter.put(dsId, counter--);
		}
	}

	public synchronized int getCheckedOutCount(String dsId) {
		Integer counter = checkedOutCounter.get(dsId);

		if (counter == null) {
			return 0;
		} else {
			return counter;
		}
	}

	public synchronized void removeCounter(String dsId) {
		checkedOutCounter.remove(dsId);
	}
	
	//TODO
}
