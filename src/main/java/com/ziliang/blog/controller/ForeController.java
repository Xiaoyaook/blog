package com.ziliang.blog.controller;

import com.ziliang.blog.dto.ArticleDto;
import com.ziliang.blog.dto.ArticleWithPictureDto;
import com.ziliang.blog.entity.CategoryInfo;
import com.ziliang.blog.result.Result;
import com.ziliang.blog.service.ArticleService;
import com.ziliang.blog.service.CategoryService;
import com.ziliang.blog.util.Markdown2HtmlUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 前台Controller
 *
 */
@Api(value = "前台controller", tags = {"展示页面"})
@RestController
@RequestMapping("/api")
public class ForeController {

    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;

    /**
     * 获取所有文章列表
     *
     * @return
     */
    @ApiOperation("获取所有文章")
    @GetMapping("article/list")
    public Result<List<ArticleWithPictureDto>> listAllArticleInfo() {
        return Result.success(articleService.listAll());
    }

    /**
     * 获取某一个分类下的所有文章
     *
     * @param id
     * @return
     */
    @ApiOperation("获取某一个分类下的所有文章")
    @ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "Long")
    @GetMapping("article/list/sort/{id}")
    public Result<List<ArticleWithPictureDto>> listArticleInfo(@PathVariable Long id) {
        return Result.success(articleService.listByCategoryId(id));
    }

    /**
     * 获取最新的文章
     *
     * @return
     */
    @ApiOperation("获取最新的几篇文章")
    @GetMapping("article/list/lastest")
    public Result<List<ArticleWithPictureDto>> listLastestArticle() {
        return Result.success(articleService.listLastest());
    }

    /**
     * 通过文章的ID获取对应的文章信息
     *
     * @param id
     * @return 自己封装好的文章信息类
     */
    @ApiOperation("通过文章ID获取文章信息")
    @GetMapping("article/{id}")
    public Result<ArticleDto> getArticleById(@PathVariable Long id) {
        ArticleDto articleDto = articleService.getOneById(id);
        articleDto.setContent(Markdown2HtmlUtil.markdown2html(articleDto.getContent()));
        return Result.success(articleDto);
    }

    /**
     * 获取所有分类信息
     *
     * @return
     */
    @ApiOperation("获取所有分类信息")
    @GetMapping("category/list")
    public Result<List<CategoryInfo>> listAllCategoryInfo() {
        return Result.success(categoryService.listAllCategory());
    }
}
