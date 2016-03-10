package com.dianping.zebra.shard;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.dianping.zebra.shard.config.XmlDataSourceRouterConfigLoaderTest;
import com.dianping.zebra.shard.jdbc.ConnectionTest;
import com.dianping.zebra.shard.jdbc.DataSourceTest;
import com.dianping.zebra.shard.jdbc.MultiDBLifeCycleTest;
import com.dianping.zebra.shard.jdbc.MultiDBPreparedStatementLifeCycleTest;
import com.dianping.zebra.shard.jdbc.ResultSetTest;
import com.dianping.zebra.shard.jdbc.ShardConnectionTest;
import com.dianping.zebra.shard.jdbc.ShardPreparedStatementMultiKeyTest;
import com.dianping.zebra.shard.jdbc.ShardPreparedStatementTest;
import com.dianping.zebra.shard.jdbc.ShardStatementTest;
import com.dianping.zebra.shard.jdbc.ShardSupportedCaseTest;
import com.dianping.zebra.shard.jdbc.SingleDBLifeCycleTest;
import com.dianping.zebra.shard.jdbc.SingleDBPreparedStatementGroupFollowNoteIntegrationTest;
import com.dianping.zebra.shard.jdbc.SingleDBPreparedStatementLifeCycleTest;
import com.dianping.zebra.shard.jdbc.SpecialSQLTest;
import com.dianping.zebra.shard.jdbc.StatementTest;
import com.dianping.zebra.shard.parser.MySQLParserResultTest;
import com.dianping.zebra.shard.parser.SQLRewriteTest;
import com.dianping.zebra.shard.router.DataSourceRouterImplTest;
import com.dianping.zebra.shard.router.rule.GroovyRuleEngineTest;

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
        MySQLParserResultTest.class,
        SQLRewriteTest.class,

        //router
        DataSourceRouterImplTest.class,
        ShardSupportedCaseTest.class,
        GroovyRuleEngineTest.class
})
public class AllTests {

}
