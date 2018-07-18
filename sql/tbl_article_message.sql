--文章评论表
CREATE TABLE `tbl_article_message` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(40) NOT NULL COMMENT '文章ID',
  `message_id` bigint(40) NOT NULL COMMENT '对应的留言ID',
  `create_by` datetime NOT NULL COMMENT '创建时间',
  `is_effective` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效，默认为1有效，置0无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;