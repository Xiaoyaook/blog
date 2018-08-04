package com.ziliang.blog.controller;

import com.ziliang.blog.entity.SysLog;
import com.ziliang.blog.entity.SysView;
import com.ziliang.blog.result.Result;
import com.ziliang.blog.service.SysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 返回所有的系统日志记录信息
     *
     * @return
     */
    @ApiOperation("返回所有的SysLog信息")
    @GetMapping("/sys/log")
    public Result<List<SysLog>> listAllLog() {
        return Result.success(sysService.listAllLog());
    }

    /**
     * 返回所有的系统浏览记录信息
     *
     * @return
     */
    @ApiOperation("返回所有的SysView信息")
    @GetMapping("/sys/view")
    public Result<List<SysView>> listAllView() {
        return Result.success(sysService.listAllView());
    }

}
