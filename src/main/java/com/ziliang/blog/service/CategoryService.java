package com.ziliang.blog.service;

import com.ziliang.blog.dto.ArticleCategoryDto;
import com.ziliang.blog.entity.ArticleCategory;
import com.ziliang.blog.entity.CategoryInfo;

import java.util.List;

/**
 * 分类Service
 */
public interface CategoryService {
    void addCategory(CategoryInfo categoryInfo);

    void deleteCategoryById(Long id);

    void updateCategory(CategoryInfo categoryInfo);

    void updateArticleCategory(ArticleCategory articleCategory);

    CategoryInfo getOneById(Long id);

    List<CategoryInfo> listAllCategory();

    ArticleCategoryDto getCategoryByArticleId(Long id);
}
