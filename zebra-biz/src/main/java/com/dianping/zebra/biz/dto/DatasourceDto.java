package com.dianping.zebra.biz.dto;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class DatasourceDto {
    private String jdbcUrl;

    private String username;

    private Integer initPoolSize;

    private Integer maxPoolSize;

    private Integer minPoolSize;

    private String beanName;

    private String type;

    private Boolean replaced = false;

    private String name;

    private Integer status;

    public DatasourceDto(String beanName) {
        this.beanName = beanName;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getInitPoolSize() {
        return initPoolSize;
    }

    public void setInitPoolSize(Integer initPoolSize) {
        this.initPoolSize = initPoolSize;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public Integer getMinPoolSize() {
        return minPoolSize;
    }

    public void setMinPoolSize(Integer minPoolSize) {
        this.minPoolSize = minPoolSize;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getReplaced() {
        return replaced;
    }

    public void setReplaced(Boolean replaced) {
        this.replaced = replaced;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
