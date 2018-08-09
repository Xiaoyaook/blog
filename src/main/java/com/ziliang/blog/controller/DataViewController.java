package com.ziliang.blog.controller;

import com.ziliang.blog.entity.DataView;
import com.ziliang.blog.result.Result;
import com.ziliang.blog.service.DataViewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 数据可视化，数据管理Controller
 * 前端获取数据可视化的接口
 */
@Api(value = "数据管理controller", tags = {"数据管理"})
@CrossOrigin(origins = {"http://localhost:8081"}, allowCredentials = "true")
@RestController
@RequestMapping("/dataview")
public class DataViewController {

    @Autowired
    DataViewService dataViewService;

    /**
     * 返回这个月的SysLog计数信息
     * @return
     */
    @ApiOperation("返回这个月的SysLog计数信息，日期->操作数")
    @GetMapping("/thismonth/syslog")
    public Result<List<DataView>> listSysLogDataViewNowMonth() {
        return Result.success(dataViewService.showSysLogDataViewNowMonth());
    }

    /**
     * 返回这个月的SysView计数信息
     * @return
     */
    @ApiOperation("返回这个月的SysView计数信息，日期->操作数")
    @GetMapping("/thismonth/sysview")
    public Result<List<DataView>> listSysViewDataViewNowMonth() {
        return Result.success(dataViewService.showSysViewDataViewNowMonth());
    }


}
