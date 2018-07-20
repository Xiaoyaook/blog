package com.ziliang.blog.controller;

import com.ziliang.blog.dto.ArticleDto;
import com.ziliang.blog.service.ArticleService;
import com.ziliang.blog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文章管理Controller
 *
 */
@Api(value = "文章管理controller", tags = {"文章管理"})
@RestController
@RequestMapping("/admin")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    /**
     * 增加一篇文章
     *
     * @return
     */
    @ApiOperation("增加一篇文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "文章标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "summary", value = "文章简介", required = true, dataType = "String"),
            @ApiImplicitParam(name = "isTop", value = "文章是否置顶", required = true, dataType = "Boolean"),
            @ApiImplicitParam(name = "categoryId", value = "文章分类对应ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "文章md源码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pictureUrl", value = "文章题图url", required = true, dataType = "String")
    })
    @PostMapping("article/")
    public String addArticle(@RequestBody ArticleDto articleDto) {
        articleService.addArticle(articleDto);
        return null;
    }

    /**
     * 删除一篇文章
     *
     * @param id
     * @return
     */
    @ApiOperation("删除一篇文章")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long")
    @DeleteMapping("article/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteArticleById(id);
        return null;
    }

    /**
     * 编辑/更新一篇文章
     *
     * @return
     */
    @ApiOperation("编辑/更新一篇文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "文章标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "summary", value = "文章简介", required = true, dataType = "String"),
            @ApiImplicitParam(name = "isTop", value = "文章是否置顶", required = true, dataType = "Boolean"),
            @ApiImplicitParam(name = "categoryId", value = "文章分类对应ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "文章md源码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pictureUrl", value = "文章题图url", required = true, dataType = "String")
    })
    @PutMapping("article/{id}")
    public String updateArticle(@PathVariable Long id, @RequestBody ArticleDto articleDto) {
        articleDto.setId(id);
        articleService.updateArticle(articleDto);
        return null;
    }

    /**
     * 改变某一篇文章的分类
     *
     * @param id
     * @return
     */
    @ApiOperation("改变文章分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "categoryId", value = "分类ID", required = true, dataType = "Long"),
    })
    @PutMapping("article/sort/{id}")
    public String changeArticleCategory(@PathVariable Long id, Long categoryId) {
        articleService.updateArticleCategory(id, categoryId);
        return null;
    }

    /**
     * 通过题图的id更改题图信息
     *
     * @param id
     * @return
     */
    @ApiOperation("更改文章题图信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "pictureUrl", value = "题图URL", required = true, dataType = "String")
    })
    @PutMapping("article/picture/{id}")
    public String updateArticlePicture(@PathVariable Long id, String pictureUrl) {
        ArticleDto articleDto = articleService.getOneById(id);
        articleDto.setPictureUrl(pictureUrl);
        articleService.updateArticle(articleDto);
        return null;
    }

    /**
     * 通过ID获取一篇文章，内容为MD源码格式
     *
     * @param id
     * @return
     */
    @ApiOperation("获取一篇文章，内容为md源码格式")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long")
    @GetMapping("article/{id}")
    public ArticleDto getArticleDtoById(@PathVariable Long id) {
        return articleService.getOneById(id);
    }
}
