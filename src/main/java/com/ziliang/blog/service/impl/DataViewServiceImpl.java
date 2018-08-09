package com.ziliang.blog.service.impl;

import com.ziliang.blog.dao.DataViewMapper;
import com.ziliang.blog.entity.DataView;
import com.ziliang.blog.service.DataViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据可视化Service的实现类
 */
@Service
public class DataViewServiceImpl implements DataViewService {

    @Autowired
    DataViewMapper dataViewMapper;

    /**
     * 返回这个月的SysLog计数信息，日期->操作数
     * @return
     */
    @Override
    public List<DataView> showSysLogDataViewNowMonth() {
        return dataViewMapper.selectSysLogNowMonth();
    }

    @Override
    public List<DataView> showSysViewDataViewNowMonth() {
        return dataViewMapper.selectSysViewNowMonth();
    }
}
