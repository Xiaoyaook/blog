-- 留言/评论表
CREATE TABLE `tbl_message` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(200) NOT NULL DEFAULT '' COMMENT '留言/评论内容',
  `create_by` datetime NOT NULL COMMENT '创建日期',
  `email` varchar(20) NOT NULL DEFAULT '' COMMENT '邮箱，用于回复消息',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '用户自己定义的名称',
  `ip` varchar(20) NOT NULL DEFAULT '' COMMENT '留言/评论IP',
  `is_effective` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效，默认为1为有效，0为无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='因为message分为两种，一种是留言，一种是评论，这里搞成一张表是因为它们几乎是拥有相同的字段，我觉得没必要分成两张表来进行维护';