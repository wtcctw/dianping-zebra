package com.dianping.zebra.admin.job.executor;

import com.dianping.zebra.admin.entity.PumaClientSyncTaskConfig;
import com.dianping.zebra.shard.router.RouterResult;
import com.dianping.zebra.shard.router.ShardRouter;
import com.google.common.base.Objects;
import com.google.gson.Gson;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Dozer @ 6/9/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class PumaClientSyncTaskExecutorTest {
    @Test
    public void test_init_shard_rule() throws Exception {
        PumaClientSyncTaskConfig config = new PumaClientSyncTaskConfig();

        config.setTable("ut");
        config.setDbRule("((#id#).toLong() % 2)");
        config.setDbIndexes("db0,db1");
        config.setTbRule("((#id# / 2).toLong() % 2)");
        config.setTbSuffix("everydb:[_0,_1]");

        PumaClientSyncTaskExecutor target = new PumaClientSyncTaskExecutor(config);
        ShardRouter actual = target.initRouter();

        RouterResult routerTarget = actual.router("insert into ut (id,name) values (1,'xx')", null);

        System.out.println(new Gson().toJson(routerTarget));

        Assert.assertEquals(1, routerTarget.getTargetedSqls().size());
        Assert.assertEquals(1, routerTarget.getTargetedSqls().get(0).getSqls().size());
        Assert.assertEquals("db1", routerTarget.getTargetedSqls().get(0).getDataSourceName());
        Assert.assertTrue(routerTarget.getTargetedSqls().get(0).getSqls().get(0).contains("ut_0"));
    }
}