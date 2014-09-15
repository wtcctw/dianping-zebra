package com.dianping.zebra.group.filter.stat;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Dozer on 9/15/14.
 */
public final class StatContext {

	public final static AtomicLong closeGroupConnectionErrorCount = new AtomicLong();

	public final static AtomicLong closeGroupConnectionSuccessCount = new AtomicLong();

	public final static AtomicLong deleteErrorCount = new AtomicLong();

	public final static AtomicLong deleteSuccessCount = new AtomicLong();

	public final static AtomicLong getGroupConnectionErrorCount = new AtomicLong();

	public final static AtomicLong getGroupConnectionSuccessCount = new AtomicLong();

	public final static AtomicLong insertErrorCount = new AtomicLong();

	public final static AtomicLong insertSuccessCount = new AtomicLong();

	public final static AtomicLong selectErrorCount = new AtomicLong();

	public final static AtomicLong selectSuccessCount = new AtomicLong();

	public final static AtomicLong updateErrorCount = new AtomicLong();

	public final static AtomicLong updateSuccessCount = new AtomicLong();

	private StatContext() {
	}
}
