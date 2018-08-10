package com.ziliang.blog.service.impl;

import com.ziliang.blog.BlogApplication;
import com.ziliang.blog.entity.SysLog;
import com.ziliang.blog.entity.SysView;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

/**
 * SysServiceImpl测试类
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogApplication.class)
public class SysServiceImplTest {

    @Autowired
    SysServiceImpl sysService;

    @Ignore
    @Test
    public void addLogTest() {
        SysLog sysLog = new SysLog();
        sysLog.setIp("127.0.0.1");
        sysLog.setRemark("add log 2");
        sysLog.setOperateUrl("/admin");
        sysService.addLog(sysLog);
    }

    @Ignore
    @Test
    public void addViewTest() {
        SysView sysView = new SysView();
        sysView.setIp("127.0.0.1");

        sysService.addView(sysView);
    }

    @Ignore
    @Test
    public void getLogCountTest() {
        int count = sysService.getLogCount();
        assertThat(count, is(2));
    }

    @Test
    public void getViewCountTest() {
        int count = sysService.getViewCount();
        assertThat(count, is(2));
    }

    @Ignore
    @Test
    public void listAllLogTest() {
        List<SysLog> logList = sysService.listAllLog();
        for (SysLog sysLog: logList) {
            System.out.println(sysLog);
        }
    }

    @Ignore
    @Test
    public void listAllViewTest() {
        List<SysView> viewList = sysService.listAllView();
        for (SysView sysView: viewList) {
            System.out.println(sysView);
        }
    }


    @Test
    public void listLogByPageTest() {
        List<SysLog> logList = sysService.listLogByPage(1,8);
        for (SysLog sysLog: logList) {
            System.out.println(sysLog);
        }
    }


    @Test
    public void listViewByPageTest() {
        List<SysView> viewList = sysService.listViewByPage(1,10);
        for (SysView sysView: viewList) {
            System.out.println(sysView);
        }
    }
}