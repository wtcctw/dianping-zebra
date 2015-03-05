package com.dianping.zebra.admin.dto;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class DatabaseDto {
    private String name;

    private int groupDataSource;

    private int dpdlDataSource;

    private int c3p0DataSource;

    private int singleDataSource;

    private int otherDataSource;

    private int totalDataSource;

    private int replacedSingleDataSource;

    private int replacedDpdlDataSource;

    private int updateStatus;

    private Map<String, AppDto> apps = new LinkedHashMap<String, AppDto>();

    public DatabaseDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupDataSource() {
        return groupDataSource;
    }

    public void setGroupDataSource(int groupDataSource) {
        this.groupDataSource = groupDataSource;
    }

    public int getDpdlDataSource() {
        return dpdlDataSource;
    }

    public void setDpdlDataSource(int dpdlDataSource) {
        this.dpdlDataSource = dpdlDataSource;
    }

    public int getC3p0DataSource() {
        return c3p0DataSource;
    }

    public void setC3p0DataSource(int c3p0DataSource) {
        this.c3p0DataSource = c3p0DataSource;
    }

    public int getSingleDataSource() {
        return singleDataSource;
    }

    public void setSingleDataSource(int singleDataSource) {
        this.singleDataSource = singleDataSource;
    }

    public int getOtherDataSource() {
        return otherDataSource;
    }

    public void setOtherDataSource(int otherDataSource) {
        this.otherDataSource = otherDataSource;
    }

    public int getTotalDataSource() {
        return totalDataSource;
    }

    public void setTotalDataSource(int totalDataSource) {
        this.totalDataSource = totalDataSource;
    }

    public int getReplacedSingleDataSource() {
        return replacedSingleDataSource;
    }

    public void setReplacedSingleDataSource(int replacedSingleDataSource) {
        this.replacedSingleDataSource = replacedSingleDataSource;
    }

    public int getReplacedDpdlDataSource() {
        return replacedDpdlDataSource;
    }

    public void setReplacedDpdlDataSource(int replacedDpdlDataSource) {
        this.replacedDpdlDataSource = replacedDpdlDataSource;
    }

    public int getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(int updateStatus) {
        this.updateStatus = updateStatus;
    }

    public Map<String, AppDto> getApps() {
        return apps;
    }

    public void setApps(Map<String, AppDto> apps) {
        this.apps = apps;
    }


    public DatabaseDto incC3p0DataSource() {
        this.c3p0DataSource++;
        return this;
    }

    public DatabaseDto incC3p0DataSource(int c3p0DataSource) {
        this.c3p0DataSource += c3p0DataSource;
        return this;
    }

    public DatabaseDto incDpdlDataSource() {
        this.dpdlDataSource++;
        return this;
    }

    public DatabaseDto incDpdlDataSource(int dpdlDataSource) {
        this.dpdlDataSource += dpdlDataSource;
        return this;
    }

    public DatabaseDto incGroupDataSource() {
        this.groupDataSource++;
        return this;
    }

    public DatabaseDto incGroupDataSource(int groupDataSource) {
        this.groupDataSource += groupDataSource;
        return this;
    }

    public DatabaseDto incOtherDataSource() {
        this.otherDataSource++;
        return this;
    }

    public DatabaseDto incOtherDataSource(int otherDataSource) {
        this.otherDataSource += otherDataSource;
        return this;
    }

    public DatabaseDto incReplacedDpdlDataSource() {
        this.replacedDpdlDataSource++;
        return this;
    }

    public DatabaseDto incReplacedDpdlDataSource(int replacedDpdlDataSource) {
        this.replacedDpdlDataSource += replacedDpdlDataSource;
        return this;
    }

    public DatabaseDto incReplacedSingleDataSource() {
        this.replacedSingleDataSource++;
        return this;
    }

    public DatabaseDto incReplacedSingleDataSource(int replacedSingleDataSource) {
        this.replacedSingleDataSource += replacedSingleDataSource;
        return this;
    }

    public DatabaseDto incSingleDataSource() {
        this.singleDataSource++;
        return this;
    }

    public DatabaseDto incSingleDataSource(int singleDataSource) {
        this.singleDataSource += singleDataSource;
        return this;
    }

    public DatabaseDto incTotalDataSource() {
        this.totalDataSource++;
        return this;
    }

    public DatabaseDto incTotalDataSource(int totalDataSource) {
        this.totalDataSource += totalDataSource;
        return this;
    }

    public AppDto findApp(String name) {
        return apps.get(name);
    }

    public AppDto findOrCreateApp(String name) {
        AppDto app = apps.get(name);

        if (app == null) {
            synchronized (apps) {
                app = apps.get(name);

                if (app == null) {
                    app = new AppDto(name);
                    apps.put(name, app);
                }
            }
        }

        return app;
    }
}
