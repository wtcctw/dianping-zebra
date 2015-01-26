执行步骤：
1. 调用`ShardDataSource`得到`ShardConnection`
2. 调用`ShardConnection`得到`ShardStatement`或者`ShardPreparedStatement`
3. 调用`*Statement`的`execute`方法
4. 根据规则和`sql`语句判断目标数据库
5. 根据规则和`sql`语句分割`sql`
6. 如果是查询的话，到目标数据库执行查询，并合并查询结果
7. 如果是数据操作的话，更新主表，并一步更新从表

上线步骤：
1. 配置分库分表策略
2. 配置两个参数，`EnableShardQuery`和`EnbableShardSync`，默认全部先关闭
3. DBA 配置好相关数据库
4. 打开`EnbableShardSync`，同步完成后打开`EnableShardQuery`

变更步骤：
1. 关闭`EnableShardQuery`和`EnbableShardSync`
2. 修改配置分库分表策略
3. DBA 修改好相关的数据库
4. 打开`EnbableShardSync`，同步完成后打开`EnableShardQuery`

多维度分库分表更新逻辑：
1. 将`sql`缓存到文件队列中
2. 更新主表
3. 异步更新附属表，全部完成后删除队列
4. 异步定时检查队列，发现有长时间（多长？）未执行的就重新执行