package com.dianping.zebra.group.router;

import java.util.ServiceLoader;

import junit.framework.Assert;

import org.junit.Test;

public class ReadWriteStrategyLoaderTest {

	@Test
	public void load_dpdl_readWriteStrategy(){
		ServiceLoader<ReadWriteStrategy> strategies = ServiceLoader.load(ReadWriteStrategy.class);

		int size = 0;
		if (strategies != null) {
			for (ReadWriteStrategy strategy : strategies) {
				size++;
				Assert.assertEquals(true, strategy instanceof DpdlReadWriteStrategy);
			}
			
			Assert.assertEquals(1, size);
		}
	}
}
