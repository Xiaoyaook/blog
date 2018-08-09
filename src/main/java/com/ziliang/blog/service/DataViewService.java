package com.ziliang.blog.service;


import com.ziliang.blog.entity.DataView;

import java.util.List;

/**
 * 数据可视化Service
 */
public interface DataViewService {

    List<DataView> showSysLogDataViewNowMonth();

    List<DataView> showSysViewDataViewNowMonth();
}
