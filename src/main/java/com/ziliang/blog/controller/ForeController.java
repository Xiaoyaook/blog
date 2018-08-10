package com.ziliang.blog.controller;

import com.vladsch.flexmark.ast.Code;
import com.ziliang.blog.dto.ArticleCommentDto;
import com.ziliang.blog.dto.ArticleDto;
import com.ziliang.blog.dto.ArticleWithPictureDto;
import com.ziliang.blog.entity.CategoryInfo;
import com.ziliang.blog.entity.Comment;
import com.ziliang.blog.result.CodeMsg;
import com.ziliang.blog.result.Result;
import com.ziliang.blog.service.ArticleService;
import com.ziliang.blog.service.CategoryService;
import com.ziliang.blog.service.CommentService;
import com.ziliang.blog.util.Markdown2HtmlUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 前台Controller
 *
 */
@Api(value = "前台controller", tags = {"展示页面"})
@CrossOrigin(origins = {"http://localhost:8081"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class ForeController {

    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CommentService commentService;

    /**
     * 获取文章列表,有页码则返回当页数据
     *
     * @return
     */
    @ApiOperation("获取文章列表")
    @ApiImplicitParam(name = "pageNum", value = "页码数", required = false, dataType = "Integer")
    @GetMapping("article/list")
    public Result<List<ArticleWithPictureDto>> listArticleInfo(@RequestParam(required = false) Integer pageNum) {
        if (pageNum != null) {
            return Result.success(articleService.listArticle(pageNum, 8)); // 一页有多少数据就由后端指定
        }
        return Result.success(articleService.listArticle(null, null));
    }

    /**
     * 获取某一个分类下的所有文章，
     * 有页码则返回当页数据
     *
     * @param id
     * @return
     */
    @ApiOperation("获取某一个分类下的文章")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "Long"),
        @ApiImplicitParam(name = "pageNum", value = "页码数", required = false, dataType = "Integer")
    })
    @GetMapping("article/list/category/{id}")
    public Result<List<ArticleWithPictureDto>> listArticleInfo(@PathVariable Long id,
                                                               @RequestParam(required = false) Integer pageNum) {
        if (pageNum != null) {
            return Result.success(articleService.listByCategoryId(id, pageNum, 8)); // 一页有多少数据就由后端指定
        }
        return Result.success(articleService.listByCategoryId(id, null, null));
    }

    /**
     * 获取最新的文章
     *
     * @returngetTagList
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

    /**
     * 获取所有的留言信息
     *
     * @return
     */
    @ApiOperation("获取所有的留言信息")
    @ApiImplicitParam(name = "pageNum", value = "页码数", required = false, dataType = "Integer")
    @GetMapping("comment/list")
    public Result<List<Comment>> listAllComment(@RequestParam(required = false) Integer pageNum) {
        if (pageNum != null) {
            return Result.success(commentService.listCommentByPage(pageNum, 8)); // 一页有多少数据就由后端指定
        }
        return Result.success(commentService.listAllComment());
    }

    /**
     * 通过文章ID获取某一篇文章的评论信息
     *
     * @param id
     * @return
     */
    @ApiOperation("获取某一篇文章的评论信息")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long")
    @GetMapping("comment/article/{id}")
    public Result<List<ArticleCommentDto>> listMessageByArticleId(@PathVariable Long id) {
        return Result.success(commentService.listAllArticleCommentById(id));
    }

    /**
     * 给某一篇文章增加一条评论信息
     *
     * @return
     */
    @ApiOperation("给文章中增加一条评论信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "评论信息", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "Email地址，用于回复", required = false, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "用户自定义的名称", required = true, dataType = "String")
    })
    @PostMapping("comment/article/{id}")
    public Result<CodeMsg> addArticleComment(@PathVariable Long id, @RequestBody ArticleCommentDto articleCommentDto, HttpServletRequest request) {

        String ip = request.getRemoteAddr();
        articleCommentDto.setIp(ip);
        articleCommentDto.setArticleId(id);
        commentService.addArticleComment(articleCommentDto);

        return Result.success(CodeMsg.ADD_ARTICLE_COMMENT_SUCCESS);
    }

    /**
     * 增加一条留言
     *
     * @return
     */
    @ApiOperation("增加一条留言")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "评论信息", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "Email地址，用于回复", required = false, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "用户自定义的名称", required = true, dataType = "String")
    })
    @PostMapping("comment")
    public Result<CodeMsg> addMessage(Comment comment, HttpServletRequest request) {

        String ip = request.getRemoteAddr();
        comment.setIp(ip);
        commentService.addComment(comment);

        return Result.success(CodeMsg.ADD_COMMENT_SUCCESS);
    }
}
