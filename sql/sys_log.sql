--日志表
CREATE TABLE `sys_log` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ip` varchar(20) NOT NULL DEFAULT '' COMMENT '操作地址的IP',
  `create_by` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '操作内容',
  `operate_url` varchar(100) NOT NULL DEFAULT '' COMMENT '操作的访问地址',
  `operate_by` varchar(100) DEFAULT '' COMMENT '操作的浏览器',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SELECT DATE_FORMAT( create_by, "%Y-%m-%d" ) , COUNT( id )
FROM sys_log
GROUP BY DATE_FORMAT( create_by, "%Y-%m-%d" );
