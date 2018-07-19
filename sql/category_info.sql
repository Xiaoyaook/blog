-- 分类信息表
CREATE TABLE `category_info` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '分类名称',
  `number` tinyint(10) NOT NULL DEFAULT '0' COMMENT '该分类下的文章数量',
  `create_by` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '分类创建时间',
  `modified_by` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '分类修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;