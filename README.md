## Zebra 

## 简介
`Zebra`是在`c3p0`和`tomcat-jdbc`基础上进行包装成的点评内部使用的`动态数据源`，它有以下的功能点：
1. 简化数据源的jdbc相关配置，业务无需关系主库或者从库的位置、数量、用户名以及密码，拿到`jdbcRef`即可使用
2. 纯粹的读写分离，支持SQL语句hint
3. 配置变化后，Zebra能够动态自刷新，无需应用进行重启
4. 支持分库分表，具体接入请参考文档[README_SHARD.md](/arch/zebra/blob/master/README_SHARD.md)
5. 业务可以自主选择使用c3p0或者tomcat-jdbc数据源，接口保持一致。
6. 更丰富的监控信息在`CAT`上呈现 : 在heartbeat中能够看到连接池的信息
7. 集成Phoenix Inspect页面，方便看到DataSource的实时信息
8. DBA可以更加方便的进行数据库维护，如写库切换，读库上线下线，用户名密码变更等操作
9. 兼容点评内老的DPDL强制走主库的逻辑
10. 支持SQL流控，DBA可以在后台按照比例对指定SQL语句进行限制访问
11. 业务可以更加方便的进行`迁库/拆库`的操作，具体请见[Database_Migrate.md](/arch/zebra/blob/master/Database_Migrate.md)

## 使用说明

### POM依赖

	<dependency>
    	<groupId>com.dianping.zebra</groupId>
	    <artifactId>zebra-api</artifactId>
    	<version>${version}</version>
	</dependency>

目前的最新版本为`2.7.6`

### 数据库监控功能
如果想要在CAT上对数据库进行监控，请务必添加该组件

    <dependency>
        <groupId>com.dianping.zebra</groupId>
        <artifactId>zebra-ds-monitor-client</artifactId>
        <version>${version}</version>
    </dependency>
    
`version`和`zebra-api`保持一致
SQL调用依赖需要加载一个配置文件 /config/spring/common/appcontext-ds-monitor.xml和/config/spring/common/appcontext-ds-replacer.xml，这些文件是在zebra-ds-monitor-client这个jar包下。 web.xml 加载是需要加入classpath*:config/spring/common/appcontext-ds-monitor.xml classpath*:config/spring/common/appcontext-ds-replacer.xml
    
### 其他依赖

* 如果想要在`CAT`中的心跳中看到数据源连接池的信息，需升级`cat-client`到`1.1.3`之上，`dpsf-net`升级到`2.1.21`之上,`lion-client`升级到`0.4.8`之上的版本。

## Spring 配置

### 在 Spring 中 DataSource 的配置

	<bean id="dataSource" class="com.dianping.zebra.group.jdbc.GroupDataSource" init-method="init">
        <!-- 唯一确定数据库的key，请咨询DBA使用哪个key -->
		<property name="jdbcRef" value="tuangou2010" /> 
        <!-- 选择使用背后使用哪种数据源，"c3p0"或者"tomcat-jdbc"，可以不配，默认值为"c3p0" -->
        <property name="poolType" value="c3p0" /> 
        <!-- 该值对应tomcat-jdbc的"minIdle" -->
		<property name="minPoolSize" value="${lion.key.minPoolSize}" />
        <!-- 该值对应tomcat-jdbc的"maxActive" -->
		<property name="maxPoolSize" value="${lion.key.maxPoolSize}" />
        <!-- 该值对应tomcat-jdbc的"initialSize" -->
        <property name="initialPoolSize" value="${lion.key.initialPoolSize}" />
        <!-- 该值对应tomcat-jdbc的"maxWait" -->
        <property name="checkoutTimeout" value="1000" />
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

支持c3p0所有数据源配置，而对于tomcat-jdbc，仅支持上述几个连接池大小的配置。
对于tomcat-jdbc的其他配置，zebra配置了一套默认值。

### 在 Spring 中使用默认 DataSource 的配置

    <bean id="dataSource" class="com.dianping.zebra.group.jdbc.GroupDataSource" init-method="init">
		<property name="jdbcRef" value="tuangou2010" /> 
    </bean>

### 迁移说明

一般情况下，业务使用的c3p0或者DPDL这两种数据源可以直接删除，改成上面的数据源，并保证bean的id不变即可完成迁移。

### 配置说明

其中，`jdbcRef`属性是该数据库的在`Lion`中的业务名称，一般是数据库名的全小写，`zebra`会自动根据这个名字到`Lion`上查找`jdbcUrl`,`user`,`password`和`driverClass`。其余C3P0参数可以在项目Spring里面直接定义，也可以使用Lion中定义的值。
1. C3P0参数是在`bean`中直接定义的，那么C3P0的参数将不具有动态刷新的功能。
2. C3P0参数是在`bean`中，读取`Lion`中定义的值，那么一旦修改了`Lion`的参数值后，该数据源将进行自刷新。
3. 业务也可以不配置任何C3P0参数，所有参数将直接继承自`jdbcRef`所给出的默认配置。但不推荐这种方式，因为C3P0的配置属于业务方，使用默认配置无法做到业务隔离。

### 特殊情况配置
1.如果业务对主从延迟要求很高，不能容忍一点延迟，比如支付等业务，可以根据需要配置两个数据源，其中一个`只走读库`，另外一个`只走写库`，可以在spring的配置中加入如下的property。
一般情况下，除非对主从延迟要求很高，一般应用`不建议`使用该配置。

    <bean id="readDs" class="com.dianping.zebra.group.jdbc.GroupDataSource" init-method="init">
    	<property name="jdbcRef" value="tuangou2010" /> 
        <property name="routerType" value="load-balance" /> <!-- 只走读库 -->
    </bean>
    
    <bean id="writeDs" class="com.dianping.zebra.group.jdbc.GroupDataSource" init-method="init">
    	<property name="jdbcRef" value="tuangou2010" /> 
        <property name="routerType" value="fail-over" /><!-- 只走写库 -->
    </bean>
    
2.关闭登录用户默认走写库的逻辑。目前，为了兼容老的DPDL登录用户走写库的逻辑，DAL也默认开启了，当然也可以通过在spring的配置中加入如下的property来关闭该功能。

    <bean id="datasource" class="com.dianping.zebra.group.jdbc.GroupDataSource" init-method="init">
    	<property name="jdbcRef" value="tuangou2010" /> 
        <property name="forceWriteOnLogin" value="false" /> <!-- 关闭登录用户走写库，默认值是true，表明开启该功能 -->
    </bean>

### 常见问题
#### Q：为什么要加`init-method`，不加会怎么样？
A：`Zebra`内需要启动多线程，而在构造函数中启动线程是不安全的，所以需要这两个方法来启动和销毁线程。

#### Q：我想看jdbcRef的配置，在哪里可以看到？
A：想要理解并查看配置，请看文档 [README_CONFIG.md](/arch/zebra/blob/master/README_CONFIG.md)

#### Q：GroupDataSource是如何根据jdbcRef读取配置的?
A：根据jdbcRef可以找到groupds.{jdbcRef}.mapping这个key，从而读到这个值；根据里面的值再进一步的去寻找ds的值，从而构建出一份配置文件，然后进行初始化。

#### Q：GroupDataSource是如何做到动态刷新的？
A：利用Lion配置变更会通知的机制。一旦任何配置变更，GroupDataSource就进行自刷新。自刷新的逻辑是，重新建立新的DataSource，然后销毁老的DataSource。

#### Q：GroupDataSource是如何做到读重试的？
A：一旦从某台读库上取连接失败，那么会自动去另外一台读库上进行重试，重试一次。有两个条件：一、配置有两台读库；二、针对的是取连接失败动作才重试

#### Q：如何判断重试是否成功？
A：在CAT上的SQL报表中，可以看到重试的sql名字和原来的sql名字有区分，重试的sql名字后缀是`(retry-by-zebra)`，你可以对比原来sql的失败个数和重试sql的成功个数，一般都能对上。

#### Q：如何让一个请求中的所有SQL都走写库，或者如何处理`先写后读`的场景？
A: zebra开放出了两个接口来进行处理：

    /**
     * 使用本地的context来设置走主库，该方式只会影响本应用内本次请求的所有sql走主库，不会影响到piegon后端服务。
     * 调用过该方法后，一定要在请求的末尾调用clearLocalContext进行清理操作。
     * 优先级比forceMasterInPiegonContext低。
     */
    ZebraForceMasterHelper.forceMasterInLocalContext();

    /**
     * 通过使用piegon的context来设置走主库，该方式将会透传到后端应用，使后端应用同样走主库，使用该方式请慎用。
     * 因为使用piegon的context,所以piegon会自动的对该context进行清理，故调用完该方法后，无需进行清理动作。
     * forceMasterInPiegonContext比forceMasterInLocalContext有更高的优先级。
     */
    ZebraForceMasterHelper.forceMasterInPiegonContext();

    /**
     * 配合forceMasterInLocalContext进行使用，在请求的末尾调用该方法，对LocalContext进行清理。
     */
    ZebraForceMasterHelper.clearLocalContext();

#### Q：如何指定让具体某条SQL走写库？
A: 可以在SQL前面加一个`hint`，表明这个读请求强制走写库，其中, `/*+zebra:w*/`就是hint的格式，告诉zebra这条sql必须走写库。

    /*+zebra:w*/select * from test

#### Q：为什么会遇到这样的异常：java.sql.SQLException: An attempt by a client to checkout a Connection has timed out. 
A：这个错误是c3p0报出来的错误，表明c3p0尝试去连接池中拿连接超时了。一般遇到这样的错误，可以有以下几个角度去解决：
    1. 是否是因为并发量太大的原因？如果是，请调整c3p0的参数，比如可以增加连接数，将maxIdleTime设置为0。
    2. 是否当时有慢查询，导致数据库拥堵？如果是，请联系DBA抓取慢查询，对sql进行优化。
    3. 是否当时网络有问题，比如网络拥堵了一下？如果是，请联系DBA排查。

#### Q：什么是数据源自动替换？
A：为了方便升级，不用业务修改代码，zebra可以对数据库级别对数据源进行动态替换。替换的技术是Spring加载完bean的时候对DataSource这个类型的bean进行替换。过程如下：
    1. 从datasource中获取jdbcUrl，从而知道是该datasource会访问哪个库
    2. 判断该数据库是否在`Lion`的白名单`groupds.autoreplace.database`配置过
    3. 如果配置过，则进行替换。替换时，判断用户名如果是读用户，则替换过的GroupDataSource只能读；如果是写用户，则替换过的GroupDataSource只能写。
替换的DataSource仅限于c3p0和dpdl两种数据源。

#### Q：如何查看版本信息？
A: Release Note [ReleaseNote.md](/arch/zebra/blob/master/ReleaseNote.md)