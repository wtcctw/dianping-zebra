package com.dianping.zebra.admin.build;

import java.util.ArrayList;
import java.util.List;

import org.unidal.dal.jdbc.configuration.AbstractJdbcResourceConfigurator;
import org.unidal.lookup.configuration.Component;

final class ZebraDatabaseConfigurator extends AbstractJdbcResourceConfigurator {
   @Override
   public List<Component> defineComponents() {
      List<Component> all = new ArrayList<Component>();

      all.add(defineJdbcDataSourceComponent("zebra", "com.mysql.jdbc.Driver", "jdbc:mysql://192.168.7.105:3306/zebra", "zebra", "dp!@mDhX20RM5", "<![CDATA[useUnicode=true&characterEncoding=UTF-8&autoReconnect=true]]>"));

      defineSimpleTableProviderComponents(all, "zebra", com.dianping.zebra.web.dal.stat._INDEX.getEntityClasses());
      defineDaoComponents(all, com.dianping.zebra.web.dal.stat._INDEX.getDaoClasses());

      return all;
   }
}
