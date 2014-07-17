package com.dianping.zebra.group.datasources;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.exception.IllegalConfigException;
import com.dianping.zebra.group.jdbc.AbstractDataSource;
import com.dianping.zebra.group.monitor.SingleDataSourceMBean;
import com.dianping.zebra.group.util.SmoothReload;
import com.dianping.zebra.group.util.StringUtils;

/**
 * @author Dozer <br/>
 *         1.实现了自动 reload 机制。 <br/>
 *         2.实现了帐号密码原子修改，只改帐号或者密码不会触发重新加载流程，只有两个同时修改后，才会一次性触发重新加载。<br/>
 *         3.并发策略：所有修改innerDs的地方都需要同步（set方法需要同步；init需要同步;refresh需要同步）。
 * 
 */
public class SingleDataSourceC3P0Adapter extends AbstractDataSource implements DataSource, SingleDataSourceMBean {
	private AtomicRefresh atomicRefresh = new AtomicRefresh();

	private DataSourceConfig config = new DataSourceConfig();

	private volatile SingleDataSource innerDs;

	public SingleDataSourceC3P0Adapter(String id) {
		// TODO: resolve from lion later
		config.setId(id);
		config.setActive(true);
		config.setCanRead(true);
		config.setCanWrite(true);

		registerMonitor();
	}

	public synchronized void close() {
		destoryInnerDs(innerDs);
	}

	private void destoryInnerDs(SingleDataSource dataSource) {
		Transaction t = Cat.newTransaction("DAL", "DataSource.Destory");
		try {
			Cat.logEvent("DAL.Destory.Start", dataSource.getId());
			SingleDataSourceManagerFactory.getDataSourceManager().destoryDataSource(dataSource);
			t.setStatus(Message.SUCCESS);
		} finally {
			// wait one second for dataSource to destroy
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
			}

			t.complete();
		}
	}

	@Override
	public DataSourceConfig getConfig() {
		return this.innerDs.getConfig();
	}

	@Override
	public Connection getConnection() throws SQLException {
		return getInnerDs().getConnection();
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return getInnerDs().getConnection(username, password);
	}

	@Override
	public String getCurrentState() {
		return this.innerDs.getCurrentState();
	}

	@Override
	public String getId() {
		return this.innerDs.getId();
	}

	private DataSource getInnerDs() throws SQLException {
		if (innerDs == null) {
			synchronized (this) {
				if (innerDs == null) {
					innerDs = initDataSource();
				}
			}
		}
		return innerDs;
	}

	@Override
	public int getNumBusyConnection() {
		return this.innerDs.getNumBusyConnection();
	}

	@Override
	public int getNumConnections() {
		return this.innerDs.getNumConnections();
	}

	@Override
	public long getNumFailedCheckins() {
		return this.innerDs.getNumFailedCheckins();
	}

	@Override
	public long getNumFailedCheckouts() {
		return this.innerDs.getNumFailedCheckouts();
	}

	@Override
	public int getNumIdleConnection() {
		return this.innerDs.getNumIdleConnection();
	}

	@Override
	public int getNumUnClosedOrphanedConnections() {
		return this.innerDs.getNumUnClosedOrphanedConnections();
	}

	@Override
	public DataSourceState getState() {
		return this.innerDs.getState();
	}

	@Override
	public int getThreadPoolNumActiveThreads() {
		return this.innerDs.getThreadPoolNumActiveThreads();
	}

	@Override
	public int getThreadPoolNumIdleThreads() {
		return this.innerDs.getThreadPoolNumIdleThreads();
	}

	@Override
	public int getThreadPoolNumTasksPending() {
		return this.innerDs.getThreadPoolNumTasksPending();
	}

	@Override
	public int getThreadPoolSize() {
		return this.innerDs.getThreadPoolSize();
	}

	private SingleDataSource initDataSource() throws SQLException {
		Transaction t = Cat.newTransaction("DAL", "DataSource.Init");

		try {
			SingleDataSource ds = SingleDataSourceManagerFactory.getDataSourceManager().createDataSource(config);
			t.setStatus(Message.SUCCESS);

			return ds;
		} catch (IllegalConfigException throwable) {
			Cat.logError(throwable);

			throw throwable;
		} finally {
			t.complete();
		}
	}

	private void refresh(String propertyToChange) {
		if (innerDs == null) {
			return;
		}

		Transaction t = Cat.newTransaction("DAL", "DataSource.Refresh");
		Cat.logEvent("DAL.Refresh.Property", propertyToChange);
		try {
			SingleDataSource tempDs = innerDs;

			innerDs = initDataSource();

			destoryInnerDs(tempDs);
			t.setStatus(Message.SUCCESS);
		} catch (Exception e) {
			Cat.logError(e);
			t.setStatus(e);
		} finally {
			t.complete();
		}
	}

	private void refreshUserAndPassword() {
		config.setUser(atomicRefresh.getNewUser());
		config.setPassword(atomicRefresh.getNewPassword());
		atomicRefresh.reset();

		// TODO:平滑切换
		SmoothReload sr = new SmoothReload();
		sr.waitForReload();

		refresh("user&password");
	}

	private void registerMonitor() {
		try {
			// compatible to Cat <= 1.0.4
			Class<?> registerClass = Class.forName("com.dianping.cat.status.StatusExtensionRegister");
			Class<?> extensionInterfaceClass = Class.forName("com.dianping.cat.status.StatusExtension");
			Class<?> extensionClass = Class.forName("com.dianping.zebra.group.monitor.SingleDataSourceStatusExtension");
			Method getInstanceMethod = registerClass.getMethod("getInstance");
			Method registerMethod = registerClass.getMethod("register", extensionInterfaceClass);
			Constructor<?> constructor = extensionClass.getConstructor(SingleDataSourceMBean.class);

			registerMethod.invoke(getInstanceMethod.invoke(registerClass), constructor.newInstance(this));
		} catch (Throwable e) {
			Cat.logError(e);
		}
	}

	public synchronized void setAcquireIncrement(int acquireIncrement) {
		setProperty("acquireIncrement", String.valueOf(acquireIncrement));
	}

	public synchronized void setAcquireRetryAttempts(int acquireRetryAttempts) {
		setProperty("acquireRetryAttempts", String.valueOf(acquireRetryAttempts));
	}

	public synchronized void setAcquireRetryDelay(int acquireRetryDelay) {
		setProperty("acquireRetryDelay", String.valueOf(acquireRetryDelay));
	}

	public synchronized void setAutoCommitOnClose(boolean autoCommitOnClose) {
		setProperty("autoCommitOnClose", String.valueOf(autoCommitOnClose));
	}

	public synchronized void setAutomaticTestTable(String automaticTestTable) {
		setProperty("automaticTestTable", automaticTestTable);
	}

	public synchronized void setBreakAfterAcquireFailure(boolean breakAfterAcquireFailure) {
		setProperty("breakAfterAcquireFailure", String.valueOf(breakAfterAcquireFailure));
	}

	public synchronized void setCheckoutTimeout(int checkoutTimeout) {
		config.setCheckoutTimeout(checkoutTimeout);
		refresh("checkoutTimeout");
	}

	public synchronized void setConnectionCustomizerClassName(String connectionCustomizerClassName) {
		setProperty("connectionCustomizerClassName", connectionCustomizerClassName);
	}

	public synchronized void setConnectionTesterClassName(String connectionTesterClassName) {
		setProperty("connectionTesterClassName", connectionTesterClassName);
	}

	public synchronized void setDataSourceName(String dataSourceName) {
		setProperty("dataSourceName", dataSourceName);
	}

	public synchronized void setDebugUnreturnedConnectionStackTraces(boolean debugUnreturnedConnectionStackTraces) {
		setProperty("debugUnreturnedConnectionStackTraces", String.valueOf(debugUnreturnedConnectionStackTraces));
	}

	public synchronized void setDescription(String description) {
		setProperty("description", description);
	}

	public synchronized void setDriverClass(String driverClass) {
		config.setDriverClass(driverClass);
		refresh("driverClass");
	}

	public synchronized void setFactoryClassLocation(String factoryClassLocation) {
		setProperty("factoryClassLocation", factoryClassLocation);
	}

	public synchronized void setForceIgnoreUnresolvedTransactions(boolean forceIgnoreUnresolvedTransactions) {
		setProperty("forceIgnoreUnresolvedTransactions", String.valueOf(forceIgnoreUnresolvedTransactions));
	}

	public synchronized void setIdleConnectionTestPeriod(int idleConnectionTestPeriod) {
		setProperty("idleConnectionTestPeriod", String.valueOf(idleConnectionTestPeriod));
	}

	public synchronized void setInitialPoolSize(int initialPoolSize) {
		config.setInitialPoolSize(initialPoolSize);
		refresh("initialPoolSize");
	}

	public synchronized void setJdbcUrl(String jdbcUrl) {
		config.setJdbcUrl(jdbcUrl);
		refresh("jdbcUrl");
	}

	public synchronized void setMaxAdministrativeTaskTime(int maxAdministrativeTaskTime) {
		setProperty("maxAdministrativeTaskTime", String.valueOf(maxAdministrativeTaskTime));
	}

	public synchronized void setMaxConnectionAge(int maxConnectionAge) {
		setProperty("maxConnectionAge", String.valueOf(maxConnectionAge));
	}

	public synchronized void setMaxIdleTime(int maxIdleTime) {
		setProperty("maxIdleTime", String.valueOf(maxIdleTime));
	}

	public synchronized void setMaxIdleTimeExcessConnections(int maxIdleTimeExcessConnections) {
		setProperty("maxIdleTimeExcessConnections", String.valueOf(maxIdleTimeExcessConnections));
	}

	public synchronized void setMaxPoolSize(int maxPoolSize) {
		config.setMaxPoolSize(maxPoolSize);
		refresh("maxPoolSize");
	}

	public synchronized void setMaxStatements(int maxStatements) {
		setProperty("maxStatements", String.valueOf(maxStatements));
	}

	public synchronized void setMaxStatementsPerConnection(int maxStatementsPerConnection) {
		setProperty("maxStatementsPerConnection", String.valueOf(maxStatementsPerConnection));
	}

	public synchronized void setMinPoolSize(int minPoolSize) {
		config.setMinPoolSize(minPoolSize);
		refresh("minPoolSize");
	}

	public synchronized void setNumHelperThreads(int numHelperThreads) {
		setProperty("numHelperThreads", String.valueOf(numHelperThreads));
	}

	public synchronized void setOverrideDefaultPassword(String overrideDefaultPassword) {
		setProperty("overrideDefaultPassword", overrideDefaultPassword);
	}

	public synchronized void setOverrideDefaultUser(String overrideDefaultUser) {
		setProperty("overrideDefaultUser", overrideDefaultUser);
	}

	public synchronized void setPassword(String password) {
		// innerDs 未加载的时候不需要 reload 逻辑
		if (innerDs == null) {
			config.setPassword(password);
			return;
		}

		atomicRefresh.setPassword(password);
		if (atomicRefresh.needToRefresh()) {
			refreshUserAndPassword();
		}
	}

	public synchronized void setPreferredTestQuery(String preferredTestQuery) {
		setProperty("preferredTestQuery", preferredTestQuery);
	}

	public synchronized void setProperties(Properties properties) {
		for (Entry<Object, Object> item : properties.entrySet()) {
			setProperty(String.valueOf(item.getKey().toString()), String.valueOf(item.getValue()));
		}
	}

	private void setProperty(String name, String value) {
		Any any = null;
		for (Any a : config.getProperties()) {
			if (a.getName().equals(name)) {
				any = a;
				break;
			}
		}

		if (any == null) {
			any = new Any();
			config.getProperties().add(any);
		}

		any.setName(name);
		any.setValue(value);

		refresh(name);
	}

	public synchronized void setPropertyCycle(int propertyCycle) {
		setProperty("propertyCycle", String.valueOf(propertyCycle));
	}

	public synchronized void setTestConnectionOnCheckin(boolean testConnectionOnCheckin) {
		setProperty("testConnectionOnCheckin", String.valueOf(testConnectionOnCheckin));
	}

	public synchronized void setTestConnectionOnCheckout(boolean testConnectionOnCheckout) {
		setProperty("testConnectionOnCheckout", String.valueOf(testConnectionOnCheckout));
	}

	public synchronized void setUnreturnedConnectionTimeout(int unreturnedConnectionTimeout) {
		setProperty("unreturnedConnectionTimeout", String.valueOf(unreturnedConnectionTimeout));
	}

	public synchronized void setUser(String user) {
		// innerDs 未加载的时候不需要 reload 逻辑
		if (innerDs == null) {
			config.setUser(user);
			return;
		}

		atomicRefresh.setUser(user);
		if (atomicRefresh.needToRefresh()) {
			refreshUserAndPassword();
		}
	}

	public synchronized void setUserOverridesAsString(String userOverridesAsString) {
		setProperty("userOverridesAsString", userOverridesAsString);
	}

	public synchronized void setUsesTraditionalReflectiveProxies(boolean usesTraditionalReflectiveProxies) {
		setProperty("usesTraditionalReflectiveProxies", String.valueOf(usesTraditionalReflectiveProxies));
	}

	static class AtomicRefresh {
		private String newPassword;

		private String newUser;

		private String oldPassword;

		private String oldUser;

		public synchronized String getNewPassword() {
			return newPassword;
		}

		public synchronized String getNewUser() {
			return newUser;
		}

		public synchronized boolean needToRefresh() {
			// 帐号和密码都改了，就需要 refresh
			return (!StringUtils.equals(newUser, oldUser)) && (!StringUtils.equals(newPassword, oldPassword));
		}

		public synchronized void reset() {
			oldPassword = newPassword;
			oldUser = newUser;
		}

		public synchronized void setPassword(String password) {
			this.newPassword = password;
		}

		public synchronized void setUser(String user) {
			this.newUser = user;
		}
	}
}