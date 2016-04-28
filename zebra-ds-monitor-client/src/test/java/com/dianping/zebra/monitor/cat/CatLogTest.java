package com.dianping.zebra.monitor.cat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.group.util.DaoContextHolder;
import com.dianping.zebra.shard.jdbc.ShardDataSource;

public class CatLogTest {

	//@Test
	public void testGroup() throws SQLException, IOException{
		GroupDataSource ds = new GroupDataSource("zebra");
		ds.init();
		
		Connection conn = ds.getConnection();
		
		DaoContextHolder.setSqlName("TestPreparead");
		PreparedStatement stmt = conn.prepareStatement("select * from Cluster where Name = ?");
		
		stmt.setString(1, "wed");
		
		stmt.executeQuery();
		
		System.in.read();
	}
	
	//@Test
	public void testShard() throws SQLException, IOException{
		ShardDataSource ds = new ShardDataSource();
		ds.setRuleName("rsreceipt");
		ds.init();
		
		Connection conn = ds.getConnection();
		DaoContextHolder.setSqlName("TestShardPreparead");

		PreparedStatement stmt = conn.prepareStatement("select * from RS_Receipt where UserID in (? ,?)");
		
		stmt.setInt(1, 1797130);
		stmt.setInt(2, 1797131);
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			System.out.println(rs.getInt(2));
		}
		System.in.read();
	}
}
