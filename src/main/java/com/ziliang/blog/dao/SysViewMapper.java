package com.ziliang.blog.dao;


import com.ziliang.blog.entity.SysView;

import java.util.List;

public interface SysViewMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysView record);

    int insertSelective(SysView record);

    SysView selectByPrimaryKey(Long id);

    List<SysView> selectAllSysView();

    int numOfSysView();

    int updateByPrimaryKeySelective(SysView record);

    int updateByPrimaryKey(SysView record);
}
