package com.dianping.zebra.group.filter.stat;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Dozer on 9/15/14.
 */
public class RangeCounter {
	private final TreeMap<Long, AtomicLong> result = new TreeMap<Long, AtomicLong>();

	public RangeCounter(long... ranges) {
		result.put(0l, new AtomicLong());

		long last = 0l;
		for (Long number : ranges) {
			if (number < last) {
				continue;
			}
			last = number;
			result.put(number, new AtomicLong());
		}

		if (ranges.length == 0 || ranges[ranges.length - 1] != Integer.MAX_VALUE) {
			result.put(Long.MAX_VALUE, new AtomicLong());
		}
	}

	public static RangeCounter getDefaultTimeRange() {
		return new RangeCounter(5, 10, 50, 100, 200, 500, 1000, 5000);
	}

	public Map<Long, AtomicLong> getResult() {
		return result;
	}

	public void increment(long value) {
		AtomicLong temp = result.get(value);
		if (temp == null) {
			temp = result.higherEntry(value).getValue();
		}
		if (temp != null) {
			temp.incrementAndGet();
		}
	}
}