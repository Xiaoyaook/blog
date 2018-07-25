package com.ziliang.blog.dao;


import com.ziliang.blog.entity.SysLog;

import java.util.List;

public interface SysLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Long id);

    List<SysLog> selectAllSysLog();

    int numOfSysLog();

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}
