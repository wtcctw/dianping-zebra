package com.dianping.zebra.group.filter.wall;

/**
 * Created by Dozer on 9/24/14.
 */

import com.dianping.zebra.group.filter.JdbcMetaData;
import com.dianping.zebra.group.util.StringUtils;
import junit.framework.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

public class WallFilterTest {
    @Test
    public void generateIdPerformanceTest() throws NoSuchAlgorithmException {
        long startTime = System.currentTimeMillis();
        for (int k = 0; k < 10000; k++) {
            StringUtils.sha1(String.valueOf(k % 1000));
        }
        long time1 = System.currentTimeMillis() - startTime;

        WallFilter filter = new WallFilter();
        startTime = System.currentTimeMillis();
        for (int k = 0; k < 10000; k++) {
            filter.generateId(String.valueOf(k % 1000));
        }

        long time2 = System.currentTimeMillis() - startTime;

        System.out.println(time1);
        System.out.println(time2);

        Assert.assertTrue(time2 < time1);
    }

    @Test
    public void test_add_id_to_sql() {
        WallFilter filter = new WallFilter();
        JdbcMetaData metaData = new JdbcMetaData();
        filter.addIdToSql("select * from user", metaData);
    }

    @Test
    public void test_get_id_from_sql() {
        WallFilter filter = new WallFilter();
        Assert.assertEquals("7yhgtr45ty", filter.getIdFromSQL("select * from user/*z:7yhgtr45ty*/"));
        Assert.assertEquals(null, filter.getIdFromSQL("select * from user/*z:7yhgtr45ty111*/"));
        Assert.assertEquals("7yhgtr45ty", filter.getIdFromSQL("select * from user/*z:7yhgtr45ty*/\\r\\n/*xxx*/"));
    }
}
