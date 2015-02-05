package com.dianping.zebra.shard;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.dianping.zebra.shard.router.DataSourceRouterImplTest;

@RunWith(Suite.class)
@SuiteClasses({
	
	//router
	DataSourceRouterImplTest.class,
	
})
public class AllTests {

}
