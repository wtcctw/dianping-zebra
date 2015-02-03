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

    protected DataSource getZebraDs() {
        return context.getBean("zebraDS");
    }

    protected DataSource getInnerDs(def name) {
        return context.getBean(name);
    }

    @Test
    public void test_insert_and_generate_key() throws Exception {
        //todo: failed ! must fix it!
        println executeInsert(getZebraDs().getConnection(), "insert into test ( name, score, type, classid) values ('xxx', 1, 'a', 0)")
    }

    @Test
    public void test_insert_with_key() throws Exception {
        assert 1 ==
                executeUpdate(getZebraDs().getConnection(),
                        "insert into test (id, name, score, type, classid) values (100, 'xxx', 1, 'a', 0)");
        def expectData = [[100, "xxx", 1, "a", 0]];
        assertData(getZebraDs().getConnection(), "select id,name,score,type,classid from test where id = 100", expectData);
        assertData(getInnerDs("id1").getConnection(), "select id,name,score,type,classid from test_2 where id = 100", expectData);
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
