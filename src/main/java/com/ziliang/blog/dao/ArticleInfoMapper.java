package com.ziliang.blog.dao;


import com.ziliang.blog.entity.ArticleInfo;

import java.util.List;

public interface ArticleInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleInfo record);

    int insertSelective(ArticleInfo record);

    ArticleInfo selectByPrimaryKey(Long id);

    List<ArticleInfo> selectAllArticleInfo();

    int updateByPrimaryKeySelective(ArticleInfo record);

    int updateByPrimaryKey(ArticleInfo record);
}
