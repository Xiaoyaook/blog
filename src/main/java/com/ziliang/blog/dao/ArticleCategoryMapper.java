package com.ziliang.blog.dao;


import com.ziliang.blog.entity.ArticleCategory;

import java.util.List;

public interface ArticleCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleCategory record);

    int insertSelective(ArticleCategory record);

    ArticleCategory selectArticleCategoryByArticleId(Long articleId);

    List<ArticleCategory> selectAllArticleCategoryByCategoryId(Long categoryId);

    ArticleCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleCategory record);

    int updateByPrimaryKey(ArticleCategory record);
}
