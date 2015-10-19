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
		<version>0.0.1</version>
	</dependency>
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

在spring的xml文件中配置一个如下的bean：

	<bean class="com.dianping.zebra.dao.mybatis.ZebraMapperScannerConfigurer">
        <property name="basePackage" value="com.dianping.zebra.dao.mapper" />
        <!--可不配，默认值为10 -->
        <property name="initPoolSize" value="10"></property>
        <!--可不配，默认值为20 -->
        <property name="maxPoolSize" value="20"></property>
    </bean>

因为zebra-dao背后异步的实现方式，是使用线程池执行的方式的，所以需要在这里设置线程池的大小。
    
## 如何使用
### Callback的方式
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


### Future的方式
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


### 常见问题
TODO
Q:如何Handle异常
















 
    