## Zebra

### 简介
Zebra是一个支持双IDC切换，并可以简化配置的DAL基础组件。
更多介绍：[https://drive.google.com/a/dianping.com/#folders/0B3Kh7VPBVPl_alFBWHN1ZzZkZjQ](https://drive.google.com/a/dianping.com/#folders/0B3Kh7VPBVPl_alFBWHN1ZzZkZjQ)


### 使用说明
##### POM依赖
	<dependency>
    	<groupId>com.dianping.zebra</groupId>
	    <artifactId>zebra-api</artifactId>
    	<version>${version}</version>
	</dependency>

注意：升级`Cat`到`1.0.5`版本，因为`2.3.0`开始依赖的是`1.0.5`版本的`Cat`

##### Spring中DataSource的配置
	<bean id="dataSource" class="com.dianping.zebra.group.jdbc.GroupDataSource" 
            init-method="init">
		<constructor-arg value="bizTest" />      
	</bean>

原来的DataSource节点可以删除

### 更新说明
##### 2.4.8
* [*]重构`FailOverDataSource`，检测逻辑更合理
* [+]兼容老版本`avatar`强制读写库的逻辑，业务方不需要修改相关逻辑代码

##### 2.4.7
* [*]修复`iBatis`中使用`SelectKey`后无法获得主键的Bug