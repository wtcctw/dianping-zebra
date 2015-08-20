package com.dianping.zebra.syncservice.executor;

import com.dianping.puma.core.model.BinlogInfo;
import com.dianping.zebra.biz.entity.PumaClientSyncTaskEntity;
import com.dianping.zebra.syncservice.executor.ShardSyncTaskExecutor;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Dozer @ 6/9/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class ShardSyncTaskExecutorTest {

	PumaClientSyncTaskEntity config;

	ShardSyncTaskExecutor target;

	@Before
	public void setUp() throws Exception {
		config = new PumaClientSyncTaskEntity();
		//
		//		config.setTableName("ut");
		//		config.setDbRule("#id# == null ? SKIP : ((#id#).toLong() % 2)");
		//		config.setDbIndexes("db0,db1");
		//		config.setTbRule("((#id# / 2).toLong() % 2)");
		//		config.setTbSuffix("everydb:[_0,_1]");

		target = new ShardSyncTaskExecutor(config);
	}

	@Test
	public void testGetAckBinlog1() throws Exception {
		target.rowEventProcessors = new ShardSyncTaskExecutor.RowEventProcessor[3];
		target.lastSendBinlogInfo = new BinlogInfo[3];
		for (int k = 0; k < 3; k++) {
			target.rowEventProcessors[k] = mock(ShardSyncTaskExecutor.RowEventProcessor.class);
		}

		Assert.assertNull(target.getAckBinlogInfo());
		for (int k = 0; k < 3; k++) {
			verify(target.rowEventProcessors[k], only()).getBinlogInfo();
		}
	}

	@Test
	public void testGetAckBinlog2() throws Exception {
		target.rowEventProcessors = new ShardSyncTaskExecutor.RowEventProcessor[2];
		target.lastSendBinlogInfo = new BinlogInfo[2];

		BinlogInfo binlogInfo0 = new BinlogInfo();
		BinlogInfo binlogInfo1 = new BinlogInfo();

		target.lastSendBinlogInfo[0] = binlogInfo0;
		target.lastSendBinlogInfo[1] = binlogInfo1;

		target.rowEventProcessors[0] = mock(ShardSyncTaskExecutor.RowEventProcessor.class);
		target.rowEventProcessors[1] = mock(ShardSyncTaskExecutor.RowEventProcessor.class);

		when(target.rowEventProcessors[1].getBinlogInfo()).thenReturn(binlogInfo1);

		Assert.assertNull(target.getAckBinlogInfo());
		verify(target.rowEventProcessors[0], only()).getBinlogInfo();
		verify(target.rowEventProcessors[1], never()).getBinlogInfo();
	}

	@Test
	public void testGetAckBinlog3() throws Exception {
		target.rowEventProcessors = new ShardSyncTaskExecutor.RowEventProcessor[3];
		target.lastSendBinlogInfo = new BinlogInfo[3];

		BinlogInfo binlogInfo0 = new BinlogInfo();
		BinlogInfo binlogInfo1 = new BinlogInfo();
		BinlogInfo binlogInfo2 = new BinlogInfo();
		binlogInfo0.setTimestamp(1);
		binlogInfo1.setTimestamp(3);
		binlogInfo2.setTimestamp(2);

		target.lastSendBinlogInfo[0] = binlogInfo0;
		target.lastSendBinlogInfo[1] = binlogInfo1;
		target.lastSendBinlogInfo[2] = binlogInfo2;

		target.rowEventProcessors[0] = mock(ShardSyncTaskExecutor.RowEventProcessor.class);
		target.rowEventProcessors[1] = mock(ShardSyncTaskExecutor.RowEventProcessor.class);
		target.rowEventProcessors[2] = mock(ShardSyncTaskExecutor.RowEventProcessor.class);

		when(target.rowEventProcessors[0].getBinlogInfo()).thenReturn(binlogInfo0);
		when(target.rowEventProcessors[1].getBinlogInfo()).thenReturn(binlogInfo1);
		when(target.rowEventProcessors[2].getBinlogInfo()).thenReturn(binlogInfo2);

		Assert.assertSame(binlogInfo1, target.getAckBinlogInfo());
		verify(target.rowEventProcessors[0], only()).getBinlogInfo();
		verify(target.rowEventProcessors[1], only()).getBinlogInfo();
		verify(target.rowEventProcessors[2], only()).getBinlogInfo();
	}

	@Test
	public void testGetAckBinlog4() throws Exception {
		target.rowEventProcessors = new ShardSyncTaskExecutor.RowEventProcessor[3];
		target.lastSendBinlogInfo = new BinlogInfo[3];

		BinlogInfo binlogInfo0 = new BinlogInfo();
		BinlogInfo binlogInfo1 = new BinlogInfo();
		BinlogInfo binlogInfo1_2 = new BinlogInfo();
		BinlogInfo binlogInfo2 = new BinlogInfo();
		BinlogInfo binlogInfo2_2 = new BinlogInfo();
		binlogInfo0.setTimestamp(1);
		binlogInfo1.setTimestamp(3);
		binlogInfo2.setTimestamp(2);

		target.lastSendBinlogInfo[0] = binlogInfo0;
		target.lastSendBinlogInfo[1] = binlogInfo1_2;
		target.lastSendBinlogInfo[2] = binlogInfo2_2;

		target.rowEventProcessors[0] = mock(ShardSyncTaskExecutor.RowEventProcessor.class);
		target.rowEventProcessors[1] = mock(ShardSyncTaskExecutor.RowEventProcessor.class);
		target.rowEventProcessors[2] = mock(ShardSyncTaskExecutor.RowEventProcessor.class);

		when(target.rowEventProcessors[0].getBinlogInfo()).thenReturn(binlogInfo0);
		when(target.rowEventProcessors[1].getBinlogInfo()).thenReturn(binlogInfo1);
		when(target.rowEventProcessors[2].getBinlogInfo()).thenReturn(binlogInfo2);

		Assert.assertSame(binlogInfo2, target.getAckBinlogInfo());
		verify(target.rowEventProcessors[0], only()).getBinlogInfo();
		verify(target.rowEventProcessors[1], only()).getBinlogInfo();
		verify(target.rowEventProcessors[2], only()).getBinlogInfo();
	}

	//
	//	@Test
	//	public void test_router() {
	//		Map<String, Object> args = new HashMap<String, Object>();
	//		target.initRouter();
	//		Number result = (Number) target.engine.eval(new RuleEngineEvalContext(args));
	//		Assert.assertEquals(-1, result.intValue());
	//	}
	//
	//	@Test
	//	public void test_init_processor_and_thread() throws Exception {
	//		test_init_event_queue();
	//		target.initProcessors();
	//
	//		Assert.assertEquals(target.NUMBER_OF_PROCESSORS, target.rowEventProcessors.size());
	//		Assert.assertEquals(target.NUMBER_OF_PROCESSORS, target.rowEventProcesserThreads.size());
	//	}
	//
	//	@Test
	//	public void test_init_event_queue() throws Exception {
	//		target.dataSources = new HashMap<String, GroupDataSource>();
	//		target.dataSources.put("a", new GroupDataSource());
	//		target.dataSources.put("b", new GroupDataSource());
	//		target.initEventQueues();
	//		Assert.assertTrue(target.eventQueues.length == target.NUMBER_OF_PROCESSORS);
	//	}
	//
	//	@Test
	//	public void test_init_datasource() {
	//		target = spy(target);
	//		doAnswer(new Answer<GroupDataSource>() {
	//			@Override
	//			public GroupDataSource answer(InvocationOnMock invocation) throws Throwable {
	//				return new GroupDataSource();
	//			}
	//		}).when(target).initGroupDataSource(anyString());
	//
	//		target.initRouter();
	//		target.initDataSources();
	//
	//		verify(target, times(1)).initGroupDataSource(eq("db0"));
	//		verify(target, times(1)).initGroupDataSource(eq("db1"));
	//		verify(target, times(2)).initGroupDataSource(anyString());
	//	}
	//
	//	@Test
	//	public void test_process_pk_removepk_add_shard_key() throws Exception {
	//		ShardSyncTaskExecutor.RowEventProcessor listener = target.new RowEventProcessor(null);
	//
	//		RowChangedEvent event = new RowChangedEvent();
	//		config.setPk("UserId");
	//		Map<String, RowChangedEvent.ColumnInfo> columns = new HashMap<String, RowChangedEvent.ColumnInfo>();
	//		columns.put("ID", new RowChangedEvent.ColumnInfo(true, 1, 1));
	//		columns.put("UserId", new RowChangedEvent.ColumnInfo(false, 1, 1));
	//		event.setColumns(columns);
	//		listener.processPK(event);
	//
	//		Assert.assertEquals(1, event.getColumns().size());
	//		Assert.assertTrue(event.getColumns().get("UserId").isKey());
	//	}
	//
	//	@Test
	//	public void test_process_pk_pk_is_shard_key() throws Exception {
	//		ShardSyncTaskExecutor.RowEventProcessor listener = target.new RowEventProcessor(null);
	//
	//		RowChangedEvent event = new RowChangedEvent();
	//		config.setPk("UserId");
	//		Map<String, RowChangedEvent.ColumnInfo> columns = new HashMap<String, RowChangedEvent.ColumnInfo>();
	//		columns.put("OrderId", new RowChangedEvent.ColumnInfo(false, 1, 1));
	//		columns.put("UserId", new RowChangedEvent.ColumnInfo(true, 1, 1));
	//		event.setColumns(columns);
	//		listener.processPK(event);
	//
	//		Assert.assertEquals(2, event.getColumns().size());
	//		Assert.assertTrue(event.getColumns().get("UserId").isKey());
	//		Assert.assertFalse(event.getColumns().get("OrderId").isKey());
	//	}
	//
	//	@Test
	//	public void test_get_oldest_binlog() throws Exception {
	//		BinlogInfo info1 = new BinlogInfo(1, "mysql.002", 80l, 0, 1);
	//		BinlogInfo info2 = new BinlogInfo(1, "mysql.002", 100l, 0, 2);
	//		BinlogInfo info3 = new BinlogInfo(1, "mysql.003", 1l, 0, 3);
	//
	//		BinlogInfo result = target.getOldestBinlog(Lists.newArrayList(info1, info2, info3));
	//
	//		Assert.assertEquals(info1.getBinlogFile(), result.getBinlogFile());
	//		Assert.assertEquals(info1.getBinlogPosition(), result.getBinlogPosition());
	//	}
	//
	//	@Test
	//	@Ignore
	//	public void testPuma() throws IOException {
	//		PumaClientSyncTaskEntity config = new PumaClientSyncTaskEntity();
	//
	//		config.setId(3);
	//		config.setPumaClientName("test");
	//		config.setRuleName("test");
	//		config.setTableName("debug");
	//		config.setDbRule("(#id# % 2).intValue()");
	//		config.setDbIndexes("localhost,localhost");
	//		config.setTbRule("(#id# % 2).intValue()");
	//		config.setTbSuffix("everydb:[_0,_1]");
	//		config.setPumaTables("debug");
	//		config.setPumaDatabase("test");
	//		config.setPk("id");
	//
	//		ShardSyncTaskExecutor target = new ShardSyncTaskExecutor(config);
	//		target.init();
	//
	//		target.start();
	//		System.in.read();
	//	}
}