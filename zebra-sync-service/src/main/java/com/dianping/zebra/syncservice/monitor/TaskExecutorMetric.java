package com.dianping.zebra.syncservice.monitor;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.jboss.netty.util.internal.ConcurrentHashMap;

public class TaskExecutorMetric {

	private AtomicLong totalBinlogNumber = new AtomicLong(0);

	private AtomicLong totalSyncBinlogNumber = new AtomicLong(0);

	private Map<String, AtomicLong> everyTableSyncBinlogNumber = new ConcurrentHashMap<String, AtomicLong>();

	private AtomicLong totalSkipBinlogNumber = new AtomicLong(0);

	private AtomicLong totalNoRowsAffected = new AtomicLong(0);

	private AtomicLong totalDuplicateKey = new AtomicLong(0);

	private transient QpsCounter syncQpsCounter = new QpsCounter(15);

	private long syncQps = syncQpsCounter.get();

	public AtomicLong getTotalBinlogNumber() {
		return totalBinlogNumber;
	}

	public void addTotalBinlog(long number) {
		this.totalBinlogNumber.addAndGet(number);
		this.syncQpsCounter.add(number);
	}

	public AtomicLong getTotalSyncBinlogNumber() {
		return totalSyncBinlogNumber;
	}

	public void addTotalSyncBinlog(long number) {
		totalSyncBinlogNumber.addAndGet(number);
	}

	public Map<String, AtomicLong> getEveryTableSyncBinlogNumber() {
		return everyTableSyncBinlogNumber;
	}

	public void addSyncBinlogNumber(String table, long number) {
		AtomicLong atomicLong = everyTableSyncBinlogNumber.get(table);

		if (atomicLong == null) {
			atomicLong = new AtomicLong(0);

			everyTableSyncBinlogNumber.put(table, atomicLong);
		}

		atomicLong.addAndGet(number);
	}

	public AtomicLong getTotalSkipBinlogNumber() {
		return totalSkipBinlogNumber;
	}

	public void addTotalSkipBinlog(long number) {
		totalSkipBinlogNumber.addAndGet(number);
	}

	public AtomicLong getTotalNoRowsAffected() {
		return totalNoRowsAffected;
	}

	public void addTotalNoRowsAffected(long number) {
		totalNoRowsAffected.addAndGet(number);
	}

	public AtomicLong getTotalDuplicateKey() {
		return totalDuplicateKey;
	}

	public void addTotalDuplicatedKey(long number) {
		totalDuplicateKey.addAndGet(number);
	}

	public long getSyncQps() {
		return syncQps;
	}

	public void setSyncQps(long syncQps) {
		this.syncQps = syncQps;
	}
}
