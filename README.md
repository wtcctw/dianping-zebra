## DAL

## 简介
`DAL`是在C3P0基础上进行包装成的点评内部使用的数据源工具，它有以下的功能点：
1. 简化数据源的jdbc相关配置
2. 纯粹的读写分离，支持SQL语句hint
3. 写库的自动切换
4. 配置变化后，数据连接池支持动态自刷新，应用无需重启
5. 更丰富的监控信息在`CAT`上呈现
6. 集成Phoenix Inspect页面，方便看到DataSource的实时信息
7. DBA可以更加方便的进行数据库维护，如写库切换，读库上线下线，用户名密码变更等操作
8. 兼容老的DPDL

## 使用说明
### POM依赖

	<dependency>
    	<groupId>com.dianping.zebra</groupId>
	    <artifactId>zebra-api</artifactId>
    	<version>${version}</version>
	</dependency>

目前的最新版本为`2.5.9`

### 数据库监控功能

	<dependency>
        <groupId>com.dianping.zebra</groupId>
        <artifactId>zebra-ds-monitor-client</artifactId>
        <version>${version}</version>
    </dependency>
    
`version`和`zebra-api`保持一致
    
### 其他依赖
* 如果想要在`CAT`中的心跳中看到数据源连接池的信息，需升级`CAT`到`1.0.5`版本，`dpsf-net`升级到`2.1.21`版本以上。

## Spring 配置

### 在 Spring 中 DataSource 的配置
	<bean id="dataSource" class="com.dianping.zebra.group.jdbc.GroupDataSource" init-method="init">
		<property name="jdbcRef" value="tuangou2010" />
		<property name="minPoolSize" value="${lion.key.minPoolSize}" />
		<property name="maxPoolSize" value="${lion.key.maxPoolSize}" />
        <property name="initialPoolSize" value="${lion.key.initialPoolSize}" />
    	<property name="maxIdleTime" value="1800" />
		<property name="idleConnectionTestPeriod" value="60" />
		<property name="acquireRetryAttempts" value="3" />
		<property name="acquireRetryDelay" value="300" />
		<property name="maxStatements" value="0" />
		<property name="maxStatementsPerConnection" value="100" />
		<property name="numHelperThreads" value="6" />
		<property name="maxAdministrativeTaskTime" value="5" />
		<property name="preferredTestQuery" value="SELECT 1" />   
	</bean>

### 在 Spring 中使用默认 DataSource 的配置
    <bean id="dataSource" class="com.dianping.zebra.group.jdbc.GroupDataSource" init-method="init">
		<property name="jdbcRef" value="tuangou2010" /> 
    </bean>

### 配置说明
其中，`jdbcRef`属性是该数据库的在`Lion`中的业务名称，一般是数据库名的全小写，`zebra`会自动根据这个名字到`Lion`上查找`jdbcUrl`,`user`,`password`和`driverClass`。其余C3P0参数可以在项目Spring里面直接定义，也可以使用Lion中定义的值。
1. C3P0参数是在`bean`中直接定义的，那么C3P0的参数将不具有动态刷新的功能。
2. C3P0参数是在`bean`中，读取`Lion`中定义的值，那么一旦修改了`Lion`的参数值后，该数据源将进行自刷新。
3. 业务也可以不配置任何C3P0参数，所有参数将直接继承自`jdbcRef`所给出的默认配置。但不推荐这种方式，因为C3P0的配置属于业务方，使用默认配置无法做到业务隔离。

### 答疑解惑
Q：为什么要加`init-method`，不加会怎么样？

A：`Zebra`内需要启动多线程，而在构造函数中启动线程是不安全的，所以需要这两个方法来启动和销毁线程。

## 老业务兼容情况
通过`Phoenix`强制升级`zebra-ds-monitor`的版本到`2.5.9`以上，`Zebra`会自动替换满足条件的`DataSource`。

#### 没有使用`dpdl`的`ComboPooledDataSource`
* 数据源在`Lion`的白名单`groupds.autoreplace.database`配置过
* 在`Lion`上找到了`groupds.${database_name}.mapping`配置
* 判断原来的数据源用户是写库还是读库，使用以上配置中对应的读库组或者写库
* 数据源是`mysql`

#### 使用`dpdl`的
* 数据源在`Lion`的白名单`groupds.autoreplace.database`配置过
* 在`Lion`上找到了`groupds.${database_name}.mapping`配置
* 写库数据源是`mysql`

## 更新说明
### 2.5.9
* [/] 修复了transaction潜在的bug

### 2.5.8
* [/] 修正了Lion的值不停变化导致数据源的频繁刷新的bug(只有在应用使用了alpaca的情况下发生)
* [-] 移除了zebra-api对phoenix-environment的强依赖

### 2.5.7
* [+] `FailOverDataSource`加入自动终止`Monitor`线程的功能，防止内存泄露
* [+] 支持事务中默认走写库

### 2.5.5
* [+] 支持自动替换`dpdl`数据源，并且可以通过数据库白名单进行限制
* [/] 移除自动替换`SingleDataSource`，全部改为替换成`GroupDataSource`
* [+] 添加`DataSource`信息上传功能，便于监控升级情况
* [+] 将`DataSource`信息展示在`inspect`页面，便于观察站点状况
* [+] 支持针对单独应用配置`mapping`，并支持运行时切换

### 2.5.2
* [/] 修复`GroupConnection`中`getMetaData`时总是得到写库信息的问题
* [/] 修复`ZebraDsMonitorClient`中`hawk`版本过低的问题
* [/] 修复`ZebraDsMonitorClient`的`bean`在`Spring`中被多次声明时出错的问题
* [/] 修改`CAT`监控参数，隐藏密码

### 2.5.1
* [/] 将`Lion`中的用户名的配置从`user`改成`username`

#### 2.5.0
* [+] 支持`Spring`方式配置`GroupDataSource`
* [+] 支持`Spring`方式配置`SingleDataSource`
* [+] 通过升级`zebra-ds-monitor`,老应用自动替换`ComboPooledDataSource`到`SingleDataSource`
* [+] 两种`DataSource`均支持配置动态刷新

### 2.4.8
* [*] 重构`FailOverDataSource`，检测逻辑更合理
* [+] 兼容老版本`avatar`强制读写库的逻辑，业务方不需要修改相关逻辑代码

### 2.4.7
* [*] 修复`iBatis`中使用`SelectKey`后无法获得主键的Bug
