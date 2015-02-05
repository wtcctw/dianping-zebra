package com.dianping.zebra.monitor.filter;

import com.dianping.zebra.group.Constants;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import groovy.sql.Sql;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by Dozer on 9/9/14.
 */

public class CatFilterTest {
    GroupDataSource ds = new GroupDataSource();

    @Before
    public void init() throws SQLException {
        ds.setConfigManagerType(Constants.CONFIG_MANAGER_TYPE_LOCAL);
        ds.setJdbcRef("sample.ds.v2");
        ds.init();

        Sql sql = new Sql(ds.getConnection());
        sql.execute("CREATE TABLE Persons\n" +
                "(\n" +
                "Id int,\n" +
                "LastName varchar(255),\n" +
                "FirstName varchar(255),\n" +
                "Address varchar(255),\n" +
                "City varchar(255)\n" +
                ")");
        sql.execute("insert into persons (id,lastname,firstname,address,city) values (1,'','','','')");
    }

    @Test
    public void test_sql_success() throws SQLException {
        new Sql(ds.getConnection()).execute(Constants.SQL_FORCE_WRITE_HINT + "select * from Persons");
    }

    @Test(expected = Exception.class)
    public void test_sql_fail() throws SQLException {
        new Sql(ds.getConnection()).execute("select * from xxx");
    }
}