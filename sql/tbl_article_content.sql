-- 文章内容表
CREATE TABLE `tbl_article_content` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `article_id` bigint(40) NOT NULL COMMENT '对应文章ID',
  `create_by` datetime NOT NULL COMMENT '创建时间',
  `modifield_by` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;