package com.dianping.zebra.biz.dto;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class AppDto {
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

    private Map<String, MachineDto> machines = new LinkedHashMap<String, MachineDto>();

    public AppDto(String name) {
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

    public Map<String, MachineDto> getMachines() {
        return machines;
    }

    public void setMachines(Map<String, MachineDto> machines) {
        this.machines = machines;
    }



    public MachineDto findMachine(String ip) {
        return machines.get(ip);
    }

    public MachineDto findOrCreateMachine(String ip) {
        MachineDto machine = machines.get(ip);

        if (machine == null) {
            synchronized (machines) {
                machine = machines.get(ip);

                if (machine == null) {
                    machine = new MachineDto(ip);
                    machines.put(ip, machine);
                }
            }
        }

        return machine;
    }

    public AppDto incC3p0DataSource() {
        this.c3p0DataSource++;
        return this;
    }

    public AppDto incC3p0DataSource(int c3p0DataSource) {
        this.c3p0DataSource += c3p0DataSource;
        return this;
    }

    public AppDto incDpdlDataSource() {
        this.dpdlDataSource++;
        return this;
    }

    public AppDto incDpdlDataSource(int dpdlDataSource) {
        this.dpdlDataSource += dpdlDataSource;
        return this;
    }

    public AppDto incGroupDataSource() {
        this.groupDataSource++;
        return this;
    }

    public AppDto incGroupDataSource(int groupDataSource) {
        this.groupDataSource += groupDataSource;
        return this;
    }

    public AppDto incOtherDataSource() {
        this.otherDataSource++;
        return this;
    }

    public AppDto incOtherDataSource(int otherDataSource) {
        this.otherDataSource += otherDataSource;
        return this;
    }

    public AppDto incReplacedDpdlDataSource() {
        this.replacedDpdlDataSource++;
        return this;
    }

    public AppDto incReplacedDpdlDataSource(int replacedDpdlDataSource) {
        this.replacedDpdlDataSource += replacedDpdlDataSource;
        return this;
    }

    public AppDto incReplacedSingleDataSource() {
        this.replacedSingleDataSource++;
        return this;
    }

    public AppDto incReplacedSingleDataSource(int replacedSingleDataSource) {
        this.replacedSingleDataSource += replacedSingleDataSource;
        return this;
    }

    public AppDto incSingleDataSource() {
        this.singleDataSource++;
        return this;
    }

    public AppDto incSingleDataSource(int singleDataSource) {
        this.singleDataSource += singleDataSource;
        return this;
    }

    public AppDto incTotalDataSource() {
        this.totalDataSource++;
        return this;
    }

    public AppDto incTotalDataSource(int totalDataSource) {
        this.totalDataSource += totalDataSource;
        return this;
    }
}
