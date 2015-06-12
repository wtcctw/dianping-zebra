package com.dianping.zebra.admin.util;

import com.dianping.puma.core.event.RowChangedEvent;
import com.dianping.puma.core.util.sql.DMLType;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Dozer @ 6/12/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class SqlBuilderTest {

//	@Benchmark  //标注这是一个微基准测试
//	@BenchmarkMode(Mode.Throughput)  //测试模式，这里是测试吞吐量
//	@OutputTimeUnit(TimeUnit.MILLISECONDS)  //时间单位
//	@Threads(value = 5)  //线程数
//	@Warmup(iterations = 2, time = 1)  //热身次数
//	@Measurement(iterations = 5, time = 1)  //循环次数和每次循环的时间
//	@Fork(value = 2)  //进程数
	@Test
	public void test_build_sql() {
		Map<String, RowChangedEvent.ColumnInfo> columns = new HashMap<String, RowChangedEvent.ColumnInfo>();
		columns.put("Id", new RowChangedEvent.ColumnInfo(true, 1, 1));
		for (int k = 0; k < 10; k++) {
			columns.put(String.valueOf(k), new RowChangedEvent.ColumnInfo(false, String.valueOf(k), String.valueOf(k)));
		}
		RowChangedEvent event = new RowChangedEvent();
		event.setDmlType(DMLType.UPDATE);
		event.setColumns(columns);
		SqlBuilder.buildSql(event);
	}
}