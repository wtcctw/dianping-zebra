package com.dianping.zebra.group.filter;

import com.dianping.zebra.group.util.StringUtils;
import com.foundationdb.sql.StandardException;
import com.foundationdb.sql.parser.SQLParser;
import com.foundationdb.sql.parser.StatementNode;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Dozer on 9/2/14.
 * Filters read metadata from this class
 */
public class JdbcMetaData implements Cloneable {

	private List<StatementNode> batchedNode;

	private List<String> batchedSqls;

	private Connection connection;

	private DataSource dataSource;

	private String dataSourceId;

	private boolean isBatch;

	private boolean isTransaction;

	private String jdbcPassword;

	private String jdbcUrl;

	private String jdbcUsername;

	private StatementNode node;

	private Object params;

	private Properties properties;

	private JdbcMetaData realJdbcMetaData;

	private String sql;

	public JdbcMetaData clone() {
		try {
			return (JdbcMetaData) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	public List<StatementNode> getBatchedNode() {
		return batchedNode;
	}

	public void setBatchedNode(List<StatementNode> batchedNode) {
		this.batchedNode = batchedNode;
	}

	public List<String> getBatchedSqls() {
		return batchedSqls;
	}

	public void setBatchedSqls(List<String> batchedSqls) {
		this.batchedSqls = batchedSqls;
		if (batchedSqls != null) {
			this.batchedNode = new ArrayList<StatementNode>();
			StringBuffer sb = new StringBuffer();
			for (String sql : batchedSqls) {
				sb.append(sql);
				if (!sql.endsWith(";")) {
					sb.append(";");
				}
			}

			try {
				this.sql = sb.toString();
				this.batchedNode = new SQLParser().parseStatements(sb.toString());
			} catch (StandardException e) {
			}
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public String getJdbcPassword() {
		return jdbcPassword;
	}

	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getJdbcUsername() {
		return jdbcUsername;
	}

	public void setJdbcUsername(String jdbcUsername) {
		this.jdbcUsername = jdbcUsername;
	}

	public StatementNode getNode() {
		return node;
	}

	public void setNode(StatementNode node) {
		this.node = node;
	}

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}

	public Properties getProperties() {
		if (properties == null) {
			properties = new Properties();
		}
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public JdbcMetaData getRealJdbcMetaData() {
		return realJdbcMetaData;
	}

	public void setRealJdbcMetaData(JdbcMetaData realJdbcMetaData) {
		this.realJdbcMetaData = realJdbcMetaData;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;

		if (StringUtils.isBlank(sql)) {
			return;
		}

		try {
			node = new SQLParser().parseStatement(sql);
		} catch (StandardException e) {
			e.printStackTrace();
		}
	}

	public boolean isBatch() {
		return isBatch;
	}

	public void setBatch(boolean isBatch) {
		this.isBatch = isBatch;
	}

	public boolean isTransaction() {
		return isTransaction;
	}

	public void setTransaction(boolean isTransaction) {
		this.isTransaction = isTransaction;
	}

	@Override public String toString() {
		return "JdbcMetaData{" +
				"batchedSqls=" + batchedSqls +
				", connection=" + connection +
				", dataSource=" + dataSource +
				", dataSourceId='" + dataSourceId + '\'' +
				", jdbcPassword='" + jdbcPassword + '\'' +
				", jdbcUrl='" + jdbcUrl + '\'' +
				", jdbcUsername='" + jdbcUsername + '\'' +
				", params=" + params +
				", properties=" + properties +
				", sql='" + sql + '\'' +
				'}';
	}
}
