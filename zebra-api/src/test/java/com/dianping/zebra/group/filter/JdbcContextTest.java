package com.dianping.zebra.group.filter;

import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Dozer on 9/2/14.
 */
public class JdbcContextTest {
    @Test
    public void test_clone() {
        JdbcContext context = new JdbcContext();
        context.setJdbcUrl("abcd");

        JdbcContext clone = context.clone();

        Assert.assertEquals(clone.getJdbcUrl(), context.getJdbcUrl());
        Assert.assertTrue(clone != context);
        Assert.assertTrue(!clone.equals(context));
    }

    @Test
    public void test_merge_sql() {
        JdbcContext context = new JdbcContext();
        context.setSql("select * from test where id = 1");
        Assert.assertEquals("SELECT * FROM test WHERE id = $1", context.getMergedSql());
    }
}
