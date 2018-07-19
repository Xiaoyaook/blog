-- 文章题图表
CREATE TABLE `tbl_article_picture` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(40) NOT NULL COMMENT '对应文章id',
  `picture_url` varchar(100) NOT NULL DEFAULT '' COMMENT '图片url',
  `create_by` datetime NOT NULL COMMENT '创建时间',
  `modified_by` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='这张表用来保存题图url，每一篇文章都应该有题图';