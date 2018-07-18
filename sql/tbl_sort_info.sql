-- 分类信息表
CREATE TABLE `tbl_sort_info` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '分类名称',
  `number` tinyint(10) NOT NULL DEFAULT '0' COMMENT '该分类下的文章数量',
  `create_by` datetime NOT NULL COMMENT '分类创建时间',
  `modified_by` datetime NOT NULL COMMENT '分类修改时间',
  `is_effective` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效，默认为1有效，为0无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;