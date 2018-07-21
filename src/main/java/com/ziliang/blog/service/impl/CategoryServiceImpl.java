package com.ziliang.blog.service.impl;


import com.ziliang.blog.dao.ArticleCategoryMapper;
import com.ziliang.blog.dao.CategoryInfoMapper;
import com.ziliang.blog.dto.ArticleCategoryDto;
import com.ziliang.blog.entity.ArticleCategory;
import com.ziliang.blog.entity.CategoryInfo;
import com.ziliang.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryInfoMapper categoryInfoMapper;
    @Autowired
    ArticleCategoryMapper articleCategoryMapper;

    /**
     * 增加一条分类数据
     *
     * @param categoryInfo
     */
    @Override
    public void addCategory(CategoryInfo categoryInfo) {
        categoryInfoMapper.insertSelective(categoryInfo);
    }

    /**
     * 通过分类id删除分类信息，要对应删除两个表的内容
     *
     * @param id 分类ID
     */
    @Override
    @Transactional
    public void deleteCategoryById(Long id) {
        // 先删除ArticleCategory表中的相关内容
        List<ArticleCategory> categories = articleCategoryMapper.selectAllArticleCategoryByCategoryId(id);
        for (ArticleCategory articleCategory : categories) {
            articleCategoryMapper.deleteByPrimaryKey(articleCategory.getId());
        }
        // 再删除CategoryInfo表中的内容
        categoryInfoMapper.deleteByPrimaryKey(id);
    }


    /**
     * 更改文章对应的分类
     *
     * @param articleCategory
     */
    @Override
    public void updateArticleCategory(ArticleCategory articleCategory) {
        articleCategoryMapper.updateByPrimaryKeySelective(articleCategory);
    }

    /**
     * 更新分类信息
     *
     * @param categoryInfo
     */
    @Override
    public void updateCategory(CategoryInfo categoryInfo) {
        categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
    }

    /**
     * 获取一条分类信息数据
     *
     * @param id
     * @return
     */
    @Override
    public CategoryInfo getOneById(Long id) {
        CategoryInfo categoryInfo = categoryInfoMapper.selectByPrimaryKey(id);
        return categoryInfo;
    }

    /**
     * 列举返回所有的分类信息
     *
     * @return
     */
    @Override
    public List<CategoryInfo> listAllCategory() {
        // 无条件查询即返回所有
        return categoryInfoMapper.selectAllCategoryInfo();
    }

    /**
     * 通过文章ID获取某一篇文章对应的分类
     *
     * @param id 文章ID
     * @return
     */
    @Override
    public ArticleCategoryDto getCategoryByArticleId(Long id) {
        ArticleCategoryDto articleCategoryDto = new ArticleCategoryDto();
        // 填充article_category中的基础数据
        ArticleCategory articleCategory = articleCategoryMapper.selectArticleCategoryByArticleId(id);
        articleCategoryDto.setArticleId(articleCategory.getArticleId());
        articleCategoryDto.setId(articleCategory.getId());
        articleCategoryDto.setCategoryId(articleCategory.getCategoryId());
        // 填充对应的分类信息
        CategoryInfo categoryInfo = getOneById(articleCategory.getCategoryId());
        articleCategoryDto.setName(categoryInfo.getName());
        articleCategoryDto.setNumber(categoryInfo.getNumber());
        return articleCategoryDto;
    }

}
