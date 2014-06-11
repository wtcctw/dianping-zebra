package com.dianping.zebra.group.datasources;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class CountPunisher {
	//ERROR 1290 (HY000): The MySQL server is running with the --read-only option so it cannot execute this statement
    private final int READ_ONLY_ERROR_CODE = 1290;

    private final String READ_ONLY_ERROR_MESSAGE = "read-only";

	private final MarkableDataSource dataSource;

	private final long timeWindow; // ms; 值为0表示关闭这个功能，无任何限制

	private final long limit;

	private final long resetTime;

	private volatile long timeWindowBegin = 0; // 0表示没有超时，没有计时

	private volatile long punishBeginTime = Long.MAX_VALUE;

	private final AtomicInteger count = new AtomicInteger();

	/**
	 * timeWindow之内，超时（或其他意义）次数超过limit，则标识为punish, 这个punish标志resetTime过后自动复位
	 * 
	 * @param smoothValve
	 * @param timeWindow
	 * @param limit
	 */
	public CountPunisher(MarkableDataSource dataSource, long timeWindow, long limit, long resetTime) {
		this.dataSource = dataSource;
		this.timeWindow = timeWindow;
		this.limit = limit;
		this.resetTime = resetTime;
	}

	public CountPunisher(MarkableDataSource dataSource, long timeWindow, long limit) {
		this(dataSource, timeWindow, limit, 5 * 60 * 100); // 默认5分钟复位
	}

	private void count() {
		if (timeWindow == 0) {
			return;
		}
		long now = System.currentTimeMillis();
		if (now - timeWindowBegin > timeWindow) {
			resetTimeWindow();
		}

		if (timeWindowBegin == 0) {
			timeWindowBegin = now;
		}

		if (count.incrementAndGet() >= limit) {
			punishBeginTime = now;
			dataSource.markDown();
		}
	}

	private void resetTimeWindow() {
		timeWindowBegin = 0;
		count.set(0);
	}

	/**
	 * @return true表示惩罚；false表示通过
	 */
	public boolean punish() {
		if (punishBeginTime == Long.MAX_VALUE || timeWindow == 0) {
			// 这里不需要复位
			return false;
		}
		if (System.currentTimeMillis() - punishBeginTime > resetTime) {
			// 超过复位时间，不再惩罚，状态复位
			punishBeginTime = Long.MAX_VALUE;
			resetTimeWindow();
			dataSource.markUp();
			return false;
		}

		return true;
	}

	public void countAndPunish(SQLException e) {
		if (e.getErrorCode() == READ_ONLY_ERROR_CODE && e.getMessage().contains(READ_ONLY_ERROR_MESSAGE)) {
			count();
		} else {
			//TODO getConnnetion loss exception 
		}
	}
}