package com.dianping.zebra.admin.dto;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class ReportDto {
    private int groupDataSource;

    private int dpdlDataSource;

    private int c3p0DataSource;

    private int singleDataSource;

    private int otherDataSource;

    private int totalDataSource;

    private int replacedSingleDataSource;

    private int replacedDpdlDataSource;

    private int successDatabase;

    private int halfDoneDatabase;

    private int failureDatabase;

    private Map<String, DatabaseDto> databases = new LinkedHashMap<String, DatabaseDto>();

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

    public int getSuccessDatabase() {
        return successDatabase;
    }

    public void setSuccessDatabase(int successDatabase) {
        this.successDatabase = successDatabase;
    }

    public int getHalfDoneDatabase() {
        return halfDoneDatabase;
    }

    public void setHalfDoneDatabase(int halfDoneDatabase) {
        this.halfDoneDatabase = halfDoneDatabase;
    }

    public int getFailureDatabase() {
        return failureDatabase;
    }

    public void setFailureDatabase(int failureDatabase) {
        this.failureDatabase = failureDatabase;
    }

    public Map<String, DatabaseDto> getDatabases() {
        return databases;
    }

    public void setDatabases(Map<String, DatabaseDto> databases) {
        this.databases = databases;
    }


    public ReportDto incC3p0DataSource() {
        this.c3p0DataSource++;
        return this;
    }

    public ReportDto incC3p0DataSource(int c3p0DataSource) {
        this.c3p0DataSource += c3p0DataSource;
        return this;
    }

    public ReportDto incDpdlDataSource() {
        this.dpdlDataSource++;
        return this;
    }

    public ReportDto incDpdlDataSource(int dpdlDataSource) {
        this.dpdlDataSource += dpdlDataSource;
        return this;
    }

    public ReportDto incFailureDatabase() {
        this.failureDatabase++;
        return this;
    }

    public ReportDto incFailureDatabase(int failureDatabase) {
        this.failureDatabase += failureDatabase;
        return this;
    }

    public ReportDto incGroupDataSource() {
        this.groupDataSource++;
        return this;
    }

    public ReportDto incGroupDataSource(int groupDataSource) {
        this.groupDataSource += groupDataSource;
        return this;
    }

    public ReportDto incHalfDoneDatabase() {
        this.halfDoneDatabase++;
        return this;
    }

    public ReportDto incHalfDoneDatabase(int halfDoneDatabase) {
        this.halfDoneDatabase += halfDoneDatabase;
        return this;
    }

    public ReportDto incOtherDataSource() {
        this.otherDataSource++;
        return this;
    }

    public ReportDto incOtherDataSource(int otherDataSource) {
        this.otherDataSource += otherDataSource;
        return this;
    }

    public ReportDto incReplacedDpdlDataSource() {
        this.replacedDpdlDataSource++;
        return this;
    }

    public ReportDto incReplacedDpdlDataSource(int replacedDpdlDataSource) {
        this.replacedDpdlDataSource += replacedDpdlDataSource;
        return this;
    }

    public ReportDto incReplacedSingleDataSource() {
        this.replacedSingleDataSource++;
        return this;
    }

    public ReportDto incReplacedSingleDataSource(int replacedSingleDataSource) {
        this.replacedSingleDataSource += replacedSingleDataSource;
        return this;
    }

    public ReportDto incSingleDataSource() {
        this.singleDataSource++;
        return this;
    }

    public ReportDto incSingleDataSource(int singleDataSource) {
        this.singleDataSource += singleDataSource;
        return this;
    }

    public ReportDto incSuccessDatabase() {
        this.successDatabase++;
        return this;
    }

    public ReportDto incSuccessDatabase(int successDatabase) {
        this.successDatabase += successDatabase;
        return this;
    }

    public ReportDto incTotalDataSource() {
        this.totalDataSource++;
        return this;
    }

    public ReportDto incTotalDataSource(int totalDataSource) {
        this.totalDataSource += totalDataSource;
        return this;
    }

    public DatabaseDto findDatabase(String name) {
        return databases.get(name);
    }

    public DatabaseDto findOrCreateDatabase(String name) {
        DatabaseDto database = databases.get(name);

        if (database == null) {
            synchronized (databases) {
                database = databases.get(name);

                if (database == null) {
                    database = new DatabaseDto(name);
                    databases.put(name, database);
                }
            }
        }

        return database;
    }
}
