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