package com.ziliang.blog.service.impl;

import com.ziliang.blog.dao.SysLogMapper;
import com.ziliang.blog.dao.SysViewMapper;
import com.ziliang.blog.entity.SysLog;
import com.ziliang.blog.entity.SysView;
import com.ziliang.blog.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SysService实现类
 *
 */
@Service
public class SysServiceImpl implements SysService {

    @Autowired
    SysLogMapper sysLogMapper;
    @Autowired
    SysViewMapper sysViewMapper;

    /**
     * 增加一条日志信息
     *
     * @param sysLog
     */
    @Override
    public void addLog(SysLog sysLog) {
        sysLogMapper.insertSelective(sysLog);
    }

    /**
     * 增加一条访问量
     *
     * @param sysView
     */
    @Override
    public void addView(SysView sysView) {
        sysViewMapper.insertSelective(sysView);
    }

    /**
     * 获取日志的总数量
     *
     * @return 日志的总数量
     */
    @Override
    public int getLogCount() {
        return sysLogMapper.numOfSysLog();
    }

    /**
     * 返回当前网站的访问量
     *
     * @return
     */
    @Override
    public int getViewCount() {
        return sysViewMapper.numOfSysView();
    }

    /**
     * 返回所有的日志信息
     *
     * @return
     */
    @Override
    public List<SysLog> listAllLog() {
        return sysLogMapper.selectAllSysLog();
    }

    /**
     * 返回所有的访问信息
     *
     * @return
     */
    @Override
    public List<SysView> listAllView() {
        return sysViewMapper.selectAllSysView();
    }
}
