package com.dianping.zebra.syncservice.metrics;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.jboss.netty.util.internal.ConcurrentHashMap;

public class TaskExecutorMetric {
	
	private AtomicLong totalBinlogNumber = new AtomicLong(0);
	
	private AtomicLong totalSyncBinlogNumber = new AtomicLong(0);
	
	private Map<String,AtomicLong> everyTableSyncBinlogNumber = new ConcurrentHashMap<String, AtomicLong>();

	private AtomicLong totalSkipBinlogNumber = new AtomicLong(0);
	
	public AtomicLong getTotalBinlogNumber() {
		return totalBinlogNumber;
	}

	public void setTotalBinlogNumber(AtomicLong totalBinlogNumber) {
		this.totalBinlogNumber = totalBinlogNumber;
	}

	public AtomicLong getTotalSyncBinlogNumber() {
		return totalSyncBinlogNumber;
	}

	public void setTotalSyncBinlogNumber(AtomicLong totalSyncBinlogNumber) {
		this.totalSyncBinlogNumber = totalSyncBinlogNumber;
	}

	public Map<String, AtomicLong> getEveryTableSyncBinlogNumber() {
		return everyTableSyncBinlogNumber;
	}

	public void setEveryTableSyncBinlogNumber(Map<String, AtomicLong> everyTableSyncBinlogNumber) {
		this.everyTableSyncBinlogNumber = everyTableSyncBinlogNumber;
	}

	public AtomicLong getTotalSkipBinlogNumber() {
		return totalSkipBinlogNumber;
	}

	public void setTotalSkipBinlogNumber(AtomicLong totalSkipBinlogNumber) {
		this.totalSkipBinlogNumber = totalSkipBinlogNumber;
	}
}
