package com.dianping.zebra.admin.build;

import java.util.ArrayList;
import java.util.List;

import org.unidal.dal.jdbc.configuration.AbstractJdbcResourceConfigurator;
import org.unidal.dal.jdbc.datasource.DataSource;
import org.unidal.dal.jdbc.datasource.JdbcDataSourceConfigurationManager;
import org.unidal.lookup.configuration.Component;

import com.dianping.zebra.admin.datasource.JdbcDataSource;

final class ZebraDatabaseConfigurator extends AbstractJdbcResourceConfigurator {
   @Override
   public List<Component> defineComponents() {
      List<Component> all = new ArrayList<Component>();

      all.add(C(DataSource.class, "zebra", JdbcDataSource.class).req(JdbcDataSourceConfigurationManager.class).config(
		      E("id").value("zebra"),
		      E("maximum-pool-size").value("3"),
		      E("connection-timeout").value("1s"),
		      E("idle-timeout").value("10m"),
		      E("statement-cache-size").value("1000"),
		      E("properties").add(
		            E("driver").value("com.mysql.jdbc.Driver"),
		            E("URL").value("${zebra.server.jdbc.url}"),
		            E("user").value("${zebra.server.jdbc.username}"),
		            E("password").value("${zebra.server.jdbc.password}"),
		            E("connectionProperties").value(
		                  "<![CDATA[useUnicode=true&characterEncoding=UTF-8&autoReconnect=true]]>"))));
      
      defineSimpleTableProviderComponents(all, "zebra", com.dianping.zebra.web.dal.stat._INDEX.getEntityClasses());
      defineDaoComponents(all, com.dianping.zebra.web.dal.stat._INDEX.getDaoClasses());

      return all;
   }
}
