package com.dianping.zebra.group.util;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;

/**
 * @author Dozer <br/>
 *         重新加载 DataSource 的时候设定一个延时，防止同一时间大量的 Connection 对数据库服务器造成冲击。
 */

public class SmoothReload {
	private static final long DEFAULT_MAX_MILLISECOND_INTERVAL = 1000;

	private static Random rnd = new Random();

	private long maxMillisecondInterval = 0;

	private long randomInterval = 0;

	private long startMillisecond = 0;

	// todo:add construct load with config

	public SmoothReload() {
		this(DEFAULT_MAX_MILLISECOND_INTERVAL);
	}

	public SmoothReload(long maxMillisecondInterval) {
		this.maxMillisecondInterval = maxMillisecondInterval;
		this.startMillisecond = System.currentTimeMillis();
		initRandomInterval();
	}

	public void initRandomInterval() {
		this.randomInterval = (long) (rnd.nextDouble() * maxMillisecondInterval);
	}

	public void waitForReload() {
		if (maxMillisecondInterval <= 0) {
			return;
		}

		Transaction t = Cat.newTransaction("DAL", "DataSource.Refresh.Soomth");
		while (startMillisecond + randomInterval > System.currentTimeMillis()) {
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException ignore) {
			}
		}
		t.setStatus(Message.SUCCESS);
		t.complete();
	}
}