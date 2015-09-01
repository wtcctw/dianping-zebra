package com.dianping.zebra.biz.monitor;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.jboss.netty.util.internal.ConcurrentHashMap;

public class TaskExecutorMetric {

	private AtomicLong totalBinlogNumber = new AtomicLong(0);

	private AtomicLong totalSyncBinlogNumber = new AtomicLong(0);

	private Map<String, AtomicLong> everyTableSyncBinlogNumber = new ConcurrentHashMap<String, AtomicLong>();

	private AtomicLong totalSkipBinlogNumber = new AtomicLong(0);

	private AtomicLong totalNoRowsAffected = new AtomicLong(0);

	private AtomicLong totalDuplicateKey = new AtomicLong(0);

	private Map<Integer, AtomicInteger> eachQueueSize = new ConcurrentHashMap<Integer, AtomicInteger>();

	private transient QpsCounter syncQpsCounter = new QpsCounter(15);

	private long syncQps;

	public AtomicLong getTotalBinlogNumber() {
		return totalBinlogNumber;
	}

	public void addTotalBinlog(long number) {
		this.totalBinlogNumber.addAndGet(number);
		this.syncQpsCounter.add(number);
		this.syncQps = syncQpsCounter.get(5);
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

	public void addEachQueueSize(int queueId) {
		AtomicInteger queueSize = eachQueueSize.get(queueId);

		if (queueSize == null) {
			queueSize = new AtomicInteger(0);

			eachQueueSize.put(queueId, queueSize);
		}

		queueSize.incrementAndGet();
	}

	public void decreaseEachQueueSize(int queueId) {
		AtomicInteger queueSize = eachQueueSize.get(queueId);

		if (queueSize != null) {
			queueSize.decrementAndGet();
		}
	}

	public void addSyncBinlogNumber(String table, long number) {
		this.totalSyncBinlogNumber.incrementAndGet();
		AtomicLong atomicLong = everyTableSyncBinlogNumber.get(table);

		if (atomicLong == null) {
			synchronized (everyTableSyncBinlogNumber) {
				atomicLong = everyTableSyncBinlogNumber.get(table);
				
				if(atomicLong == null){
					atomicLong = new AtomicLong(0);
					everyTableSyncBinlogNumber.put(table, atomicLong);
				}
         }
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

	public Map<Integer, AtomicInteger> getEachQueueSize() {
		return eachQueueSize;
	}

	public void setEachQueueSize(Map<Integer, AtomicInteger> eachQueueSize) {
		this.eachQueueSize = eachQueueSize;
	}

	public long getSyncQps() {
		return syncQps;
	}

	public void setSyncQps(long syncQps) {
		this.syncQps = syncQps;
	}
}
