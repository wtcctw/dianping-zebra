package com.dianping.zebra.shard;

import com.dianping.zebra.shard.config.XmlDataSourceRouterConfigLoaderTest;
import com.dianping.zebra.shard.jdbc.*;
import com.dianping.zebra.shard.parser.InsertTest;
import com.dianping.zebra.shard.parser.SelectTest;
import com.dianping.zebra.shard.router.DataSourceRouterImplTest;
import com.dianping.zebra.shard.router.rule.GroovyRuleEngineTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        //config
        XmlDataSourceRouterConfigLoaderTest.class,

        //jdbc
        ConnectionTest.class,
        DataSourceTest.class,
        MultiDBLifeCycleTest.class,
        MultiDBPreparedStatementLifeCycleTest.class,
        ResultSetTest.class,
        SingleDBLifeCycleTest.class,
        SingleDBPreparedStatementGroupFollowNoteIntegrationTest.class,
        SingleDBPreparedStatementLifeCycleTest.class,
        SpecialSQLTest.class,
        StatementTest.class,
        ShardConnectionTest.class,
        ShardStatementTest.class,
        ShardPreparedStatementTest.class,
        ShardPreparedStatementMultiKeyTest.class,

        //parser
        SelectTest.class,
        InsertTest.class,

        //router
        DataSourceRouterImplTest.class,
        ShardSupportedCaseTest.class,
        GroovyRuleEngineTest.class
})
public class AllTests {

}
