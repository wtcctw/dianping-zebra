## Zebra

### 简介
`Zebra`是在C3P0基础上进行包装成的点评内部使用的数据源工具，它有以下的功能点：
1. 简化数据源的jdbc相关配置
2. 纯粹的读写分离，支持SQL语句hint
3. 写库的自动切换
4. 配置变化后，数据连接池支持动态自刷新，应用无需重启
5. 更丰富的监控信息在CAT上呈现
6. DBA可以更加方便的进行数据库维护，如写库切换，读库上线下线，用户名密码变更等操作

### 使用说明
##### POM依赖
	<dependency>
    	<groupId>com.dianping.zebra</groupId>
	    <artifactId>zebra-api</artifactId>
    	<version>${version}</version>
	</dependency>

注意：如果想要在CAT中的心跳中看到数据源连接池的信息，需升级`Cat`到`1.0.5`版本，`dpsf-net`升级到`2.1.21`版本以上。

##### 单数据库在 Spring 中 DataSource 的配置
	<bean id="dataSource" class="com.dianping.zebra.group.jdbc.SingleDataSource">
		<property name="jdbcRef" value="TuanGou2010" />
		<property name="minPoolSize" value="5" />
		<property name="maxPoolSize" value="25" />
        <property name="initialPoolSize" value="5" />
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

##### 多数据库在 Spring 中 DataSource 的配置
	<bean id="dataSource" class="com.dianping.zebra.group.jdbc.GroupDataSource" init-method="init">
		<property name="jdbcRef" value="TuanGou2010" />
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

##### 在 Spring 中使用默认配置的 DataSource 的配置
    <bean id="dataSource" class="com.dianping.zebra.group.jdbc.GroupDataSource" init-method="init">
        <property name="jdbcRef" value="TuanGou2010" />  
    </bean>
其中，`jdbcRef`就是该数据库的jdbc参数，由DBA给出。其余C3P0参数可以在项目Spring里面直接定义，也可以使用Lion中定义的值。
情况一：C3P0参数是直接定义的，那么C3P0的参数将不具有动态刷新的功能。
情况二：C3P0参数是使用的Lion中定义的值，那么一旦修改了Lion的参数值后，该数据源将进行自刷新。
情况三：业务也可以不配置任何C3P0参数，所有参数将直接继承自`jdbcRef`所给出的默认配置。
但不推荐这种方式，因为C3P0的配置属于业务方，使用默认配置无法做到业务隔离。

### 老业务兼容情况
通过`Phoenix`强制升级`zebra-ds-monitor`的版本到2.4.9-SNAPSHOT以上，
`Zebra`将会对所有的ComboDataSource进行替换，替换成`SingleDataSource`。通过替换，虽然具备了以上大部分功能，
但这种方式有它的局限性，它不支持许多功能：如读库切换操作，写库Failover等。
要想使用DAL的全部功能，必须显示的修改业务Spring配置，即上述的使用方式。

### 已知问题清单

### 更新说明
#### 2.4.9-SNAPSHOT
* [+] 支持Spring方式配置`GroupDataSource`
* [+] 支持Spring方式配置`SingleDataSource`
* [+] 通过升级zebra-ds-monitor,老应用自动替换`C3P0`到`SingleDataSource`
* [+] 两种DataSource均支持配置动态刷新

##### 2.4.8
* [*] 重构`FailOverDataSource`，检测逻辑更合理
* [+] 兼容老版本`avatar`强制读写库的逻辑，业务方不需要修改相关逻辑代码

##### 2.4.7
* [*] 修复`iBatis`中使用`SelectKey`后无法获得主键的Bug