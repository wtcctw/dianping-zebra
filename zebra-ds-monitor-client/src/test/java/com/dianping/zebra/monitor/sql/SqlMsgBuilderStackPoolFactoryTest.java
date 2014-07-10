package com.dianping.zebra.monitor.sql;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Stack;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.StackObjectPool;
import org.junit.Before;
import org.junit.Test;

import com.dianping.hawk.protocol.Messages.SqlMsg;
import com.dianping.hawk.protocol.Messages.SqlMsg.Builder;


public class SqlMsgBuilderStackPoolFactoryTest {
	
	private ObjectPool objectPool;
	private static AtomicInteger errorCounter = new AtomicInteger();
	
	@Before
	public void setUp() {
		objectPool = new StackObjectPool(new SqlMsgBuilderStackPoolFactory(), 250, 200);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testPool() throws  Exception {
		Stack<Builder> stack = (Stack<Builder>) objectPool.borrowObject();
		try {
			assertNotNull(stack);
			assertTrue(stack.isEmpty());
		} finally {
			if (stack != null) {
				stack.push(SqlMsg.newBuilder());
				assertFalse(stack.isEmpty());
				objectPool.returnObject(stack);
			}
		}
		Stack<Builder> newStack = (Stack<Builder>) objectPool.borrowObject();
		assertSame(stack, newStack);
		assertNotNull(newStack);
		assertTrue(newStack.isEmpty());
		boolean hasError = false;
		for (int i = 0; i < 400; i++) {
			stack = (Stack<Builder>) objectPool.borrowObject();
			if (stack == null) {
				hasError = true;
				break;
			}
		}
		assertFalse(hasError);
		CountDownLatch latch = new CountDownLatch(400);
		for (int i = 0; i < 400; i++) {
			new UseSqlMsgBuilderStackPool(latch).start();
		}
		latch.await();
		assertTrue(errorCounter.intValue() == 0);
	}
	
	class UseSqlMsgBuilderStackPool extends Thread {
		private final CountDownLatch latch;

		public UseSqlMsgBuilderStackPool(CountDownLatch latch) {
			this.latch = latch;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			try {
				Stack<Builder> stack = (Stack<Builder>) objectPool.borrowObject();
				if (stack == null || !stack.isEmpty()) {
					errorCounter.incrementAndGet();
				}
				stack.push(SqlMsg.newBuilder());
				Thread.sleep(200);
				objectPool.returnObject(stack);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				latch.countDown();
			}
		}
	}

}
