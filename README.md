## Zebra 
`Zebra`是点评内部使用数据源、DAO以及监控等和数据库打交道的中间件集。

### Documents
#### zebra-api
是一个动态数据源，具有读写分离和分库分表等功能。

用户文档请看这里：[User Guide](/arch/zebra/blob/master/zebra-api/README.md)
#### zebra-dao
是一个`异步`DAO，在MyBatis基础上封装而成。

用户文档请看这里：[User Guide](/arch/zebra/blob/master/zebra-dao/README.md)

#### zebra-ds-monitor-client
具备SQL监控的功能，打点到CAT上，配合zebra-api一起使用

### Latest Version
	<dependency>
		<groupId>com.dianping.zebra</groupId>
		<artifactId>zebra-api</artifactId>
		<version>2.7.9</version>
	</dependency>
	
	<dependency>
		<groupId>com.dianping.zebra</groupId>
		<artifactId>zebra-ds-monitor-client</artifactId>
		<version>2.7.9</version>
	</dependency>
	
	<dependency>
		<groupId>com.dianping.zebra</groupId>
		<artifactId>zebra-dao</artifactId>
		<version>0.1.2</version>
	</dependency>
	

### Release Notes
[ReleaseNote](/arch/zebra/blob/master/ReleaseNote.md)

