--文章分类表
CREATE TABLE `tbl_article_sort` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `sort_id` bigint(40) NOT NULL COMMENT '分类id',
  `article_id` bigint(40) NOT NULL COMMENT '文章id',
  `create_by` datetime NOT NULL COMMENT '创建时间',
  `modified_by` datetime NOT NULL COMMENT '更新时间',
  `is_effective` tinyint(1) DEFAULT '1' COMMENT '表示当前数据是否有效，默认为1有效，0则无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;