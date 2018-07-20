package com.ziliang.blog.service;


import com.ziliang.blog.dto.ArticleDto;
import com.ziliang.blog.dto.ArticleWithPictureDto;
import com.ziliang.blog.entity.ArticlePicture;

import java.util.List;

/**
 * 文章Service
 * 说明：ArticleInfo里面封装了picture/content/category等信息
 */
public interface ArticleService {

    void addArticle(ArticleDto articleDto);

    void deleteArticleById(Long id);

    void updateArticle(ArticleDto articleDto);

    void updateArticleCategory(Long articleId, Long categoryId);

    ArticleDto getOneById(Long id);

    ArticlePicture getPictureByArticleId(Long id);

    List<ArticleWithPictureDto> listAll();

    List<ArticleWithPictureDto> listByCategoryId(Long id);

    List<ArticleWithPictureDto> listLastest();
}
