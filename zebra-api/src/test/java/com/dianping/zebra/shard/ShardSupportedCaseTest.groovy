package com.dianping.zebra.shard

import com.dianping.zebra.shard.jdbc.ZebraMultiDBBaseTestCase
import org.junit.Test

import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
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
    public void test_query() throws Exception {
        println executeQuery(context.getBean("id0").getConnection(), "select NOW(),2")
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

            List<List<Object>> result = new ArrayList<>();
            def size = resultSet.getMetaData().getColumnCount();
            while (resultSet.next()) {
                def row = new ArrayList<Object>();
                result.add(row);

                for (def k = 1; k <= size; k++) {
                    row.add(resultSet.getObject(k));
                }
            }

            return result;
        } finally {
            closeAll(resultSet, stmt);
            if (closeConnection) {
                closeAll(conn);
            }
        }
        return new ArrayList<>();
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
