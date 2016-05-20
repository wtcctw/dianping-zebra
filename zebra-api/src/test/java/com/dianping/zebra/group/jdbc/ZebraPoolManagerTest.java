package com.dianping.zebra.group.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.junit.Test;

import com.dianping.zebra.Constants;
import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.single.jdbc.SingleDataSource;
import com.dianping.zebra.single.pool.ZebraPoolManager;

import java.sql.Statement;

public class ZebraPoolManagerTest {

	@Test
	public void testDruid() throws SQLException {
		
		List<Any> propsList = new ArrayList<Any>();
		Any any = new Any();
		any.setName("initialSize");
		any.setValue("1");
		propsList.add(any);
		any = new Any();
		any.setName("minIdle");
		any.setValue("1");
		propsList.add(any);
		any = new Any();
		any.setName("maxActive");
		any.setValue("20");
		propsList.add(any);
		any = new Any();
		any.setName("maxWait");
		any.setValue("60000");
		propsList.add(any);
		any = new Any();
		any.setName("timeBetweenEvictionRunsMillis");
		any.setValue("60000");
		propsList.add(any);
		any = new Any();
		any.setName("minEvictableIdleTimeMillis");
		any.setValue("300000");
		propsList.add(any);
		any = new Any();
		any.setName("validationQuery");
		any.setValue("select 1");
		propsList.add(any);
		any = new Any();
		any.setName("testWhileIdle");
		any.setValue("true");
		propsList.add(any);
		any = new Any();
		any.setName("testOnBorrow");
		any.setValue("false");
		propsList.add(any);
		any = new Any();
		any.setName("testOnReturn");
		any.setValue("false");
		propsList.add(any);
		any = new Any();
		any.setName("poolPreparedStatements");
		any.setValue("true");
		propsList.add(any);
		any = new Any();
		any.setName("maxPoolPreparedStatementPerConnectionSize");
		any.setValue("20");
		propsList.add(any);

		DataSourceConfig config = new DataSourceConfig();
		
		config.setType(Constants.CONNECTION_POOL_TYPE_DRUID);
		config.setActive(true);
		config.setCanRead(true);
		config.setCanWrite(true);
		config.setDriverClass("com.mysql.jdbc.Driver");
		config.setId("test-druid");
		config.setJdbcUrl("jdbc:mysql://10.1.101.216:3306/zebra?zeroDateTimeBehavior=convertToNull&characterEncoding=UTF8&socketTimeout=60000");
		config.setUsername("zebra_a_r");
		config.setPassword("dp!@VTWKvafGT");
		config.setProperties(propsList);
		
		DataSource ds = ZebraPoolManager.buildDataSource(config);
		
		Connection con = ds.getConnection("zebra_a_r", "dp!@VTWKvafGT");
		Statement st = con.createStatement();
		
		ResultSet result = st.executeQuery("select * form `cluster` where id=20");
		System.out.println(result.getString(2));
		
		ZebraPoolManager.close(new SingleDataSource(config,null));
	}
}
