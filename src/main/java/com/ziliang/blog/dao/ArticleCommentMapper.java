package com.ziliang.blog.dao;


import com.ziliang.blog.entity.ArticleComment;

import java.util.List;

public interface ArticleCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleComment record);

    int insertSelective(ArticleComment record);

    ArticleComment selectByPrimaryKey(Long id);

    List<ArticleComment> selectAllArticleComment(Long articleId);

    int updateByPrimaryKeySelective(ArticleComment record);

    int updateByPrimaryKey(ArticleComment record);
}
