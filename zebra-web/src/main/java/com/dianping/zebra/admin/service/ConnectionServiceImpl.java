package com.dianping.zebra.admin.service;

import com.dianping.cat.Cat;
import com.dianping.zebra.admin.dto.ConnectionStatusDto;
import com.dianping.zebra.admin.mock.GroupDataSourceExtend;
import com.dianping.zebra.config.ConfigService;
import com.dianping.zebra.group.config.datasource.entity.DataSourceConfig;
import com.dianping.zebra.group.config.datasource.entity.GroupDataSourceConfig;
import com.dianping.zebra.group.jdbc.GroupDataSource;
import com.dianping.zebra.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

@Service
public class ConnectionServiceImpl implements ConnectionService {

    @Override
    public ConnectionStatusDto getConnectionResult(final String jdbcRef, final DalConfigService.GroupConfigModel modal) {
        ConnectionStatusDto result = new ConnectionStatusDto();

        GroupDataSource ds = null;
        try {

            if (modal != null) {
                GroupDataSourceExtend ext = new GroupDataSourceExtend();
                ext.setJdbcRef(modal.getId());
                ext.setDataSourceConfigManager(new ConfigService() {
                    @Override
                    public void addPropertyChangeListener(PropertyChangeListener listener) {

                    }

                    @Override
                    public String getProperty(String key) {
                        if (key.equals(String.format("groupds.%s.mapping", modal.getId()))) {
                            return modal.getConfig();
                        }
                        for (DalConfigService.DsConfigModel dsConfig : modal.getConfigs()) {
                            for (DalConfigService.ConfigProperty prop : dsConfig.getProperties()) {
                                if (key.equals(prop.getKey())) {
                                    return prop.getNewValue();
                                }
                            }
                        }
                        return null;
                    }

                    @Override
                    public void init() {

                    }
                });

                ds = ext;
            } else {
                ds = new GroupDataSource(jdbcRef);
                ds.setJdbcRef(jdbcRef);
            }

            ds.setFilter("stat,cat,wall");
            ds.init();

            testDs(ds);

            result.setConnected(true);
        } catch (Exception t) {
            Cat.logError(t);
            result.setConnected(false);

            StringBuffer sb = new StringBuffer();
            Throwable exp = t;
            while (exp != null) {
                sb.append(exp.getMessage());
                sb.append("\r\n");
                exp = exp.getCause();
            }

            result.setException(sb.toString());
        } finally {
            if (ds != null) {
                try {
                    ds.close();
                } catch (Exception ignore) {
                }
            }
        }

        hidePassword(ds.getConfig());
        result.setConfig(ds.getConfig().toString());

        return result;
    }

    public void hidePassword(GroupDataSourceConfig configs) {
        for (Map.Entry<String, DataSourceConfig> config : configs.getDataSourceConfigs().entrySet()) {
            config.getValue().setPassword(config.getValue().getPassword() != null ?
                    StringUtils.repeat("*", config.getValue().getPassword().length()) :
                    null);
        }
    }

    public void testDs(DataSource ds) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            stmt.executeQuery("select 1");
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

    }
}
