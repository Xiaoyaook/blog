package com.ziliang.blog.service.impl;

import com.ziliang.blog.BlogApplication;
import com.ziliang.blog.entity.DataView;
import com.ziliang.blog.service.DataViewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class DataViewServiceImplTest {

    @Autowired
    DataViewService dataViewService;

    @Test
    public void showSysLogDataViewNowMonthTest() {
        List<DataView> dataViews = dataViewService.showSysLogDataViewNowMonth();
        for (DataView dv: dataViews) {
            System.out.println(dv);
        }
    }
}