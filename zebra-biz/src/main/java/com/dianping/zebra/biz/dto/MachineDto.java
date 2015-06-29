package com.dianping.zebra.biz.dto;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class MachineDto {
    private String ip;

    private String version;

    private int updateStatus;

    private boolean intergrateWithDal = true;

    private Map<String, DatasourceDto> datasources = new LinkedHashMap<String, DatasourceDto>();

    public MachineDto(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(int updateStatus) {
        this.updateStatus = updateStatus;
    }

    public boolean isIntergrateWithDal() {
        return intergrateWithDal;
    }

    public void setIntergrateWithDal(boolean intergrateWithDal) {
        this.intergrateWithDal = intergrateWithDal;
    }

    public Map<String, DatasourceDto> getDatasources() {
        return datasources;
    }

    public void setDatasources(Map<String, DatasourceDto> datasources) {
        this.datasources = datasources;
    }

    public MachineDto addDatasource(DatasourceDto datasource) {
        datasources.put(datasource.getBeanName(), datasource);
        return this;
    }

    public DatasourceDto findDatasource(String beanName) {
        return datasources.get(beanName);
    }

    public DatasourceDto findOrCreateDatasource(String beanName) {
        DatasourceDto datasource = datasources.get(beanName);

        if (datasource == null) {
            synchronized (datasources) {
                datasource = datasources.get(beanName);

                if (datasource == null) {
                    datasource = new DatasourceDto(beanName);
                    datasources.put(beanName, datasource);
                }
            }
        }

        return datasource;
    }
}
