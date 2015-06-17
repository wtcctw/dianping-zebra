package com.dianping.zebra.syncservice.job.executor;

import com.dianping.puma.core.event.RowChangedEvent;
import com.dianping.puma.core.model.BinlogInfo;
import com.dianping.zebra.admin.entity.PumaClientStatusEntity;
import com.dianping.zebra.admin.entity.PumaClientSyncTaskEntity;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * Dozer @ 6/9/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class ShardSyncTaskExecutorTest {

	PumaClientSyncTaskEntity config;

	PumaClientStatusEntity status;

	ShardSyncTaskExecutor target;

	@Before
	public void setUp() throws Exception {
		config = new PumaClientSyncTaskEntity();
		status = new PumaClientStatusEntity();

		config.setTableName("ut");
		config.setDbRule("((#id#).toLong() % 2)");
		config.setDbIndexes("db0,db1");
		config.setTbRule("((#id# / 2).toLong() % 2)");
		config.setTbSuffix("everydb:[_0,_1]");

		target = new ShardSyncTaskExecutor(config);
	}

	@Test
	public void test_router() {

	}

	@Test
	public void test_init_processor_and_thread() throws Exception {
		test_init_event_queue();
		target.initProcessors();

		Assert.assertEquals(target.NUMBER_OF_PROCESSORS, target.rowEventProcessors.size());
		Assert.assertEquals(target.NUMBER_OF_PROCESSORS, target.rowEventProcesserThreads.size());
	}

	@Test
	public void test_init_event_queue() throws Exception {
		target.dataSources = new HashMap<String, GroupDataSource>();
		target.dataSources.put("a", new GroupDataSource());
		target.dataSources.put("b", new GroupDataSource());
		target.initEventQueues();
		Assert.assertTrue(target.eventQueues.length == target.NUMBER_OF_PROCESSORS);
	}

	@Test
	public void test_init_datasource() {
		target = spy(target);
		doAnswer(new Answer<GroupDataSource>() {
			@Override
			public GroupDataSource answer(InvocationOnMock invocation) throws Throwable {
				return new GroupDataSource();
			}
		}).when(target).initGroupDataSource(anyString());

		target.initRouter();
		target.initDataSources();

		verify(target, times(1)).initGroupDataSource(eq("db0"));
		verify(target, times(1)).initGroupDataSource(eq("db1"));
		verify(target, times(2)).initGroupDataSource(anyString());
	}

	@Test
	public void test_process_pk_removepk_add_shard_key() throws Exception {
		ShardSyncTaskExecutor.RowEventProcessor listener = target.new RowEventProcessor(null);

		RowChangedEvent event = new RowChangedEvent();
		config.setPk("UserId");
		Map<String, RowChangedEvent.ColumnInfo> columns = new HashMap<String, RowChangedEvent.ColumnInfo>();
		columns.put("ID", new RowChangedEvent.ColumnInfo(true, 1, 1));
		columns.put("UserId", new RowChangedEvent.ColumnInfo(false, 1, 1));
		event.setColumns(columns);
		listener.processPK(event);

		Assert.assertEquals(1, event.getColumns().size());
		Assert.assertTrue(event.getColumns().get("UserId").isKey());
	}

	@Test
	public void test_process_pk_pk_is_shard_key() throws Exception {
		ShardSyncTaskExecutor.RowEventProcessor listener = target.new RowEventProcessor(null);

		RowChangedEvent event = new RowChangedEvent();
		config.setPk("UserId");
		Map<String, RowChangedEvent.ColumnInfo> columns = new HashMap<String, RowChangedEvent.ColumnInfo>();
		columns.put("OrderId", new RowChangedEvent.ColumnInfo(false, 1, 1));
		columns.put("UserId", new RowChangedEvent.ColumnInfo(true, 1, 1));
		event.setColumns(columns);
		listener.processPK(event);

		Assert.assertEquals(2, event.getColumns().size());
		Assert.assertTrue(event.getColumns().get("UserId").isKey());
		Assert.assertFalse(event.getColumns().get("OrderId").isKey());
	}

	@Test
	public void test_get_oldest_binlog() throws Exception {
		ShardSyncTaskExecutor.BinlogReporter reporter = target.new BinlogReporter();

		BinlogInfo info1 = new BinlogInfo("mysql.002", 80l);
		BinlogInfo info2 = new BinlogInfo("mysql.002", 100l);
		BinlogInfo info3 = new BinlogInfo("mysql.003", 1l);

		BinlogInfo result = reporter.getOldestBinlog(Lists.newArrayList(info1, info2, info3));

		Assert.assertEquals(info1.getBinlogFile(), result.getBinlogFile());
		Assert.assertEquals(info1.getBinlogPosition(), result.getBinlogPosition());
	}

	@Test
	@Ignore
	public void testPuma() throws IOException {
		PumaClientSyncTaskEntity config = new PumaClientSyncTaskEntity();

		config.setId(3);
		config.setPumaClientName("UnifiedOrder0@UOD_Order@ShopID");
		config.setRuleName("unifiedorder");
		config.setTableName("UOD_Order");
		config.setDbRule("(date(#AddTime#).year - 114 < 0 ? 0 :date(#AddTime#).year - 114).intValue()");
		config.setDbIndexes("ordershop0,ordershop1");
		config.setTbRule("((date(#AddTime#).month - 1)/3).intValue()");
		config.setTbSuffix("everydb:[_Time0,_Time3]");
		config.setPumaTables("UOD_Order0,UOD_Order1");
		config.setPumaDatabase("UnifiedOrder0");
		config.setPk("OrderID");

		ShardSyncTaskExecutor target = new ShardSyncTaskExecutor(config);
		target.init();

		target.start();
		System.in.read();
	}
}