CREATE database zebra;

CREATE TABLE `heartbeat` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `app_name` varchar(50) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `datasource_bean_name` varchar(50) DEFAULT NULL,
  `database` varchar(50) DEFAULT NULL,
  `database_type` varchar(20) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `datasource_bean_class` varchar(50) DEFAULT NULL,
  `replaced` tinyint(2) DEFAULT NULL,
  `jdbc_url` varchar(200) DEFAULT NULL,
  `init_pool_size` int(11) DEFAULT NULL,
  `max_pool_size` int(11) DEFAULT NULL,
  `min_pool_size` int(11) DEFAULT NULL,
  `version` varchar(20) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

CREATE TABLE `flowcontrol` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sqlId` varchar(11) NOT NULL DEFAULT '',
  `app` varchar(100) NOT NULL DEFAULT '',
  `sql` varchar(256) DEFAULT '',
  `env` varchar(11) NOT NULL,
  `isDelete` tinyint(11) NOT NULL DEFAULT '0' COMMENT '0 is active,1 is deleted',
  `createTime` timestamp NULL DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `monitor_history` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `dsId` varchar(50) DEFAULT NULL,
  `operator` tinyint(11) DEFAULT NULL COMMENT '0 for markup, -1 for mark down',
  `createTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

CREATE TABLE `ShardDumpTask` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Executor` varchar(45) NOT NULL,
  `SrcDbName` varchar(45) NOT NULL,
  `DstDbName` varchar(45) NOT NULL,
  `DataBase` varchar(45) NOT NULL,
  `TargetDataBase` varchar(45) NOT NULL,
  `TableName` varchar(45) NOT NULL,
  `TargetTableName` varchar(45) NOT NULL,
  `ShardRule` varchar(45) NOT NULL,
  `IndexColumnName` varchar(45) NOT NULL,
  `IndexKey` BIGINT(64) NOT NULL,
  `MaxKey` BIGINT(64) NOT NULL,
  `BinlogFile` VARCHAR(45) NULL,
  `BinlogPos` BIGINT(64) NOT NULL DEFAULT 0,
  `Status` VARCHAR(100) NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ShardDumpDb` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Host` varchar(100) NOT NULL,
  `Port` int(11) NOT NULL,
  `Username` varchar(45) NOT NULL,
  `Password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `ShardMigrateProcess` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `InitFinish` tinyint(1) NOT NULL DEFAULT '0',
  `DumpFinish` tinyint(1) NOT NULL DEFAULT '0',
  `SyncCreateFinish` tinyint(1) NOT NULL DEFAULT '0',
  `CatchUpFinish` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `SyncServerMonitor` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Load` double NOT NULL,
  `UpdateTime` datetime NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `ShardSyncTask` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `PumaServerHost` varchar(45) NOT NULL,
  `PumaServerPort` int(11) NOT NULL,
  `PumaTaskName` varchar(45) NOT NULL,
  `Executor` varchar(45) NOT NULL,
  `RuleName` varchar(45) NOT NULL,
  `TableName` varchar(45) NOT NULL,
  `Type` int(11) NOT NULL,
  `BinlogName` varchar(45) DEFAULT NULL,
  `BinlogPos` bigint(64) DEFAULT NULL,
  `SeqTimestamp` bigint(64) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `puma_task` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ruleName` varchar(128) DEFAULT NULL,
  `table` varchar(128) DEFAULT NULL,
  `dbRule` varchar(512) DEFAULT NULL,
  `dbIndexes` varchar(512) DEFAULT NULL,
  `tbRule` varchar(512) DEFAULT NULL,
  `tbSuffix` varchar(512) DEFAULT NULL,
  `pumaTaskName` varchar(256) DEFAULT NULL,
  `pumaDatabase` varchar(256) DEFAULT NULL,
  `pumaTables` varchar(1024) DEFAULT NULL,
  `executor` varchar(128) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT NULL,
  `updateTime` timestamp NULL DEFAULT NULL,
  `sequence` bigint(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
