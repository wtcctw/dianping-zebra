package com.dianping.zebra.shard

import com.dianping.zebra.shard.jdbc.ZebraMultiDBBaseTestCase
import org.junit.Test

import javax.sql.DataSource
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 *
 * ===== data-multidb-lifecycle 分库分表规则 =====
 * dbRule="(#id#.intValue() % 8).intdiv(2)"
 * dbIndexes="id0,id1,id2,id3"
 * tbRule="#id#.intValue() % 2"
 * tbSuffix="alldb:[_0,_7]"
 * isMaster="true"
 *
 * dbRule="(#classid#.intValue() % 8).intdiv(2)"
 * dbIndexes="class0,class1,class2,class3"
 * tbRule="#classid#.intValue() % 2"
 * tbSuffix="alldb:[_class0,_class7]"
 * isMaster="false"
 * ==============================================
 */
class ShardSupportedCaseTest extends ZebraMultiDBBaseTestCase {
    @Override
    protected String getDBBaseUrl() {
        return "jdbc:h2:mem:";
    }

    @Override
    protected String getCreateScriptConfigFile() {
        return "db-datafiles/createtable-multidb-lifecycle.xml";
    }

    @Override
    protected String getDataFile() {
        return "db-datafiles/data-multidb-lifecycle.xml";
    }

    @Override
    protected String[] getSpringConfigLocations() {
        return ["ctx-multidb-lifecycle.xml"];
    }

    @Test
    public void "insert into test (name) values ('test')"() throws Exception {
        //todo: failed ! must fix it!
        println executeInsert(getZebraDs().getConnection(), "insert into test ( name, score, type, classid) values ('xxx', 1, 'a', 0)")
    }

    @Test
    public void "insert into test (id, name) values (1, 'test')"() throws Exception {
        assert 1 ==
                executeUpdate(getZebraDs().getConnection(),
                        "insert into test (id, name, score, type, classid) values (100, 'xxx', 1, 'a', 0)");
        def expectData = [[100, "xxx", 1, "a", 0]];
        assertData(getZebraDs().getConnection(), "select id,name,score,type,classid from test where id = 100", expectData);
        assertData(getInnerDs("id2").getConnection(), "select id,name,score,type,classid from test_4 where id = 100", expectData);
        assertData(getInnerDs("id0").getConnection(), "select id,name,score,type,classid from test_1 where id = 100", []);
    }

    @Test
    public void "select * from test"() {
        assert executeQuery(getZebraDs().getConnection(), "select * from test").size() > 0;
    }

    //todo: 不支持？？
    @Test
    public void "select * from test where xxx order by classid"() {
        def whereCondition = [
                "",
                "where id = 3",
        ];

        whereCondition.each {
            def sql = "select classid from test ${it} order by classid";
            println sql;
            def data = executeQuery(getZebraDs().getConnection(), sql);
            def lastClassid = null;

            data.each {
                if (lastClassid != null) {
                    assert it[0] >= lastClassid;
                }
                lastClassid = it[0];
            }
        }
    }

    @Test
    public void "select * from test where id < 100"() {
        def size1 = executeQuery(getZebraDs().getConnection(), "select * from test where  id <= 3").size();
        def size2 = executeQuery(getZebraDs().getConnection(), "select * from test where  id > 3").size();
        def sizeAll = executeQuery(getZebraDs().getConnection(), "select * from test").size();

        assert sizeAll == size1 + size2;
    }

    @Test
    public void "select * from test where id = 3"() {
        assert executeQuery(getZebraDs().getConnection(), "select * from test where  id = 3").size() > 0;
    }

    @Test
    public void "select * from test where id <> 3"() {
        assert executeQuery(getZebraDs().getConnection(), "select * from test where  id <> 3").size() > 0;
    }

    @Test
    public void "select * from test where id <> 3 limit 1"() {
        assert executeQuery(getZebraDs().getConnection(), "select * from test where  id <> 3 limit 1").size() == 1;
    }

    @Test
    public void "select * from test where id = 3 and classid = 100"() {
        assert executeQuery(getZebraDs().getConnection(), "select * from test where id = 3 and classid = 100").size() == 0;
        assert executeQuery(getZebraDs().getConnection(), "select * from test where id = 3 and classid = 3").size() > 0;
    }

    @Test
    public void "select * from test where id = 3 and name = 'x'"() {
        assert executeQuery(getZebraDs().getConnection(), "select * from test where id = 3 and name = 'leox'").size() == 0;
        assert executeQuery(getZebraDs().getConnection(), "select * from test where id = 3 and name = 'leo3'").size() > 0;
    }

    @Test
    public void "select * from test where id = 3 and name like '%x%'"() {
        assert executeQuery(getZebraDs().getConnection(), "select * from test where id = 3 and name like '%dozer%'").size() == 0;
        assert executeQuery(getZebraDs().getConnection(), "select * from test where id = 3 and name like '%leo%'").size() > 0;
    }

    @Test
    public void "select * from test where id > 3 and name like '%x%'"() {
        assert executeQuery(getZebraDs().getConnection(), "select * from test where id > 3 and name like '%dozer%'").size() == 0;
        assert executeQuery(getZebraDs().getConnection(), "select * from test where id > 3 and name like '%leo%'").size() > 0;
    }

    @Test
    public void "select * from test where id in (select id from test where id = 5)"() {
        assert executeQuery(getZebraDs().getConnection(), "select * from test where id in (select id from test where id= 5)").size() > 0;
    }

    @Test
    public void "select * from test where id in (select id from test where id > 2 and id < 10)"() {
        assert executeQuery(getZebraDs().getConnection(), "select * from test where id in (select id from test where id > 2 and id < 10)").size() > 0;
    }

    @Test
    public void "select count(id) from test"() {
        //todo: not support count!
        def count = executeQuery(getZebraDs().getConnection(), "select count(id) from test")[0][0];
        def max = executeQuery(getZebraDs().getConnection(), "select max(id) from test")[0][0];
        def min = executeQuery(getZebraDs().getConnection(), "select min(id) from test")[0][0];
        def sum = executeQuery(getZebraDs().getConnection(), "select sum(id) from test")[0][0];
        def avg = executeQuery(getZebraDs().getConnection(), "select avg(id) from test")[0][0];
    }

    @Test
    public void "select classid,count(id) from test group by classid"() {
        //todo:
    }

    @Test
    public void debug() {
        println executeUpdate(getZebraDs().getConnection(), "update test set name = 'newName' where classid = 3")
    }

    @Test
    public void "update test set name = 'newName'"() {
        def baseUpdate = "update test set name = 'newName' ";
        def baseQuery = "select name from test "
        def whereCondiction = [
                "",
                "where id = 3",
                "where id in (1,2,3)",
                "where id <> 3",
                //"where classid = 3", //不支持！
                //"where id in (select id from test where id = 3)", //不支持！
        ];

        whereCondiction.each {
            assert executeUpdate(getZebraDs().getConnection(), "${baseUpdate} ${it}") > 0;
            println "${baseUpdate} ${it}"

            executeQuery(getZebraDs().getConnection(), "${baseQuery} ${it}").each {
                assert it[0] == "newName";
            };
        };
    }

    @Test
    public void "delete from test"() {
        def baseUpdate = "delete from test ";
        def baseQuery = "select * from test "
        def whereCondiction = [
                "", //todo:error
                "where id = 3",
                "where id in (1,2,3)",
                "where id <> 3",
                //"where classid = 3", //不支持
                //"where id in (select id from test where id = 3)", //不支持
        ];

        whereCondiction.each {
            assert executeUpdate(getZebraDs().getConnection(), "${baseUpdate} ${it}") > 0;
            println "${baseUpdate} ${it}"
            assert executeQuery(getZebraDs().getConnection(), "${baseQuery} ${it}").size() == 0;
        };
    }

    protected DataSource getZebraDs() {
        return context.getBean("zebraDS");
    }

    protected DataSource getInnerDs(def name) {
        return context.getBean(name);
    }

    protected void assertData(Connection conn, String sql, List<List<Object>> expect) {
        def actual = executeQuery(conn, sql)
        assert expect.equals(actual);
    }

    protected def executeInsert(Connection conn, String sql, boolean closeConnection = true) {
        Statement stmt;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            return readResultSet(stmt.getGeneratedKeys());
        } finally {
            closeAll(stmt, conn);
            if (closeConnection) {
                closeAll(conn);
            }
        }
        return 0;
    }

    protected int executeUpdate(Connection conn, String sql, boolean closeConnection = true) {
        Statement stmt;
        try {
            stmt = conn.createStatement();
            return stmt.executeUpdate(sql);
        } finally {
            closeAll(stmt, conn);
            if (closeConnection) {
                closeAll(conn);
            }
        }
        return 0;
    }

    protected List<List<Object>> executeQuery(Connection conn, String sql, boolean closeConnection = true) {
        Statement stmt;
        ResultSet resultSet;
        try {
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(sql);
            return readResultSet(resultSet);
        } finally {
            closeAll(resultSet, stmt);
            if (closeConnection) {
                closeAll(conn);
            }
        }
        return new ArrayList<>();
    }

    protected ArrayList<List<Object>> readResultSet(ResultSet resultSet) {
        List<List<Object>> result = new ArrayList<>();
        def size = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            def row = new ArrayList<Object>();
            result.add(row);

            for (def k = 1; k <= size; k++) {
                row.add(resultSet.getObject(k));
            }
        }
        result
    }

    private void closeAll(Object... targets) {
        for (Object item : targets) {
            if (item == null) {
                continue;
            }

            try {
                item.close();
            } catch (Exception ignore) {
                ignore.printStackTrace();
            }
        }
    }
}
