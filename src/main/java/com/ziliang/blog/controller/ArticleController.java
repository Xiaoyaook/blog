package com.ziliang.blog.controller;

import com.ziliang.blog.dto.ArticleDto;
import com.ziliang.blog.dto.ArticleWithPictureDto;
import com.ziliang.blog.result.CodeMsg;
import com.ziliang.blog.result.Result;
import com.ziliang.blog.service.ArticleService;
import com.ziliang.blog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文章管理Controller
 *
 */
@Api(value = "文章管理controller", tags = {"文章管理"})
@CrossOrigin(origins = {"http://localhost:8081"}, allowCredentials = "true")
@RestController
// @RequestMapping("/admin") 所有后台操作Controller都使用/admin会出现Bug，全部去掉
public class ArticleController {

    private static Logger log = LoggerFactory.getLogger(ArticleController.class);

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
    @PostMapping(value = "/article")
    public Result<CodeMsg> addArticle(ArticleDto articleDto) {
        // 注意我们这里参数直接是ArticleDto， 没有用@RequestBody转化。
        // 我们前端post的数据类型是application/x-www-form-urlencoded，或者我们也可以发json
        // 但使用了@RequestBody后，都会抛出HttpMediaTypeNotSupportedException异常
        articleService.addArticle(articleDto);
        return Result.success(CodeMsg.ADD_ARTICLE_SUCCESS);
    }

    /**
     * 删除一篇文章
     *
     * @param id
     * @return
     */
    @ApiOperation("删除一篇文章")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long")
    @DeleteMapping("/article/{id}")
    public Result<CodeMsg> deleteArticle(@PathVariable Long id) {
        log.info("删除文章");
        articleService.deleteArticleById(id);
        return Result.success(CodeMsg.DELETE_ARTICLE_SUCCESS);
    }

    /**
     * 编辑/更新一篇文章
     *
     * @return
     */
    @ApiOperation("/编辑/更新一篇文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "文章标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "summary", value = "文章简介", required = true, dataType = "String"),
            @ApiImplicitParam(name = "isTop", value = "文章是否置顶", required = true, dataType = "Boolean"),
            @ApiImplicitParam(name = "categoryId", value = "文章分类对应ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "文章md源码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pictureUrl", value = "文章题图url", required = true, dataType = "String")
    })
    @PutMapping("/article/{id}")
    public Result<CodeMsg> updateArticle(@PathVariable Long id,ArticleDto articleDto) {
        articleDto.setId(id);
        articleService.updateArticle(articleDto);
        return Result.success(CodeMsg.UPDATE_ARTICLE_SUCCESS);
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
    @PutMapping("/article/category/{id}")
    public Result<CodeMsg> changeArticleCategory(@PathVariable Long id, Long categoryId) {
        articleService.updateArticleCategory(id, categoryId);
        return Result.success(CodeMsg.UPDATE_ARTICLE_CATEGORY_SUCCESS);
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
    @PutMapping("/article/picture/{id}")
    public Result<CodeMsg> updateArticlePicture(@PathVariable Long id, String pictureUrl) {
        ArticleDto articleDto = articleService.getOneById(id);
        articleDto.setPictureUrl(pictureUrl);
        articleService.updateArticle(articleDto);
        return Result.success(CodeMsg.UPDATE_ARTICLE_PICTURE_SUCCESS);
    }

    /**
     * 通过ID获取一篇文章，内容为MD源码格式
     *
     * @param id
     * @return
     */
    @ApiOperation("获取一篇文章，内容为md源码格式")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long")
    @GetMapping("/article/{id}")
    public Result<ArticleDto> getArticleDtoById(@PathVariable Long id) {
        return Result.success(articleService.getOneById(id));
    }
}
