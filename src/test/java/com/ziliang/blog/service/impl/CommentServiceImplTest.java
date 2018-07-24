package com.ziliang.blog.service.impl;

import com.ziliang.blog.BlogApplication;
import com.ziliang.blog.dto.ArticleCommentDto;
import com.ziliang.blog.entity.Comment;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class CommentServiceImplTest {

    @Autowired
    CommentServiceImpl commentService;

    @Ignore
    @Test
    public void addCommentTest() {
        Comment comment = new Comment();
        comment.setEmail("599319698@qq.com");
        comment.setName("xiaoyaook");
        comment.setIp("127.0.0.1");
        comment.setContent("第3条comment");
        commentService.addComment(comment);
    }

    @Ignore
    @Test
    public void addArticleCommentTest() {
        ArticleCommentDto articleCommentDto = new ArticleCommentDto();
        articleCommentDto.setEmail("599319698@qq.com");
        articleCommentDto.setName("xiaoyaook");
        articleCommentDto.setIp("127.0.0.1");
        articleCommentDto.setContent("第2条comment");

        articleCommentDto.setArticleId(9L);

        commentService.addArticleComment(articleCommentDto);
    }

    @Ignore
    @Test
    public void deleteCommentByIdTest() {
        commentService.deleteCommentById(1L);
    }

    @Ignore
    @Test
    public void deleteArticleCommentByIdTest() {
        commentService.deleteArticleCommentById(1L);
    }

    @Ignore
    @Test
    public void listAllCommentTest() {
        List<Comment> comments = commentService.listAllComment();
        for (Comment comment: comments) {
            System.out.println(comment);
        }
    }

    @Ignore
    @Test
    public void listAllArticleCommentById() {
        commentService.listAllArticleCommentById(9L);
    }
}