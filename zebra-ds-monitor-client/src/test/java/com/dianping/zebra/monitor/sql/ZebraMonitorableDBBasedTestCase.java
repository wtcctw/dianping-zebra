/**
 * Project: zebra-client
 * 
 * File Created at 2011-6-29
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
package com.dianping.zebra.monitor.sql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.statement.IBatchStatement;
import org.dbunit.database.statement.IStatementFactory;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.CompositeOperation;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 
 * @author Leo Liang
 * @author danson.liu
 * 
 */
public abstract class ZebraMonitorableDBBasedTestCase extends DataSourceBasedDBTestCase {

	private List<CreateResourceScriptEntry>	createdResourceList	= new ArrayList<CreateResourceScriptEntry>();
	protected ApplicationContext			context;

	protected abstract String getDataSetFilePath();

	protected abstract String getCreateTableScriptPath();

	protected String[] getSpringConfigLocations() {
		return new String[0];
	}

	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(ZebraMonitorableDBBasedTestCase.class.getClassLoader().getResourceAsStream(
				getDataSetFilePath()));
	}

	protected DatabaseOperation getSetUpOperation() throws Exception {
		parseCreateTableScriptFile();

		initSpringContext();

		return new CompositeOperation(new DatabaseOperation[] { new DatabaseOperation() {

			@Override
			public void execute(IDatabaseConnection connection, IDataSet dataSet) throws DatabaseUnitException,
					SQLException {

				DatabaseConfig databaseConfig = connection.getConfig();
				IStatementFactory statementFactory = (IStatementFactory) databaseConfig
						.getProperty(DatabaseConfig.PROPERTY_STATEMENT_FACTORY);
				IBatchStatement statement = statementFactory.createBatchStatement(connection);
				try {
					int count = 0;
					for (CreateResourceScriptEntry entry : createdResourceList) {
						statement.addBatch(entry.getScript());
						count++;
					}

					if (count > 0) {
						statement.executeBatch();
						statement.clearBatch();
					}
				} finally {
					statement.close();
				}
			}
		}, DatabaseOperation.CLEAN_INSERT });
	}

	protected DatabaseOperation getTearDownOperation() throws Exception {
		return new DatabaseOperation() {

			@Override
			public void execute(IDatabaseConnection connection, IDataSet dataSet) throws DatabaseUnitException,
					SQLException {

				DatabaseConfig databaseConfig = connection.getConfig();
				IStatementFactory statementFactory = (IStatementFactory) databaseConfig
						.getProperty(DatabaseConfig.PROPERTY_STATEMENT_FACTORY);
				IBatchStatement statement = statementFactory.createBatchStatement(connection);

				try {
					int count = 0;
					for (CreateResourceScriptEntry entry : createdResourceList) {
						statement.addBatch("drop " + entry.getType() + " " + entry.getName());
						count++;
					}

					if (count > 0) {
						statement.executeBatch();
						statement.clearBatch();
					}
				} finally {
					statement.close();
				}

			}
		};
	}

	protected void initSpringContext() throws Exception {
		String[] configLocations = getSpringConfigLocations();
		if (context == null && configLocations != null && configLocations.length != 0) {
			context = new ClassPathXmlApplicationContext(configLocations);
		}
	}

	protected void parseCreateTableScriptFile() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document configDoc = builder.parse(ZebraMonitorableDBBasedTestCase.class.getClassLoader().getResourceAsStream(
				getCreateTableScriptPath()));
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		NodeList tableScriptList = (NodeList) xpath.compile("/database/tables/table")
				.evaluate(configDoc, XPathConstants.NODESET);
		for (int i = 0; i < tableScriptList.getLength(); i++) {
			CreateResourceScriptEntry entry = new CreateResourceScriptEntry();
			Element ele = (Element) tableScriptList.item(i);
			entry.setType("table");
			entry.setName(ele.getAttribute("name"));
			entry.setScript(ele.getTextContent());
			createdResourceList.add(entry);
		}
		NodeList aliasScriptList = (NodeList) xpath.compile("/database/aliases/alias")
				.evaluate(configDoc, XPathConstants.NODESET);
		for (int i = 0; i < aliasScriptList.getLength(); i++) {
			CreateResourceScriptEntry entry = new CreateResourceScriptEntry();
			Element ele = (Element) aliasScriptList.item(i);
			entry.setType("alias");
			entry.setName(ele.getAttribute("name"));
			entry.setScript(ele.getTextContent());
			createdResourceList.add(entry);
		}
	}

	private static class CreateResourceScriptEntry {
		private String	type;
		private String	name;
		private String	script;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		/**
		 * @return the tableName
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the tableName to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the createTableScript
		 */
		public String getScript() {
			return script;
		}

		/**
		 * @param createTableScript
		 *            the createTableScript to set
		 */
		public void setScript(String createTableScript) {
			this.script = createTableScript;
		}

	}
}
