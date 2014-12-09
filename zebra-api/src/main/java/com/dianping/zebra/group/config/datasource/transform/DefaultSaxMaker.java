package com.dianping.zebra.group.config.datasource.transform;

import static com.dianping.zebra.group.config.datasource.Constants.ATTR_ACTIVE;
import static com.dianping.zebra.group.config.datasource.Constants.ATTR_CANREAD;
import static com.dianping.zebra.group.config.datasource.Constants.ATTR_CANWRITE;
import static com.dianping.zebra.group.config.datasource.Constants.ATTR_FILTERS;
import static com.dianping.zebra.group.config.datasource.Constants.ATTR_ID;
import static com.dianping.zebra.group.config.datasource.Constants.ATTR_ROUTER_STRATEGY;
import static com.dianping.zebra.group.config.datasource.Constants.ATTR_TRANSACTION_FORCE_WRITE;
import static com.dianping.zebra.group.config.datasource.Constants.ATTR_WEIGHT;
import static com.dianping.zebra.group.config.datasource.Constants.ATTR_WRITE_FIRST;

import org.xml.sax.Attributes;

import com.dianping.zebra.group.config.datasource.entity.Any;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;

public class DefaultSaxMaker implements IMaker<Attributes> {

   @Override
   public Any buildAny(Attributes attributes) {
      throw new UnsupportedOperationException("Not needed!");
   }

   @Override
   public DataSourceConfig buildDataSourceConfig(Attributes attributes) {
      String id = attributes.getValue(ATTR_ID);
      String weight = attributes.getValue(ATTR_WEIGHT);
      String canRead = attributes.getValue(ATTR_CANREAD);
      String canWrite = attributes.getValue(ATTR_CANWRITE);
      String active = attributes.getValue(ATTR_ACTIVE);
      DataSourceConfig dataSourceConfig = new DataSourceConfig(id);

      if (weight != null) {
         dataSourceConfig.setWeight(convert(Integer.class, weight, 0));
      }

      if (canRead != null) {
         dataSourceConfig.setCanRead(convert(Boolean.class, canRead, false));
      }

      if (canWrite != null) {
         dataSourceConfig.setCanWrite(convert(Boolean.class, canWrite, false));
      }

      if (active != null) {
         dataSourceConfig.setActive(convert(Boolean.class, active, false));
      }

      return dataSourceConfig;
   }

   @Override
   public GroupDataSourceConfig buildGroupDataSourceConfig(Attributes attributes) {
      String routerStrategy = attributes.getValue(ATTR_ROUTER_STRATEGY);
      String transactionForceWrite = attributes.getValue(ATTR_TRANSACTION_FORCE_WRITE);
      String writeFirst = attributes.getValue(ATTR_WRITE_FIRST);
      String filters = attributes.getValue(ATTR_FILTERS);
      GroupDataSourceConfig groupDataSourceConfig = new GroupDataSourceConfig();

      if (routerStrategy != null) {
         groupDataSourceConfig.setRouterStrategy(routerStrategy);
      }

      if (transactionForceWrite != null) {
         groupDataSourceConfig.setTransactionForceWrite(convert(Boolean.class, transactionForceWrite, false));
      }

      if (writeFirst != null) {
         groupDataSourceConfig.setWriteFirst(convert(Boolean.class, writeFirst, false));
      }

      if (filters != null) {
         groupDataSourceConfig.setFilters(filters);
      }

      return groupDataSourceConfig;
   }

   @SuppressWarnings("unchecked")
   protected <T> T convert(Class<T> type, String value, T defaultValue) {
      if (value == null) {
         return defaultValue;
      }

      if (type == Boolean.class) {
         return (T) Boolean.valueOf(value);
      } else if (type == Integer.class) {
         return (T) Integer.valueOf(value);
      } else if (type == Long.class) {
         return (T) Long.valueOf(value);
      } else if (type == Short.class) {
         return (T) Short.valueOf(value);
      } else if (type == Float.class) {
         return (T) Float.valueOf(value);
      } else if (type == Double.class) {
         return (T) Double.valueOf(value);
      } else if (type == Byte.class) {
         return (T) Byte.valueOf(value);
      } else if (type == Character.class) {
         return (T) (Character) value.charAt(0);
      } else {
         return (T) value;
      }
   }
}
