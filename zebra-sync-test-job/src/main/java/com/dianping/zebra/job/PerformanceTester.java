package com.dianping.zebra.job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.time.StopWatch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dianping.zebra.shard.jdbc.ShardDataSource;

public class PerformanceTester {

	private static int INSERT_NUM = 2000;

	private static String INSERT = "INSERT INTO `UOD_Order` (`OrderID`, `UserID`, `MobileNO`, `CountryCode`, `DPID`, `BizType`, `CityID`, `SpugID`, `TotalAmount`, `TotalQuantity`, `Status`, `SubStatus`, `Platform`, `Source`, `Mode`, `ShopID`, `PartnerID`, `DeviceType`, `AddTime`, `UpdateTime`, `BuySuccessTime`, `TradeFinishedTime`, `ExpiredTime`, `HasPayment`, `HasRefund`, `HasCertification`, `HasConsumption`, `HasPromotion`, `DisplayMode`) VALUES(?,?,'123456',NULL,NULL,1,1,'24312d',100.00,3,0,0,1,1234,12,?,NULL,1245,NOW(),NOW(),NULL,NULL,NULL,0,0,0,0,0,0)";

	private ShardDataSource datasource;

	private int max = 100;

	private int min = 100;

	private int queue = 5000;

	private int sleepTime = 100;

	private AtomicInteger count = new AtomicInteger(0);

	private AtomicInteger activeThread = new AtomicInteger(0);

	private StopWatch stopWatch = new StopWatch();

	private String seed;
	
	private ExecutorService es = new ThreadPoolExecutor(min, max, 0L, TimeUnit.MILLISECONDS,
	      new LinkedBlockingQueue<Runnable>(queue));

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:config/spring/local/appcontext-aop.xml",
		      "classpath:config/spring/local/appcontext-db-unifiedorder.xml");

		PerformanceTester bean = ctx.getBean(PerformanceTester.class);

		bean.insertData();
		// bean.testInsert3();
	}

	public ShardDataSource getDatasource() {
		return datasource;
	}

	public void insertData() {
		long now = System.currentTimeMillis();
		
		this.seed = String.valueOf(now).substring(0, 6);
		
		stopWatch.start();

		while (true) {
			for (int i = 0; i < INSERT_NUM; i++) {
				try {
					es.submit(new Callable<Object>() {

						@Override
						public Object call() throws Exception {

							activeThread.getAndIncrement();

							int userId = ThreadLocalRandom.current().nextInt(1000) + 1000;

							int orderPart = ThreadLocalRandom.current().nextInt(1000000) + 1000000;
							String orderId = String.format("%s35322%s%s", orderPart, userId, seed);
							int shopId = ThreadLocalRandom.current().nextInt(10000);

//							System.out.println(orderId);

							Connection conn = null;
							PreparedStatement stmt = null;
							try {
								conn = datasource.getConnection();
								stmt = conn.prepareStatement(INSERT);

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

							if (finished % 10000 == 0) {

								long now = stopWatch.getTime();

								System.out.println(String.format("use shard: count:{%s} time: {%s}, QPS: {%s} Active:{%s}",
								      finished, now, finished * 1.0 / now * 1000, activeThread.get()));
							}
							activeThread.getAndDecrement();

							return null;
						}
					});
				} catch (RejectedExecutionException e) {
				}
			}
			try {
				TimeUnit.MILLISECONDS.sleep(sleepTime);
			} catch (InterruptedException e) {
			}
		}
	}

	public void setDatasource(ShardDataSource datasource) {
		this.datasource = datasource;
	}

	public void testInsert3() throws Exception {
		Connection conn = datasource.getConnection();
		String sql = "INSERT INTO `UOD_Order` (`OrderID`, `UserID`, `MobileNO`, `CountryCode`, `DPID`, `BizType`, `CityID`, `SpugID`, `TotalAmount`, `TotalQuantity`, `Status`, `SubStatus`, `Platform`, `Source`, `Mode`, `ShopID`, `PartnerID`, `DeviceType`, `AddTime`, `UpdateTime`, `BuySuccessTime`, `TradeFinishedTime`, `ExpiredTime`, `HasPayment`, `HasRefund`, `HasCertification`, `HasConsumption`, `HasPromotion`, `DisplayMode`) VALUES(?,?,'123456',NULL,NULL,1,1,'24312d',100.00,3,0,0,1,1234,12,1,NULL,1245,NOW(),NOW(),NULL,NULL,NULL,0,0,0,0,0,0)";

		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, "1433768353223123801617");
		stmt.setInt(2, 321123);

		stmt.executeUpdate();

		conn.close();
	}
}