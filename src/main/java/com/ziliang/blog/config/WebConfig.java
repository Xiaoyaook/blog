package com.ziliang.blog.config;

import com.ziliang.blog.interceptor.SysMessageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 解析器和拦截器在此注册
 *
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    SysMessageInterceptor sysMessageInterceptor;

    /**
     * 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(sysMessageInterceptor).addPathPatterns("/**").excludePathPatterns("/toLogin", "/admin/**", "/js/**", "/css/**", "/img/**");
    }
}
