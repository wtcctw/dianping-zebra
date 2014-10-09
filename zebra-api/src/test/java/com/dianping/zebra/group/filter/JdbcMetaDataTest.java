package com.dianping.zebra.group.filter;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Dozer on 9/2/14.
 */
public class JdbcMetaDataTest {
    @Test
    public void test_clone() {
        JdbcMetaData metaData = new JdbcMetaData();
        metaData.setJdbcUrl("abcd");

        JdbcMetaData clone = metaData.clone();

        Assert.assertEquals(clone.getJdbcUrl(), metaData.getJdbcUrl());
        Assert.assertTrue(clone != metaData);
        Assert.assertTrue(!clone.equals(metaData));
    }

    @Test
    @Ignore
    public void test_merge_sql() {
        JdbcMetaData metaData = new JdbcMetaData();
        metaData.setSql("select * from test where id = 1");
        Assert.assertEquals("select * from test where id = ?", metaData.getMergedSql());
    }
}
