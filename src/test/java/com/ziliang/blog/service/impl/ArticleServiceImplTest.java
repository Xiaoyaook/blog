package com.ziliang.blog.service.impl;

import com.ziliang.blog.BlogApplication;
import com.ziliang.blog.dto.ArticleDto;
import com.ziliang.blog.dto.ArticleWithPictureDto;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class ArticleServiceImplTest {

    @Autowired
    ArticleServiceImpl articleService;

    @Ignore
    @Test
    public void addArticleTest() {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setTitle("t");
        articleDto.setSummary("tttt");

        articleDto.setPictureUrl("./img/t.jpg");

        articleDto.setContent("aaaaaaaaaaa");

        articleDto.setCategoryId(1L);

        articleService.addArticle(articleDto);
    }

    @Ignore
    @Test
    public void deleteArticleByIdTest() {
        articleService.deleteArticleById(1L);
    }

    @Ignore
    @Test
    public void updateArticleCategoryTest() {
        articleService.updateArticleCategory(1L,2L);
    }

    @Ignore
    @Test
    public void updateArticleTest() {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(1L);
        articleDto.setTitle("t");
        articleDto.setSummary("tttt");
        articleDto.setArticlePictureId(1L);
        articleDto.setPictureUrl("./img/t.jpg");
        articleDto.setArticleContentId(1L);
        articleDto.setContent("123542345");
        articleDto.setArticleCategoryId(1L);
        articleDto.setCategoryId(1L);

        articleService.updateArticle(articleDto);
    }

    @Ignore
    @Test
    public void getOneByIdTest() {
        ArticleDto articleDto = articleService.getOneById(1L);
        System.out.println(articleDto);
    }

    @Ignore
    @Test
    public void listAllTest() {
        List<ArticleWithPictureDto> articleWithPictureDtos = articleService.listAll();
        for (ArticleWithPictureDto articleWithPictureDto: articleWithPictureDtos) {
            System.out.println(articleWithPictureDto);
        }
    }

    @Ignore
    @Test
    public void listByCategoryIdTest() {
        List<ArticleWithPictureDto> articleWithPictureDtos = articleService.listByCategoryId(1L);
        for (ArticleWithPictureDto articleWithPictureDto: articleWithPictureDtos) {
            System.out.println(articleWithPictureDto);
        }
    }

    @Ignore
    @Test
    public void listLastestTest() {
        List<ArticleWithPictureDto> articleWithPictureDtos = articleService.listLastest();
        for (ArticleWithPictureDto articleWithPictureDto: articleWithPictureDtos) {
            System.out.println(articleWithPictureDto);
        }
    }

    @Ignore
    @Test
    public void getPictureByArticleIdTest() {
        System.out.println(articleService.getPictureByArticleId(1L));
    }
}