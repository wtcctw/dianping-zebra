package com.dianping.zebra.admin.job.executor;

import com.dianping.zebra.admin.entity.PumaClientSyncTaskEntity;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.shard.router.RouterResult;
import com.google.gson.Gson;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * Dozer @ 6/9/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class PumaClientSyncTaskExecutorTest {
    PumaClientSyncTaskEntity config;

    PumaClientSyncTaskExecutor target;

    @Before
    public void setUp() throws Exception {
        config = new PumaClientSyncTaskEntity();

        config.setTable("ut");
        config.setDbRule("((#id#).toLong() % 2)");
        config.setDbIndexes("db0,db1");
        config.setTbRule("((#id# / 2).toLong() % 2)");
        config.setTbSuffix("everydb:[_0,_1]");

        target = new PumaClientSyncTaskExecutor(config);
    }

    @Test
    public void test_init_shard_rule() throws Exception {
        target.initRouter();
        RouterResult routerTarget = target.shardRouter.router("insert into ut (id,name) values (1,'xx')", null);
        System.out.println(new Gson().toJson(routerTarget));
        Assert.assertEquals(1, routerTarget.getTargetedSqls().size());
        Assert.assertEquals(1, routerTarget.getTargetedSqls().get(0).getSqls().size());
        Assert.assertEquals("db1", routerTarget.getTargetedSqls().get(0).getDataSourceName());
        Assert.assertTrue(routerTarget.getTargetedSqls().get(0).getSqls().get(0).contains("ut_0"));
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

        target.initRule();
        target.initDataSources();

        verify(target, times(1)).initGroupDataSource(eq("db0"));
        verify(target, times(1)).initGroupDataSource(eq("db1"));
        verify(target, times(2)).initGroupDataSource(anyString());
    }

    @Test
    @Ignore
    public void testPuma() throws IOException {
        config.setPumaTaskName("dpshop@server_beta");
        config.setPumaDatabase("DPShop");
        config.setPumaTables("DP_Shop");
        target.initPumaClient();
        target.start();

        System.in.read();
    }
}