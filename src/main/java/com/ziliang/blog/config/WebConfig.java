package com.ziliang.blog.config;

import com.ziliang.blog.interceptor.AdminVerificationInterceptor;
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

    @Autowired
    AdminVerificationInterceptor adminVerificationInterceptor;

    /**
     * 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用于排除拦截
        registry.addInterceptor(sysMessageInterceptor).addPathPatterns("/**").excludePathPatterns("/login/do_login", "/js/**", "/css/**", "/img/**");
        registry.addInterceptor(adminVerificationInterceptor).addPathPatterns("/**").excludePathPatterns("/login/do_login", "/api/**");
    }
}
