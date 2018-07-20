package com.ziliang.blog.dao;


import com.ziliang.blog.entity.ArticleContent;

import java.util.List;

public interface ArticleContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleContent record);

    int insertSelective(ArticleContent record);

    ArticleContent selectByPrimaryKey(Long id);

    ArticleContent selectByArticleId(Long ArticleId);

    int updateByPrimaryKeySelective(ArticleContent record);

    int updateByPrimaryKey(ArticleContent record);
}
