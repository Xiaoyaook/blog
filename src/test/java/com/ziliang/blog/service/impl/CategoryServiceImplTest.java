package com.ziliang.blog.service.impl;

import com.ziliang.blog.BlogApplication;
import com.ziliang.blog.entity.CategoryInfo;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class CategoryServiceImplTest {

    @Autowired
    CategoryServiceImpl categoryService;

    @Ignore
    @Test
    public void addCategoryTest() {
        CategoryInfo categoryInfo = new CategoryInfo();
        categoryInfo.setName("Python");
        categoryService.addCategory(categoryInfo);
        Assert.assertThat(categoryInfo.getId(), IsEqual.equalTo(2L));
        // 插入数据后，可以返回主键的值,与我们mapper中keyProperty有关，这里要注意的是我们的主键类型为Long，判断相等需要加L
    }

    @Ignore
    @Test
    public void deleteCategoryById() {
    }

    @Ignore
    @Test
    public void updateArticleCategory() {
    }

    @Ignore
    @Test
    public void updateCategoryTest() {
        CategoryInfo categoryInfo = new CategoryInfo();
        categoryInfo.setId(1L);
        categoryInfo.setName("JavaScript");
        categoryService.updateCategory(categoryInfo);
        categoryInfo = categoryService.getOneById(1L);
        Assert.assertThat(categoryInfo.getName(), is("JavaScript"));
    }

    @Ignore
    @Test
    public void getOneByIdTest() {
        CategoryInfo categoryInfo = categoryService.getOneById(1L);
        Assert.assertThat(categoryInfo.getName(), is("Java"));
    }

    @Ignore
    @Test
    public void listAllCategoryTest() {
        List<CategoryInfo> categoryInfos = categoryService.listAllCategory();
        for (CategoryInfo categoryInfo: categoryInfos) {
            System.out.println(categoryInfo);
        }

    }

    @Ignore
    @Test
    public void getCategoryByArticleId() {
    }
}