package com.dianping.zebra.monitor.other;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:config/spring/appcontext-test.xml")
public class SingleDataSourceC3P0AdapterSpringTest {

	// @Resource(name = "dsTest")
	// private DataSource dsTest;

	@Test
	@Ignore
	public void test_jdbc() throws SQLException, PropertyVetoException, InterruptedException {
		// startThread();
		// startThread();
		// startThread();
		// startThread();
		// startThread();
		// startThread();
		// startThread();
		// startThread();
		// startThread();
		// startThread();
		// startThread();
		// startThread();

		// Thread.sleep(5000);
		//
		// dsTest.setUser("test1");
		// dsTest.setPassword("123456");
		//
		// Thread.sleep(5000);
		// dsTest.setMaxPoolSize(5);
		// Thread.sleep(5000);
		//
		// dsTest.setDriverClass("asdsadasdasda.asdadas");

		// Thread.sleep(1000000);
	}

	// private void startThread() throws InterruptedException {
	// new Thread(new Runnable() {
	// @Override
	// public void run() {
	// try {
	// while (true) {
	// query();
	// Thread.yield();
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// }).start();
	// }
	//
	// private void query() throws SQLException {
	// Connection conn = dsTest.getConnection();
	// Statement stm = conn.createStatement();
	// ResultSet result = stm.executeQuery("select * from person");
	// Assert.assertTrue(result.next());
	// Assert.assertEquals("1", result.getString(1));
	// result.close();
	// stm.close();
	// conn.close();
	// }
}
