##  更新说明
### zebra-dao 0.1.5
* [+] 增加了分页的支持，包括物理分页和逻辑分页

### zebra-api 2.7.10
* [+] 读写分离的路由规则支持了多IDC的支持
* [+] 提供分库分表的路由api

### zebra-api 2.7.9
* [+] 增加了GroupDataSource的安全访问检测
* [/] 修正了若干日志的输出

### zebra-api 2.7.8
* [+] 增加了规则中多Column的支持
* [/] 修正了若干bug

### zebra-dao 0.1.0
* [+] 异步dao的支持
* [+] cat监控的支持

### 2.7.7
* [/] 重构了分库分表的部分代码，修正了分库分表的若干bug
* [+] 增加了GroupDataSource的socketTimeout的设置功能

### 2.7.6
* [+] 增加底层对tomcat-jdbc的支持
* [+] 增加配置支持GroupDataSource可以选择底层用c3p0还是tomcat-jdbc

### 2.7.5
* [/] 修正了无法正确关闭写库连接的bug
* [+] 增加了在写库切换时取不到连接时打点到CAT
* [/] 修正了zebra-ds-monitor-client上报数据时版本上报错误的bug
* [+] 增加流控日志输出

### 2.7.4
* [+] 增加对`先写后读`场景的API支持
* [+] 增加对读库进行的连接检测
* [+] 增加日志文件输出到/data/applogs/zebra
* [/] 修正了zebra-ds-monitor-client上报数据的逻辑
* [/] 修正了GroupDataSource在close后仍然响应Lion的配置变更的推送的bug
* [/] 修正了zebra-ds-monitor-client不能连接hang住的bug
* [/] 修正了refresh配置时datasource会泄露的bug

### 2.7.2
* [/] 修正了不能加载DpdlReadWriteStrategy
* [/] 修正了retry逻辑的cat打点问题

### 2.7.1
* [/] 删除了配置变更需要线程池 
* [/] 补回了StringUtils这个类，并把这个类设置成@Deprecated

### 2.7.0
* [+] 支持了分库分表
* [/] 客户端新增支持按百分比进行SQL流控功能
* [+] 客户端新增支持对于非事务的读连接自动的重试，如果2个以上读库，那么一旦一台读库挂了，CAT虽然报错，但并不影响业务，因为SQL会被重试执行，重试的SQL能在CAT中看到
* [/] 客户端修正取连接失败后CAT打点丢失信息的bug
* [/] 客户端强制使用c3p0的参数checkoutTimeout=1000
* [/] 客户端修正了若干线程安全的bug

### 2.6.7
* [-] 删除了DAL中无用的配置
* [+] 添加了forceWriteOnLogin这个配置项来关闭登录用户走写库的逻辑
* [/] 修正了SQL的CAT打点丢失Exception的StackTrace的bug
* [/] 修正SQL黑名单只对PreparedStatement进行拦截，不再对Statement进行拦截

### 2.6.4
* [/] 修正了zebra-ds-monitor-client的若干bug

### 2.6.3
* [+] 增加了`filter`功能
* [+] 利用`filter`，增加了SQL黑名单功能
* [/] 利用`filter`，重构了`CAT`监控的代码

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

### 2.5.0
  * [+] 支持`Spring`方式配置`GroupDataSource`
  * [+] 支持`Spring`方式配置`SingleDataSource`
  * [+] 通过升级`zebra-ds-monitor`,老应用自动替换`ComboPooledDataSource`到`SingleDataSource`
  * [+] 两种`DataSource`均支持配置动态刷新

### 2.4.8
  * [*] 重构`FailOverDataSource`，检测逻辑更合理
  * [+] 兼容老版本`avatar`强制读写库的逻辑，业务方不需要修改相关逻辑代码

### 2.4.7
  * [*] 修复`iBatis`中使用`SelectKey`后无法获得主键的Bug
