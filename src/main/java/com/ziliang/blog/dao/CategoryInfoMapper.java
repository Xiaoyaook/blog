package com.ziliang.blog.dao;


import com.ziliang.blog.entity.CategoryInfo;

import java.util.List;

public interface CategoryInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CategoryInfo record);

    int insertSelective(CategoryInfo record);

    CategoryInfo selectByPrimaryKey(Long id);

    List<CategoryInfo> selectAllCategoryInfo();

    int updateByPrimaryKeySelective(CategoryInfo record);

    int updateByPrimaryKey(CategoryInfo record);
}
