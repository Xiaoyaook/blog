package com.ziliang.blog.dao;


import com.ziliang.blog.entity.DataView;

import java.util.List;

public interface DataViewMapper {
    List<DataView> selectSysLogNowMonth();

    List<DataView> selectSysViewNowMonth();
}
