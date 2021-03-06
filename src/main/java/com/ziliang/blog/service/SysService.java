package com.ziliang.blog.service;

import com.ziliang.blog.entity.SysLog;
import com.ziliang.blog.entity.SysView;

import java.util.List;

/**
 * 日志/访问统计等系统相关Service
 *
 */
public interface SysService {
    void addLog(SysLog sysLog);

    void addView(SysView sysView);

    int getLogCount();

    int getViewCount();

    List<SysLog> listAllLog();

    List<SysLog> listLogByPage(int pageNum, int pageSize);

    List<SysView> listAllView();

    List<SysView> listViewByPage(int pageNum, int pageSize);
}
