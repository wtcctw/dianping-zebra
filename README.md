## Zebra

### 简介
`Zebra`是一个支持双IDC切换，并可以简化配置的DAL基础组件。
更多介绍：[https://drive.google.com/a/dianping.com/#folders/0B3Kh7VPBVPl_alFBWHN1ZzZkZjQ](https://drive.google.com/a/dianping.com/#folders/0B3Kh7VPBVPl_alFBWHN1ZzZkZjQ)


### 老项目支持情况（开发中）
`Zebra`将会支持老项目的无缝切换。
满足以下条件的项目可以通过`Phoenix`升级。

#### 使用C3P0并引用zebra-ds-moniror的项目
自动替换成`SingleDataSourceC3P0Adapter`

#### 使用C3P0、dpdl并引用zebra-ds-moniror的项目
自动替换成`GroupDataSource`

### 新项目使用说明
##### POM依赖
	<dependency>
    	<groupId>com.dianping.zebra</groupId>
	    <artifactId>zebra-api</artifactId>
    	<version>${version}</version>
	</dependency>

注意：升级`Cat`到`1.0.5`版本，因为`2.3.0`开始依赖的是`1.0.5`版本的`Cat`

##### 单数据库在 Spring 中 DataSource 的配置
	<bean id="dataSource" class="com.dianping.zebra.group.jdbc.SingleDataSourceC3P0Adapter">
		<constructor-arg value="bizTest" />
		<property name="minPoolSize" value="5" />
		<property name="maxPoolSize" value="25" />
	</bean>

##### 多数据库在 Spring 中 DataSource 的配置
	<bean id="dataSource" class="com.dianping.zebra.group.jdbc.GroupDataSource" 
            init-method="init">
		<constructor-arg value="bizTest" />      
	</bean>

### 更新说明
#### 2.4.9-SNAPSHOT
* [+] 支持单独配置`SingleDataSource`
* [+] 支持自动替换老的`C3P0`配置到`SingleDataSource`

##### 2.4.8
* [*] 重构`FailOverDataSource`，检测逻辑更合理
* [+] 兼容老版本`avatar`强制读写库的逻辑，业务方不需要修改相关逻辑代码

##### 2.4.7
* [*] 修复`iBatis`中使用`SelectKey`后无法获得主键的Bug