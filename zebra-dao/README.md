# zebra-dao

## 简介
`zebra-dao`是在`mybatis`基础上进一步封装的`异步数据源`,它有以下的功能点：

1. 实现`回调`和`Future`两种方式的异步dao
2. 集成CAT，可以在CAT中看到SQL打点
3. 使用方式和mybatis一致，有关mybati的使用问题请参考文档https://mybatis.github.io/mybatis-3/zh/

## 如何配置
配置pom，引入zebra-dao和其他zebra组件

	<dependency>
		<groupId>com.dianping.zebra</groupId>
		<artifactId>zebra-dao</artifactId>
		<version>0.1.0-SNAPSHOT</version>
	</dependency>
	
	<!--Zebra数据源相关依赖，必须要2.7.8以上-->
	<dependency>
		<groupId>com.dianping.zebra</groupId>
		<artifactId>zebra-api</artifactId>
		<version>2.7.8</version>
	</dependency>
	<dependency>
		<groupId>com.dianping.zebra</groupId>
		<artifactId>zebra-ds-monitor-client</artifactId>
		<version>2.7.8</version>
	</dependency>
	
	<!--Spring 相关依赖，请指定版本-->
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-context</artifactId>
		<version>${spring.version}</version>
	</dependency>
	<dependency>
		<groupId>org.springframework</groupId>
		<artifactId>spring-jdbc</artifactId>
		<version>${spring.version}</version>
	</dependency>

在spring的appcontext-dao.xml文件中配置一个如下的bean。因为zebra-dao背后异步的实现方式，是使用线程池执行的方式的，所以需要在这里设置线程池的大小。该部分配置与原生的mybatis有差异，也是唯一差异的地方。

	<bean class="com.dianping.zebra.dao.mybatis.ZebraMapperScannerConfigurer">
        <property name="basePackage" value="com.dianping.zebra.dao.mapper" />
        <!--可不配，默认值为20,支持配置在lion中并动态刷新 -->
        <property name="initPoolSize" value="20"></property>
        <!--可不配，默认值为200，支持配置在lion中并动态刷新 -->
        <property name="maxPoolSize" value="200"></property>
        <!--可不配，默认值为500，支持配置在lion中，但不能够动态刷新 -->
        <property name="queueSize" value="500"></property>
    </bean>


在spring的appcontext-db.xml文件中配置一个如下的bean，该部分配置和原生的mybatis一致，如果不明白，请参考mybatis使用的相关文档。
		
	<bean id="datasource" class="com.dianping.zebra.group.jdbc.GroupDataSource"
		init-method="init">
		<property name="jdbcRef" value="你需访问数据库的jdbcRef" />
	</bean>

	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<!--指定数据源-->
		<property name="dataSource" ref="datasource"/>
		<!--指定Mapper文件地址，需要改写-->
		<property name="mapperLocations" value="classpath*:config/sqlmap/**/*.xml" />
		<!--指定entity的package,需要改写-->
		<property name="typeAliasesPackage" value="com.dianping.zebra.dao.entity" />
	</bean>

	<!--事务管理器，如不需要，可不定义-->
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="datasource" />
	</bean>

    
## 如何使用

### 同步访问的使用方式
就是mybatis的使用方式，如不清楚，请参考mybatis的相关文档~

### 异步访问的Callback方式
1. 举例来说，在UserMapper中，有一个同步方法`findUserById`,如果想要有异步的通过回调方式的接口，则可以增加一个相同方法名，并且参数列表中多一个`AsyncDaoCallback`的参数的方法。

		public interface UserMapper {
			/**
	 		*正常的Mapper Class的同步写法
	 		*/
			public UserEntity findUserById(@Param("userId") int userId);
			
			/**
	 		*Zebra-dao支持的异步回调写法，返回值为void，仅支持一个回调方法
	 		*/
			public void findUserById(@Param("userId") int userId, AsyncDaoCallback<UserEntity> callback);
		}

2. 在业务代码中使用`UserMapper`时，需要在调用时实现自己的`AsyncDaoCallback`，例如：

		@Autowired
		private UserMapper dao;
		
		......
		
		dao.findUserById(1, new AsyncDaoCallback<UserEntity>() {
			@Override
			public void onSuccess(UserEntity user) {
				System.out.println("current " + System.currentTimeMillis());
				System.out.println(user);
				
				dao.findUserById(2, new AsyncDaoCallback<UserEntity>() {
					
					@Override
					public void onSuccess(UserEntity user) {
						System.out.println("current " + System.currentTimeMillis());
						System.out.println(user);
					}

					@Override
               		public void onException(Exception e) {
               		}
				});
				
				UserEntity entity = dao.findUserById(3);
				System.out.println(entity);
			}

			@Override
         	public void onException(Exception e) {
         	}
		});


### 异步访问的Future方式
1. 举例来说，在UserMapper中，有一个同步方法`getAll`,如果想要有异步的通过`Future`方式的接口，则可以增加一个方法，并使用`@TargetMethod`指定新方法需要对应到哪一个同步方法。

		public interface UserMapper {
			/**
	 		*正常的Mapper Class的同步写法
	 		*/
			public List<UserEntity> getAll();

			/**
	 		*Zebra-dao支持的异步回调写法，返回值为Future。需要通过annotation指定原始的同步方法名是哪一个
	 		*/
			@TargetMethod(name = "getAll")
			public Future<List<UserEntity>> getAll1();
		}

2.在业务代码中使用`UserMapper`时，需要通过Future接口获取到返回值，例如：

		@Autowired
		private UserMapper dao;
		
		......

		Future<List<UserEntity>> future = dao.getAll1();
		List<UserEntity> list = future.get();
		
		for(UserEntity user : list){
			System.out.println(user);
		}

### 监控
在CAT的heartbeat中，可以看到有关线程池的信息，heartbeat是CAT每分钟的采样，目前对线程池采样了3个指标：

1. ActiveCount ： 表明当前活跃的线程数。
2. PoolSize ： 表明当前总的线程数。
3. QueueSize ：表明当前队列的大小。一般情况下应当为0，如果采样都能发现QueueSize长时间很大，可以考虑调整线程池的大小。

### 常见问题
TODO
Q:如何Handle异常
















 
    