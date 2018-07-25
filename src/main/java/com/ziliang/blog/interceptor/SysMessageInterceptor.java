package com.ziliang.blog.interceptor;

import com.ziliang.blog.entity.SysLog;
import com.ziliang.blog.entity.SysView;
import com.ziliang.blog.service.SysService;
import com.ziliang.blog.util.BrowserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 系统操作拦截器
 * 说明：用于对访问数量进行统计
 *
 */
@Service
public class SysMessageInterceptor implements HandlerInterceptor {
    @Autowired
    SysService sysService;

    private SysLog sysLog = new SysLog();
    private SysView sysView = new SysView();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 访问者的IP
        String ip = request.getRemoteAddr();
        // 访问地址
        String url = request.getRequestURL().toString();
        //得到用户的浏览器名
        String userbrowser = BrowserUtil.getOsAndBrowserInfo(request);

        // 给SysLog增加字段
        sysLog.setIp(StringUtils.isEmpty(ip) ? "0.0.0.0" : ip);
        sysLog.setOperateBy(StringUtils.isEmpty(userbrowser) ? "获取浏览器名失败" : userbrowser);
        sysLog.setOperateUrl(StringUtils.isEmpty(url) ? "获取URL失败" : url);

        // 增加访问量
        sysView.setIp(StringUtils.isEmpty(ip) ? "0.0.0.0" : ip);
        sysService.addView(sysView);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            // 保存日志信息
            sysLog.setRemark(method.getName());
            sysService.addLog(sysLog);

        } else {
            String uri = request.getRequestURI();
            sysLog.setRemark(uri);
            sysService.addLog(sysLog);
        }

//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();

        // 保存日志信息
//        sysLog.setRemark(method.getName());
//        sysService.addLog(sysLog);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
