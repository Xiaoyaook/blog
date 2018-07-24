package com.ziliang.blog.controller;

import com.ziliang.blog.entity.Comment;
import com.ziliang.blog.result.CodeMsg;
import com.ziliang.blog.result.Result;
import com.ziliang.blog.service.impl.CommentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 评论管理Controller
 *
 */
@Api(value = "评论管理controller", tags = {"评论管理"})
@CrossOrigin(origins = {"http://localhost:8081"}, allowCredentials = "true")
@RestController
@RequestMapping("/admin")
public class CommentController {

    @Autowired
    CommentServiceImpl commentService;

    /**
     * 通过评论ID删除文章评论信息
     *
     * @param id
     * @return
     */
    @ApiOperation("删除文章评论信息")
    @ApiImplicitParam(name = "id", value = "评论ID", required = true, dataType = "Long")
    @DeleteMapping("comment/article/{id}")
    public Result<CodeMsg> deleteArticleComment(@PathVariable Long id) {
        commentService.deleteArticleCommentById(id);
        return Result.success(CodeMsg.DELETE_ARTICLE_COMMENT_SUCCESS);
    }

    /**
     * 通过id删除某一条留言
     *
     * @param id
     * @return
     */
    @ApiOperation("删除一条留言")
    @ApiImplicitParam(name = "id", value = "评论/留言ID", required = true, dataType = "Long")
    @DeleteMapping("comment/{id}")
    public Result<CodeMsg> deleteComment(@PathVariable Long id) {
        commentService.deleteCommentById(id);
        return Result.success(CodeMsg.DELETE_COMMENT_SUCCESS);
    }

//    /**
//     * 回复留言/评论，通过id去找到对应的Email
//     *
//     * @param id
//     * @return
//     */
//    @ApiOperation("回复留言/评论")
//    @ApiImplicitParam(name = "id", value = "评论/留言ID", required = true, dataType = "Long")
//    @GetMapping("comment/reply/{id}")
//    public String replyComment(@PathVariable Long id) {
//        return null;
//    }
//
//    /**
//     * 通过id获取某一条评论/留言
//     *
//     * @param id
//     * @return
//     */
//    @ApiOperation("获取某一条评论/留言")
//    @ApiImplicitParam(name = "id", value = "评论/留言ID", required = true, dataType = "Long")
//    @GetMapping("comment/{id}")
//    public Comment getCommentById(@PathVariable Long id) {
//        return null;
//    }
}
