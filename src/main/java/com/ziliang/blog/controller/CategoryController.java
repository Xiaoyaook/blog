package com.ziliang.blog.controller;

import com.ziliang.blog.entity.CategoryInfo;
import com.ziliang.blog.service.ArticleService;
import com.ziliang.blog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 分类管理Controller
 *
 */
@Api(value = "分类管理controller", tags = {"分类管理"})
@RestController
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * 增加一条分类信息数据
     *
     * @return
     */
    @ApiOperation("增加分类信息")
    @ApiImplicitParam(name = "name", value = "分类名称", required = true, dataType = "String")
    @PostMapping("category")
    public String addCategoryInfo(@RequestBody CategoryInfo categoryInfo) {
        categoryService.addCategory(categoryInfo);
        return null;
    }

    /**
     * 更新/编辑一条分类信息
     *
     * @param id
     * @return
     */
    @ApiOperation("更新/编辑分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "name", value = "分类名称", required = true, dataType = "String")
    })
    @PutMapping("category/{id}")
    public String updateCategoryInfo(@PathVariable Long id, @RequestBody CategoryInfo categoryInfo) {
        categoryService.updateCategory(categoryInfo);
        return null;
    }

    /**
     * 根据ID删除分类信息
     *
     * @param id
     * @return
     */
    @ApiOperation("删除分类信息")
    @ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "Long")
    @DeleteMapping("category/{id}")
    public String deleteCategoryInfo(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return null;
    }

    /**
     * 通过id获取一条分类信息
     *
     * @param id
     * @return
     */
    @ApiOperation("获取某一条分类信息")
    @ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "Long")
    @GetMapping("category/{id}")
    public CategoryInfo getCategoryInfo(@PathVariable Long id) {
        return categoryService.getOneById(id);
    }

}
