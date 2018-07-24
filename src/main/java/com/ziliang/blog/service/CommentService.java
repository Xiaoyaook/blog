package com.ziliang.blog.service;

import com.ziliang.blog.dto.ArticleCommentDto;
import com.ziliang.blog.entity.Comment;

import java.util.List;

/**
 * 留言的Service
 */
public interface CommentService {
    int addComment(Comment comment);

    void addArticleComment(ArticleCommentDto articleCommentDto);

    void deleteCommentById(Long id);

    void deleteArticleCommentById(Long id);

    List<Comment> listAllComment();

    List<ArticleCommentDto> listAllArticleCommentById(Long id);
}
