package com.ziliang.blog.controller;

import com.ziliang.blog.entity.SysLog;
import com.ziliang.blog.entity.SysView;
import com.ziliang.blog.result.Result;
import com.ziliang.blog.service.SysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统监控与管理 Controller
 *
 */
@Api(value = "系统管理controller", tags = {"系统管理"})
@CrossOrigin(origins = {"http://localhost:8081"}, allowCredentials = "true")
@RestController
public class SysController {

    @Autowired
    SysService sysService;

    /**
     * 如果前端传来页码，就按页码取信息
     * 否则返回所有的系统日志记录信息
     *
     * @return
     */
    @ApiOperation("返回SysLog信息")
    @ApiImplicitParam(name = "pageNum", value = "页码数", required = false, dataType = "Integer")
    @GetMapping("/sys/log")
    public Result<List<SysLog>> listAllLog(@RequestParam(required = false) Integer pageNum) {
        if (pageNum != null) {
            return Result.success(sysService.listLogByPage(pageNum, 8)); // 一页有多少数据就由后端指定
        }
        return Result.success(sysService.listAllLog());
    }

    /**
     * 如果前端传来页码，就按页码取信息
     * 返回所有的系统浏览记录信息
     *
     * @return
     */
    @ApiOperation("返回SysView信息")
    @ApiImplicitParam(name = "pageNum", value = "页码数", required = false, dataType = "Integer")
    @GetMapping("/sys/view")
    public Result<List<SysView>> listAllView(@RequestParam(required = false) Integer pageNum) {
        if (pageNum != null) {
            return Result.success(sysService.listViewByPage(pageNum, 8)); // 一页有多少数据就由后端指定
        }
        return Result.success(sysService.listAllView());
    }


}
