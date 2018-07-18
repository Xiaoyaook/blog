-- 浏览量表
CREATE TABLE `sys_view` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `ip` varchar(20) NOT NULL COMMENT '访问IP',
  `create_by` datetime NOT NULL COMMENT '访问时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;