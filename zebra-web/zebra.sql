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
