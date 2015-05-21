## DAL SHARD 使用手册

### 如何接入
用户只需要将在spring中加入以下的datasource即可，不需关系分库细节。

	<bean id="shardDs" class="com.dianping.zebra.shard.jdbc.ShardDataSource" init-method="init">
		<!--ruleName是分库分布的规则名，该规则可以在lion上看到-->
		<property name="ruleName" value="welife" />   
	</bean>

#### 分库分表规则
每个规则都有一个名字，比如`welife`，该规则可以在lion上的`shardds.welife.shard`看到。规则类似如下：

	{
		"tableShardConfigs":[
			{"tableName":"welife_users","dimensionConfigs":[
				{"dbRule":"crc32(#bid#) %10",
				"dbIndexes":"welife0,welife1,welife2,welife3,welife4,welife5,welife6,welife7,welife8,welife9",
				"tbRule":"(crc32(#bid#)/10).toLong()%10",
				"tbSuffix":"everydb:[0,9]",
				"isMaster":true}
			],"generatedPK":"pk"}
		]
	}

	tableName: 需要分库分表的表名
	dimensionConfigs: 具体对这个表的分库分表的规则
	dbRule: 是库名的路由规则，值是一个groovy脚本计算得出
	dbIndexes: 对应的是GroupDataSource的jdbcRef，即相应的数据库
	tbRule: 是分表的路由规则，值是groovy脚本计算得出
	tbSuffix: 是表的后缀命名规则，分[everydb | alldb]。everydb指任何库上的表名都相同，从welife_users0到welife_users9，而alldb是指10表名在十个库上都不一样。
	generatedPK: 是否生成主键，目前未使用。
	

### SQL 支持列表

假设有一张表：User，主维度是：Id，辅维度是：CityId

<br/>

#### INSERT

##### 插入时必须指定主键，暂不支持自动生成主键

* INSERT INTO User WHERE (Id, Name, CityId) VALUES (1, 'test', 2)


<br/>


#### UPDATE & DELETE

##### 更新操作最好指定主维度，这样性能高

* UPDATE User SET Name = 'new name' WHERE Id = 1
* DELETE FROM User WHERE Id = 1


##### 这里虽然有主维度，但是这类操作会操作所有的主维度表，性能较差

* UPDATE User SET Name = 'new name' WHERE Id > 1
* UPDATE User SET Name = 'new name' WHERE Id IN (1,2,3,4,5)
* UPDATE User SET Name = 'new name' WHERE Id <> 1
* DELETE FROM User WHERE Id > 1
* DELETE FROM User WHERE Id IN (1,2,3,4,5)
* DELETE FROM User WHERE Id <> 1

##### 使用辅维度，但是也会操作所有主维度，性能较差

* UPDATE User SET Name = 'new name' WHERE CityId = 2
* DELETE FROM User WHERE CityId = 2


##### 使用其他字段或者不加条件，都会操作所有主维度

* UPDATE User SET Name = 'new name' WHERE Name = 'test'
* DELETE FROM User WHERE Name = 'test'


<br/>


#### SELECT

##### 指定主维度或者辅维度，都可以快速查询到数据，性能高

* SELECT Id, Name, CityId WHERE Id = 1
* SELECT Id, Name, CityId WHERE CityId = 1
* SELECT Id, Name, CityId WHERE Id = 2 AND CityId = 1


##### 不指定主维度或者辅维度，会查询所有主维度的表，性能差

* SELECT Id, Name, CityId FROM User
* SELECT Id, Name, CityId FROM User WHERE Id > 1
* SELECT Id, Name, CityId FROM User WHERE Id <> 1
* SELECT Id, Name, CityId FROM User WHERE Id IN (1,2,3,4)
* SELECT Id, Name, CityId FROM User WHERE Name LIKE '%test%'


##### LIMIT, OFFSET, ORDER BY 都支持，性能差

* SELECT Id, Name, CityId FROM User ORDER BY CityId LIMIT 1 OFFSET 1


##### 支持子查询，但无法识别子查询中的分区字段，性能差

* SELECT Id, Name, CityId FROM User WHERE Id IN (SELECT Id FROM User WHERE CityId = 1)


##### 支持 GROUP BY, COUNT, MAX, MIN 都支持，AVG 不支持，性能差，另外必须加字段别名

* SELECT CityId, MAX(Id) as MaxId FROM User GROUP BY CityId
* SELECT CityId, MIN(Id) as MinId FROM User GROUP BY CityId
* SELECT CityId, COUNT(Id) as AllId FROM User GROUP BY CityId


### 使用说明

### 多维度支持

### 迁移过程

0. 项目程序升级，使用 ShardDataSource，但是配置成使用原来的数据库
1. 在 puma-admin 上启动多维度同步任务
2. 在 puma-admin 上启动迁移任务
3. 在迁移任务快完成的时候，把原来的数据库设置成`readonly`，禁止写入
4. 通过 Lion 修改程序配置，让程序使用新的分库分表数据库
5. 关闭迁移任务，迁移完成，老数据库作废，新数据库开始运作