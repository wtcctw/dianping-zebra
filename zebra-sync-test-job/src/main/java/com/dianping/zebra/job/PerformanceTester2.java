package com.dianping.zebra.job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import com.dianping.zebra.group.jdbc.GroupDataSource;

public class PerformanceTester2 {

	private static int INSERT_NUM = 2000;

	private static String INSERT00 = "INSERT INTO `UOD_Order0` (`OrderID`, `UserID`, `MobileNO`, `CountryCode`, `DPID`, `BizType`, `CityID`, `SpugID`, `TotalAmount`, `TotalQuantity`, `Status`, `SubStatus`, `Platform`, `Source`, `Mode`, `ShopID`, `PartnerID`, `DeviceType`, `AddTime`, `UpdateTime`, `BuySuccessTime`, `TradeFinishedTime`, `ExpiredTime`, `HasPayment`, `HasRefund`, `HasCertification`, `HasConsumption`, `HasPromotion`, `DisplayMode`) VALUES(?,?,'123456',NULL,NULL,1,1,'24312d',100.00,3,0,0,1,1234,12,?,NULL,1245,NOW(),NOW(),NULL,NULL,NULL,0,0,0,0,0,0)";

	private static String INSERT01 = "INSERT INTO `UOD_Order1` (`OrderID`, `UserID`, `MobileNO`, `CountryCode`, `DPID`, `BizType`, `CityID`, `SpugID`, `TotalAmount`, `TotalQuantity`, `Status`, `SubStatus`, `Platform`, `Source`, `Mode`, `ShopID`, `PartnerID`, `DeviceType`, `AddTime`, `UpdateTime`, `BuySuccessTime`, `TradeFinishedTime`, `ExpiredTime`, `HasPayment`, `HasRefund`, `HasCertification`, `HasConsumption`, `HasPromotion`, `DisplayMode`) VALUES(?,?,'123456',NULL,NULL,1,1,'24312d',100.00,3,0,0,1,1234,12,?,NULL,1245,NOW(),NOW(),NULL,NULL,NULL,0,0,0,0,0,0)";

	private static String INSERT12 = "INSERT INTO `UOD_Order2` (`OrderID`, `UserID`, `MobileNO`, `CountryCode`, `DPID`, `BizType`, `CityID`, `SpugID`, `TotalAmount`, `TotalQuantity`, `Status`, `SubStatus`, `Platform`, `Source`, `Mode`, `ShopID`, `PartnerID`, `DeviceType`, `AddTime`, `UpdateTime`, `BuySuccessTime`, `TradeFinishedTime`, `ExpiredTime`, `HasPayment`, `HasRefund`, `HasCertification`, `HasConsumption`, `HasPromotion`, `DisplayMode`) VALUES(?,?,'123456',NULL,NULL,1,1,'24312d',100.00,3,0,0,1,1234,12,?,NULL,1245,NOW(),NOW(),NULL,NULL,NULL,0,0,0,0,0,0)";

	private static String INSERT13 = "INSERT INTO `UOD_Order3` (`OrderID`, `UserID`, `MobileNO`, `CountryCode`, `DPID`, `BizType`, `CityID`, `SpugID`, `TotalAmount`, `TotalQuantity`, `Status`, `SubStatus`, `Platform`, `Source`, `Mode`, `ShopID`, `PartnerID`, `DeviceType`, `AddTime`, `UpdateTime`, `BuySuccessTime`, `TradeFinishedTime`, `ExpiredTime`, `HasPayment`, `HasRefund`, `HasCertification`, `HasConsumption`, `HasPromotion`, `DisplayMode`) VALUES(?,?,'123456',NULL,NULL,1,1,'24312d',100.00,3,0,0,1,1234,12,?,NULL,1245,NOW(),NOW(),NULL,NULL,NULL,0,0,0,0,0,0)";

	private GroupDataSource ds1;

	private GroupDataSource ds2;

	private int max = 100;

	private int min = 100;

	private int queue = 20000;

	private int sleepTime = 100;

	private AtomicInteger count = new AtomicInteger(0);

	private AtomicInteger activeThread = new AtomicInteger(0);

	private String seed;

	private ExecutorService es = new ThreadPoolExecutor(min, max, 0L, TimeUnit.MILLISECONDS,
	      new LinkedBlockingQueue<Runnable>(queue));

	public static void main(String[] args) throws Exception {
		PerformanceTester2 bean = new PerformanceTester2();
		bean.init();
		
		bean.insertData();
	}

	public void init() {
		ds1 = new GroupDataSource("unifiedorder0");
		ds1.setFilter("!cat");
		ds1.setPoolType("tomcat-jdbc");
		ds1.init();

		ds2 = new GroupDataSource("unifiedorder1");
		ds2.setFilter("!cat");
		ds2.setPoolType("tomcat-jdbc");
		ds2.init();
	}
	
	public void insertSingleData(){
		String orderId = String.format("%s%s%s%s", "123123123","123123", "12123", seed);
		int shopId = 123123;

		int dbIndex = 12313 % 1000 % 2;
		int tbIndex = ((12313 % 1000) / 2) % 2;
		DataSource datasource = null;
		String sql = null;
		if (dbIndex == 0) {
			datasource = ds1;
			if (tbIndex == 0) {
				sql = INSERT00;
			} else {
				sql = INSERT01;
			}
		} else {
			datasource = ds2;
			if (tbIndex == 0) {
				sql = INSERT12;
			} else {
				sql = INSERT13;
			}
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = datasource.getConnection();
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, orderId);
			stmt.setInt(2, 12313);
			stmt.setInt(3, shopId);

			int executeUpdate = stmt.executeUpdate();
			
			System.out.println("Execute Update : " + executeUpdate);
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
				}
			}
		}
	}

	public void insertData() {
		long now = System.currentTimeMillis();

		System.out.println("------" + now);
		this.seed = String.valueOf(now).substring(8, 13);

		System.out.println("------" + seed);

		final long start = System.currentTimeMillis();
		final Random random1 = new Random(1);
		final Random random2 = new Random(2);
		
		while (true) {
			for (int i = 0; i < INSERT_NUM; i++) {
				try {
					es.submit(new Callable<Object>() {

						@Override
						public Object call() throws Exception {

							activeThread.getAndIncrement();

							int userId = random1.nextInt(1000) + 1000;

							int orderPart1 = random1.nextInt(1000000) + 1000000;
							int orderPart2 = random2.nextInt(10000) + 10000;
							
							String orderId = String.format("%s%s%s%s", orderPart1,orderPart2, userId, seed);
							int shopId = random1.nextInt(10000);

							int dbIndex = userId % 1000 % 2;
							int tbIndex = ((userId % 1000) / 2) % 2;
							DataSource datasource = null;
							String sql = null;
							if (dbIndex == 0) {
								datasource = ds1;
								if (tbIndex == 0) {
									sql = INSERT00;
								} else {
									sql = INSERT01;
								}
							} else {
								datasource = ds2;
								if (tbIndex == 0) {
									sql = INSERT12;
								} else {
									sql = INSERT13;
								}
							}

							Connection conn = null;
							PreparedStatement stmt = null;
							try {
								conn = datasource.getConnection();
								stmt = conn.prepareStatement(sql);

								stmt.setString(1, orderId);
								stmt.setInt(2, userId);
								stmt.setInt(3, shopId);

								stmt.executeUpdate();
							} catch (SQLException e) {
								System.err.println(e);
							} finally {
								if (stmt != null) {
									try {
										stmt.close();
									} catch (SQLException e1) {
									}
								}

								if (conn != null) {
									try {
										conn.close();
									} catch (SQLException e1) {
									}
								}
							}

							int finished = count.getAndAdd(1);

							if (finished % 1000 == 0) {

								long time = System.currentTimeMillis() - start;

								System.out.println(String.format("use shard: count:{%s} time: {%s}, QPS: {%s} Active:{%s}",
								      finished, time, finished * 1.0 / time * 1000, activeThread.get()));
							}
							activeThread.getAndDecrement();

							return null;
						}
					});
				} catch (RejectedExecutionException e) {
					e.printStackTrace();
				}
			}
			try {
				TimeUnit.MILLISECONDS.sleep(sleepTime);
			} catch (InterruptedException e) {
			}
		}
	}
}