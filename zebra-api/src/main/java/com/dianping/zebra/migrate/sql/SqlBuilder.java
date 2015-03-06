package com.dianping.zebra.migrate.sql;

import com.dianping.zebra.migrate.task.TaskConfig;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public final class SqlBuilder {
    private SqlBuilder() {
    }

    private static final String SELECT_SQL_TEMPLATE = "SELECT * FROM %s WHERE %s >= ? AND %s <= ? ORDER BY %s LIMIT ?,?";

    private static final String INSERT_SQL_TEMPLATE = "INSERT INTO %s (%s) VALUES (%s)";

    public static String getInsert(TaskConfig config, ResultSet resultSet) throws SQLException {
        StringBuilder names = new StringBuilder();
        StringBuilder values = new StringBuilder();

        int columnCount = resultSet.getMetaData().getColumnCount();
        for (int k = 1; k <= columnCount; k++) {
            if (names.length() > 0) {
                names.append(",");
            }
            if (values.length() > 0) {
                values.append(",");
            }
            values.append("?");

            names.append(resultSet.getMetaData().getColumnName(k));
        }

        return String.format(INSERT_SQL_TEMPLATE, config.getTableName(), names.toString(), values.toString());
    }

    public static String getSelect(TaskConfig config) {
        return String.format(SELECT_SQL_TEMPLATE, config.getTableName(), config.getKeyName(), config.getKeyName(), config.getKeyName());
    }
}
