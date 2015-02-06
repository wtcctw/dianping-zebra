package com.dianping.zebra.shard;

import com.dianping.zebra.shard.router.DataSourceRouterImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({

        //router
        DataSourceRouterImplTest.class,
        ShardSupportedCaseTest.class

})
public class AllTests {

}
