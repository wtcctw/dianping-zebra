/**
 * Project: com.dianping.zebra.zebra-client-0.1.0
 * 
 * File Created at 2011-6-22
 * $Id$
 * 
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.dianping.zebra.router;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dianping.zebra.jdbc.MockDataSource;

/**
 * TODO Comment of DataSourceRouterTest
 * @author danson.liu
 *
 */
public class DataSourceRouterTest {

	private static DataSourceRouter router;

	@BeforeClass
	public static void setUp() {
		DataSourceRouterFactory routerFactory = new ClassPathXmlDataSourceRouterFactory("db-router-rule.xml");
		router = routerFactory.getRouter();
		router.setDataSourcePool(createDataSourcePool());
		router.init();
	}
	
	private static Map<String, Object> createDataSourcePool() {
		Map<String, Object> dsPool = new HashMap<String, Object>();
		dsPool.put("Group_00", createMockDataSource("Group_00", 1));
		dsPool.put("Group_01", createMockDataSource("Group_01", 1));
		dsPool.put("Group_02_R", createMockDataSource("Group_02_R", 3));
		dsPool.put("Group_02_W", createMockDataSource("Group_02_W", 1));
		dsPool.put("Group_03_R", createMockDataSource("Group_03", 6));
		dsPool.put("Group_03_W", createMockDataSource("Group_03", 1));
		dsPool.put("Group_07_R", createMockDataSource("Group_07_R", 2));
		dsPool.put("Group_07_W", createMockDataSource("Group_07_W", 1));
		return dsPool;
	}

	private static Object createMockDataSource(String identity, int i) {
		if (i == 1) {
			return new MockDataSource(identity);
		}
		List<DataSource> dataSources = new ArrayList<DataSource>();
		for (int j = 0; j < i; j++) {
			dataSources.add(new MockDataSource(identity + "$" + i));
		}
		return dataSources;
	}
	
	@Test
	public void testCase1() {
		String sql = "UPDATE DP_GroupFollowNote SET NoteClass = ? WHERE UserID = ?";
		RouterTarget target = router.getTarget(sql, Arrays.asList((Object) 1, 200));
		assertNotNull(target);
		List<TargetedSql> targetedSqls = target.getTargetedSqls();
		assertTrue(targetedSqls != null && !targetedSqls.isEmpty());
		assertTrue(!target.getNewParams().isEmpty());
	}
	
	@Test
	public void testCase2() {
		String sql = "SELECT N.GroupID, F.FollowNoteID, F.UserID, F.NoteId " +
				"FROM DP_GroupFollowNote F INNER JOIN DP_GroupNote N ON N.NoteID = F.NoteID " +
				"WHERE F.UserID = ? AND F.NoteClass <> 3";
		RouterTarget target = router.getTarget(sql, Arrays.asList((Object) 200));
		assertNotNull(target);
		List<TargetedSql> targetedSqls = target.getTargetedSqls();
		assertTrue(targetedSqls != null && !targetedSqls.isEmpty());
		assertTrue(!target.getNewParams().isEmpty());
	}
	
	@Test
	public void testCase3() {
		String sql = "SELECT * FROM DP_GroupFollowNote " +
				"WHERE (NoteClass = 1 OR (NoteClass = 4 AND UserID = ?)) " +
				"AND NoteID = ? LIMIT ?, ?";
		List<Object> params = new ArrayList<Object>();
		params.add(3);	//UserID
		params.add(5);	//NoteID
		params.add(3);	//Skip
		params.add(5);	//Max
		RouterTarget target = router.getTarget(sql, params);
		assertNotNull(target);
		List<TargetedSql> targetedSqls = target.getTargetedSqls();
		assertTrue(targetedSqls != null && !targetedSqls.isEmpty());
		assertTrue(!target.getNewParams().isEmpty());
	}
	
	@Test
	public void testCase4() {
		String sql = "SELECT COUNT(FollowNoteID) FROM DP_GroupFollowNote WHERE (NoteClass = 1 OR (NoteClass = 4 AND UserID = ?)) AND NoteID = ?";
		RouterTarget target = router.getTarget(sql, Arrays.asList((Object) 200, 300));
		assertNotNull(target);
		List<TargetedSql> targetedSqls = target.getTargetedSqls();
		assertTrue(targetedSqls != null && !targetedSqls.isEmpty());
		assertTrue(!target.getNewParams().isEmpty());
	}
	
	@Test
	public void testCase5() {
		String sql = "SELECT * FROM DP_GroupFollowNote " +
				"WHERE (NoteClass = 1 OR (NoteClass = 4 AND UserID = ?)) " +
				"AND NoteID = ? AND UserID = ? " +
				"LIMIT ?, ?";
		//match white list of NodeID's rule
		RouterTarget target = router.getTarget(sql, Arrays.asList((Object) 200, 100, 200, 3, 5));
		assertNotNull(target);
		List<TargetedSql> targetedSqls = target.getTargetedSqls();
		assertTrue(targetedSqls != null && !targetedSqls.isEmpty());
		assertTrue(!target.getNewParams().isEmpty());
	}
	
	@Test
	public void testCase6() {
		String sql = "SELECT COUNT(FollowNoteID) FROM DP_GroupFollowNote " +
				"WHERE (NoteClass = 1 OR (NoteClass = 4 AND UserID = ?)) AND NoteID = ? " +
				"AND UserID = ?";
		RouterTarget target = router.getTarget(sql, Arrays.asList((Object) 200, 1, 200, 3, 5));
		assertNotNull(target);
		List<TargetedSql> targetedSqls = target.getTargetedSqls();
		assertTrue(targetedSqls != null && !targetedSqls.isEmpty());
		assertTrue(!target.getNewParams().isEmpty());
	}
	
	@Test
	public void testCase7() {
		String sql = "INSERT INTO DP_GroupFollowNote (NoteID, UserID, NoteClass, ADDTIME, UpdateTime, LastIP, DCashNumber) " +
				"VALUES(?, ?, ?, ?, ?, ?, ?)";
		RouterTarget target = router.getTarget(sql, Arrays.asList((Object) 200, 100, 3, new Date(), new Date(), "10.1.1.22", "223344422"));
		assertNotNull(target);
		List<TargetedSql> targetedSqls = target.getTargetedSqls();
		assertTrue(targetedSqls != null && !targetedSqls.isEmpty());
		assertTrue(!target.getNewParams().isEmpty());
	}
	
	@Test
	public void testCase8() {
		String sql = "SELECT * FROM DP_GroupFollowNote WHERE FollowNoteID = ?";
		RouterTarget target = router.getTarget(sql, Arrays.asList((Object) 200));
		assertNotNull(target);
		List<TargetedSql> targetedSqls = target.getTargetedSqls();
		assertTrue(targetedSqls != null && !targetedSqls.isEmpty());
		assertTrue(!target.getNewParams().isEmpty());
	}
	
	@Test
	public void testCase9() {
		String sql = "SELECT COUNT(FollowNoteID) FROM DP_GroupFollowNote " +
				"WHERE (NoteClass = 1 OR (NoteClass = 4 AND UserID = ?)) AND NoteID = ? " +
				"AND FollowNoteID <= ?";
		RouterTarget target = router.getTarget(sql, Arrays.asList((Object) 200, 100, 20));
		assertNotNull(target);
		List<TargetedSql> targetedSqls = target.getTargetedSqls();
		assertTrue(targetedSqls != null && !targetedSqls.isEmpty());
		assertTrue(!target.getNewParams().isEmpty());
	}
	
	@Test
	public void testCase10() {
		String sql = "SELECT COUNT(DISTINCT(UserID)) FROM DP_GroupFollowNote WHERE NoteID = ?";
		RouterTarget target = router.getTarget(sql, Arrays.asList((Object) 200));
		assertNotNull(target);
		List<TargetedSql> targetedSqls = target.getTargetedSqls();
		assertTrue(targetedSqls != null && !targetedSqls.isEmpty());
		assertTrue(!target.getNewParams().isEmpty());
	}
	
	@Test
	public void testCase11() {
		String sql = "SELECT COUNT(FollowNoteID) FROM DP_GroupFollowNote WHERE NoteID = ? AND UserID = ?";
		RouterTarget target = router.getTarget(sql, Arrays.asList((Object) 200, 300));
		assertNotNull(target);
		List<TargetedSql> targetedSqls = target.getTargetedSqls();
		assertTrue(targetedSqls != null && !targetedSqls.isEmpty());
		assertTrue(!target.getNewParams().isEmpty());
	}
	
	@Test
	public void testCase12() {
		String sql = "SELECT * FROM DP_GroupFollowNote WHERE NoteID = ? AND NoteClass = 1 ORDER BY FollowNoteID DESC LIMIT 1";
		RouterTarget target = router.getTarget(sql, Arrays.asList((Object) 200));
		assertNotNull(target);
		List<TargetedSql> targetedSqls = target.getTargetedSqls();
		assertTrue(targetedSqls != null && !targetedSqls.isEmpty());
		assertTrue(!target.getNewParams().isEmpty());
	}
	
	@Test
	public void testCase13() {
		String sql = "SELECT COUNT(FollowNoteID) FROM DP_GroupFollowNote F INNER JOIN DP_GroupNote N ON F.NoteID = N.NoteID AND N.GroupID = ? AND N.Status = 1 " +
				"WHERE F.UserID = ? AND F.NoteClass = 1";
		RouterTarget target = router.getTarget(sql, Arrays.asList((Object) 200, 300));
		assertNotNull(target);
		List<TargetedSql> targetedSqls = target.getTargetedSqls();
		assertTrue(targetedSqls != null && !targetedSqls.isEmpty());
		assertTrue(!target.getNewParams().isEmpty());
	}
	
	@Test
	public void testCase14() {
		String sql = "UPDATE DP_GroupFollowNote SET DCashNumber = DCashNumber + ? WHERE FollowNoteID = ?";
		RouterTarget target = router.getTarget(sql, Arrays.asList((Object) 200, 300));
		assertNotNull(target);
		List<TargetedSql> targetedSqls = target.getTargetedSqls();
		assertTrue(targetedSqls != null && !targetedSqls.isEmpty());
		assertTrue(!target.getNewParams().isEmpty());
	}
	
	@Test
	public void testCase15() {
		String sql = "SELECT DISTINCT(GN.NoteID) FROM DP_GroupNote GN INNER JOIN DP_Group G ON GN.GroupID = G.GroupID AND G.Status = 0 " +
				"INNER JOIN DP_GroupFollowNote GFN ON GN.NoteID = GFN.NoteID " +
				"WHERE (GN.Status = 1 OR (GN.Status = 3 AND GN.UserID = ?)) AND GN.UserID <> ? " +
				"AND GFN.UserID = ? AND GNF.NoteClass = 1";
		RouterTarget target = router.getTarget(sql, Arrays.asList((Object) 200, 300, 400));
		assertNotNull(target);
		List<TargetedSql> targetedSqls = target.getTargetedSqls();
		assertTrue(targetedSqls != null && !targetedSqls.isEmpty());
		assertTrue(!target.getNewParams().isEmpty());
	}
	
	@Test
	public void testCase16() {
		String sql = "UPDATE DP_GroupFollowNote SET UpdateTime = Now(), LastIP = ? WHERE FollowNoteID = ?";
		RouterTarget target = router.getTarget(sql, Arrays.asList((Object) "10.1.1.22", 300));
		assertNotNull(target);
		List<TargetedSql> targetedSqls = target.getTargetedSqls();
		assertTrue(targetedSqls != null && !targetedSqls.isEmpty());
		assertTrue(!target.getNewParams().isEmpty());
	}
	
	@Test
	public void testCase17() {
		String sql = "UPDATE DP_GroupFollowNote SET NoteClass = ? WHERE  FollowNoteID = ?";
		RouterTarget target = router.getTarget(sql, Arrays.asList((Object) 3, 300));
		assertNotNull(target);
		List<TargetedSql> targetedSqls = target.getTargetedSqls();
		assertTrue(targetedSqls != null && !targetedSqls.isEmpty());
		assertTrue(!target.getNewParams().isEmpty());
	}
	
}
